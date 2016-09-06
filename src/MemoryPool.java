
/**
 * Created by berth on 9/6/2016.
 */
public class MemoryPool {

    // Memory pool array
    private byte[] pool;

    // Freeblock list
    private FreeBlockList freeBlockList;

    /**
     * Constructor
     *
     * @param poolSize initial size
     */
    public MemoryPool(int poolSize) {
        freeBlockList = new FreeBlockList(poolSize);
        pool = new byte[poolSize];
    }

    /**
     * @param bytes   content in bytes
     * @param length1 required lenght
     */
    public void put(byte[] bytes, int length1) {
        int length = length1 + 2;
        int whereToStore = freeBlockList.getNextAvailable(length);
        if (length > pool.length || whereToStore == -1) {
            int newSize = pool.length + length;
            freeBlockList.expand(length, pool.length);
            pool = Helper.resizeArray(pool, newSize);
            System.out.println("Memory pool expanded to be " + pool.length + " bytes.");
            whereToStore = freeBlockList.getNextAvailable(length);
        }
        // copy size to pool as 2 byte number
        pool[whereToStore] = (byte) ((length1 >> 8) & 0xFF);
        pool[whereToStore + 1] = (byte) (length1 & 0xFF);
        // copy byte into memory pool
        int j = 0;
        for (int i = whereToStore + 2; i < length; i++) {
            pool[i] = bytes[j];
            j++;
        }


    }

    /**
     * Print content of memory pool
     */
    public void printContent() {
        for (byte element : pool) {
            System.out.print((char) element);
        }
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
     */
    public void printFreeBlocks() {
        freeBlockList.printBlocks();
    }
}
