import java.io.PrintWriter;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016.
 */
public class MemoryPool {

    // Memory pool array
    private byte[] pool;

    // Free block list
    private FreeBlockList freeBlockList;

    // block size
    private int blockSize;

    /**
     * Constructor
     *
     * @param poolSize
     *            initial size
     */
    public MemoryPool(int poolSize) {
        blockSize = poolSize;
        freeBlockList = new FreeBlockList(poolSize);
        pool = new byte[poolSize];
    }

    /**
     * Store bytes of given length
     *
     * @param bytes
     *            content in bytes
     * @param length1
     *            required lenght
     * @param writer
     *            used to return status of operation
     * @return position where bytes was stored
     */
    public int put(byte[] bytes, int length1, PrintWriter writer) {
        int length = length1 + 2;
        int whereToStore = freeBlockList.getNextAvailable(length);
        // keep expanding until there is enough free space
        while (length > pool.length || whereToStore == -1) {
            int newSize = pool.length + this.blockSize;
            freeBlockList.expand(blockSize, pool.length);
            pool = resizeArray(pool, newSize);
            writer.println(
                    "Memory pool expanded to be " + pool.length + " bytes.");
            whereToStore = freeBlockList.getNextAvailable(length);
        }
                
        // copy size to pool as 2 byte number
        pool[whereToStore] = (byte) ((length1 >> 8) & 0xFF);
        pool[whereToStore + 1] = (byte) (length1 & 0xFF);
        // copy byte into memory pool
        int j = 0;
        for (int i = whereToStore + 2; i < (whereToStore + length); i++) {
            pool[i] = bytes[j];
            j++;
        }
        return whereToStore;
    }

    /**
     * get string stored at given location
     *
     * @param location
     *            start position of string
     * @return string from memory pool
     */
    public String getStringAt(int location) {
        int length = (pool[location] << 8) + pool[location + 1];
        StringBuilder builder = new StringBuilder();
        for (int i = location + 2; i <= length + location + 1; i++) {
            builder.append(Character.toString((char) pool[i]));
        }
        return builder.toString();
    }

    /**
     * Removes string at given location
     *
     * @param location
     *            location of string
     */
    public void removeStringAt(int location) {
        int length = (pool[location] << 8) + pool[location + 1];
        freeBlockList.freeUpSpace(location, length + 2);
    }

    /**
     * Print content of memory pool
     * @return a string representation of the memory pool
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (byte element : pool) {
            builder.append((char) element);
        }
        return builder.toString();
    }

    /**
     * Get number of free blocks in list
     *
     * @return number of free blocks
     */
    public int numbOfFreeBlocks() {
        return freeBlockList.size();
    }

    /**
     * @return size of memory pool
     */
    public int getSize() {
        return pool.length;
    }

    /**
     * print free block list
     * 
     * @return a string representation of the blocks
     */
    public String printFreeBlocks() {
        if (freeBlockList.isEmpty()) {
            return "(" + (pool.length) + "," + "0)";
        }
        return freeBlockList.printBlocks();
    }

    /**
     * Resize byte array
     *
     * @param bytes
     *            array to be resized
     * @param newSize
     *            new size of array
     * @return new array with new size
     */
    private byte[] resizeArray(byte[] bytes, int newSize) {
        byte[] newArray = new byte[newSize];
        System.arraycopy(bytes, 0, newArray, 0, bytes.length);
        return newArray;
    }
}
