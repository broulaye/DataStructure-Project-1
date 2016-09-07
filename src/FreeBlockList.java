
/**
 * Created by berth on 9/6/2016.
 */
public class FreeBlockList extends DLLinkedList<Helper.Tuple> {
    /**
     * Constructor
     * @param poolsize size of free space
     */
    public FreeBlockList(int poolsize) {
        if (poolsize > 0) {
            add(new Helper.Tuple(0, poolsize - 1));
        }
    }

    /**
     * Expand list by addition
     * @param additionalLength additional length
     */
    public void expand(int additionalLength, int oldLength) {
        if (size() == 0) {
            add(new Helper.Tuple(oldLength, oldLength + additionalLength - 1));
            return;
        }
        Helper.Tuple lastBlock = get(size() - 1);
        if (lastBlock.getY() == oldLength - 1) {
            lastBlock.setY(lastBlock.getY() + additionalLength);
        }
        else {
            add(new Helper.Tuple(oldLength, oldLength + additionalLength - 1));
        }
    }
    /**
     * print content of list
     */
    public String printBlocks() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(get(i).toString());
            if (i < size() - 1) {
                builder.append("->");
            }
        }
        return builder.toString();
    }

    /**
     *
     * @param requiredLength
     * @return
     */
    public int getNextAvailable(int requiredLength) {
        // check if list is empty
        if (size() == 0) {
            expand(requiredLength, 0);
            return 0;
        }
        for (int i = 0; i < size(); i++) {
            // get size of current node
            int currentLength = get(i).getY() - get(i).getX() + 1;
            // check for exact fit
            if (currentLength == requiredLength) {
                int startOfFreeBlock = get(i).getX();
                remove(i);
                return startOfFreeBlock;
            }
            if (currentLength > requiredLength) {
                int remainingSpace = currentLength - requiredLength;
                int newStart = get(i).getX() + requiredLength;
                int startOfFreeBlock = get(i).getX();
                remove(i);
                Helper.Tuple newFreeBlock = new Helper.Tuple(newStart, newStart + remainingSpace - 1);
                add(i, newFreeBlock);
                return startOfFreeBlock;
            }
        }
        // no available
        return -1;
    }

    /**
     * Adds new free space block
     * @param location start of block
     * @param length length of block
     */
    public void freeUpSpace(int location, int length) {
        add(new Helper.Tuple(location, location + length - 1));
    }
}
