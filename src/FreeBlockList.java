/**
 * Represent the free block list keeping track of the available free blocks
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/07/2016
 */
public class FreeBlockList extends DLLinkedList<Block> {

    private int poolLength;

    /**
     * Constructor
     *
     * @param poolsize
     *            size of free space
     */
    public FreeBlockList(int poolsize) {
        if (poolsize > 0) {
            add(new Block(0, poolsize - 1));
        }

        poolLength = poolsize;
    }

    /**
     * Expand list by addition
     *
     * @param additionalLength
     *            additional length
     * @param oldLength
     *            represent the old length
     */
    public void expand(int additionalLength, int oldLength) {
        if (size() == 0) {
            add(new Block(oldLength, oldLength + additionalLength - 1));
            poolLength += additionalLength;
            return;
        }
        Block lastBlock = get(size() - 1);
        if (lastBlock.getY() == oldLength - 1) {
            lastBlock.setY(lastBlock.getY() + additionalLength);
        }
        else {
            add(new Block(oldLength, oldLength + additionalLength - 1));
        }
        
        poolLength += additionalLength;
    }

    /**
     * print content of the list
     *
     * @return a string representation of the list
     */
    public String printBlocks() {

        if (size == 0) {
            return "(" + poolLength + ",0)";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            builder.append(get(i).toString());
            if (i < size() - 1) {
                builder.append(" -> ");
            }
        }
        return builder.toString();
    }

    /**
     * get the next available free space of requredLength using best fit
     * algorithm
     *
     * @param requiredLength
     *            length to look for
     * @return -1 if no free space is found otherwise return start of free block
     */
    public int getNextAvailable(int requiredLength) {
        // check if list is empty
        if (size() == 0) {
            // expand(requiredLength, 0);
            return -1;
        }
        int min = -1;
        int at = -1;
        int currentLength;
        for (int i = 0; i < size(); i++) {
            // get size of current node
            currentLength = get(i).getSize();
            int newMin = currentLength - requiredLength;
            if (newMin >= 0) {
                if (min < 0) {
                    min = newMin;
                    at = i;
                }
                else if (newMin < min) {
                    min = newMin;
                    at = i;
                }
            }

        }
        if (at < 0) {
            return at;
        }
        currentLength = get(at).getSize();
        if (currentLength == requiredLength) {
            int startOfFreeBlock = get(at).getX();
            remove(at);
            return startOfFreeBlock;
        }
        else {
            int remainingSpace = currentLength - requiredLength;
            int newStart = get(at).getX() + requiredLength;
            int startOfFreeBlock = get(at).getX();
            remove(at);
            Block newFreeBlock =
                    new Block(newStart, newStart + remainingSpace - 1);
            add(at, newFreeBlock);
            return startOfFreeBlock;
        }
    }

    /**
     * Adds new free space block
     *
     * @param location
     *            start of block
     * @param length
     *            length of block
     */
    public void freeUpSpace(int location, int length) {
        // add a new node when the list is empty
        if (size == 0) {
            add(0, new Block(location, location + length - 1));
            return;
        }

        Block node;
        int x = location - 1;
        int y = location + length;
        int i = 0;
        node = get(i); // head node
        // check is you can merge with node at the right
        if (node.getX() == y) {
            add(0, new Block(location, location + length - 1));
            merge(0, 1);
        }
        else if (node.getX() < y) {
            while (node.getX() < y) {
                i++;
                if (i == size()) {
                    break;
                }
                node = get(i);

            }

            add(i, new Block(location, location + length - 1));

            if (i + 1 == size()) {
                if (get(i - 1).getY() == x) {

                    // add(i, new Block(location, location + length - 1));

                    merge(i - 1, i);

                    return;
                }
                else {
                    // add(i, new Block(location, location + length - 1));
                    return;
                }
            }

            if (get(i - 1).getY() == x) {

                merge(i - 1, i);
                int newlocation = i - 1;

                if ((get(newlocation).getY() + 1) == get(newlocation + 1)
                        .getX()) {

                    merge(newlocation, newlocation + 1);

                }
            }
            else if ((get(i + 1).getX()) == y) {

                merge(i, i + 1);

            }
        }

        else {
            add(0, new Block(location, location + length - 1));
        }

    }

    /**
     * merge to node at given location
     * 
     * @param loc1
     *            first location
     * @param loc2
     *            second location
     */
    private void merge(int loc1, int loc2) {
        get(loc1).setY(get(loc2).getY());
        remove(get(loc2));
    }

    /**
     * get pool lenght
     * 
     * @return poolLenght
     */
    public int getPoolLenght() {
        return poolLength;
    }
}
