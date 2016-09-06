
/**
 * Created by berth on 9/6/2016.
 */
public class MemoryPool {
    private byte[] pool;
    // Freeblock list
    private FreeBlockList freeBlockList;
    public MemoryPool(int poolsize) {
        freeBlockList = new FreeBlockList(poolsize);
        pool = new byte[poolsize];
    }

    /**
     * @param bytes
     * @param length
     */
    public void put(byte[] bytes, int length) {

        int whereToStore = freeBlockList.getNextAvailable(length);
        if (length > pool.length || whereToStore == -1) {
            int newSize = pool.length + length;
            freeBlockList.expand(length, pool.length);
            pool = Helper.resizeArray(pool, newSize);
            System.out.println("Memory pool expanded to be " + pool.length + " bytes.");
            int j = 0;
            for (int i = freeBlockList.getNextAvailable(length) ; i < length ; i++) {
                pool[i] = bytes[j];
                j++;
            }
        }
        else {
            // copy byte into memory pool
            int j = 0;
            for (int i = whereToStore; i < length; i++) {
                pool[i] = bytes[j];
                j++;
            }
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
     * @return number of free blocks
     */
    public int numbOfFreeBlocks() {
        return freeBlockList.size();
    }

    /**
     * @return
     */
    public int getSize() {
        return pool.length;
    }

    public void printFreeBlocks() {
        freeBlockList.printBlocks();
    }
}
