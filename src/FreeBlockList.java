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
        if (size <= 0) {
            return "(" + poolLength + ", 0)";
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
            currentLength = get(i).getY() - get(i).getX() + 1;
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
        currentLength = get(at).getY() - get(at).getX() + 1;
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
        if(size <= 0) {
            add(new Block(location, length-1));
            return;
        }
        int rightEnd = location + length - 1;
        Block newBlock;
        for (int it = 0; it < size(); it++) {
            if (location < get(it).getX()) {
                // add to left of node
                if (rightEnd + 1 == get(it).getX()) {
                    // merge
                    newBlock = new Block(location, get(it).getX());
                    remove(it);
                    add(it, newBlock);
                    return;
                }
                newBlock = new Block(location, rightEnd);
                add(it, newBlock);
                return;
            }
            if (location > get(it).getY()) {
                // add to right of node
                if (location - 1 == get(it).getY()) {
                    // merge
                    newBlock = new Block(get(it).getX(), rightEnd);
                    remove(it);
                    add(it, newBlock);
                    return;
                }
                newBlock = new Block(location, rightEnd);
                add(it + 1, newBlock);
                return;
            }
        }
    }
    
    public int getPoolLenght() {
        return poolLength;
    }
}
