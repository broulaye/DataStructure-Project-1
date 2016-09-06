/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/5/2016.
 */
public class MemManager {

    // memory pool
    MemoryPool memoryPool;

    /**
     * constructor
     * @param poolSize size of memory pool in bytes
     */
    public MemManager(int poolSize) {
        memoryPool = new MemoryPool(poolSize);
    }


    /**
     * Insert a record and return its position handle
     * @param str string to be stored
     * @return handle for str
     */
    public Handle insert(String str) {
        int position = memoryPool.put(str.getBytes(), str.length());
        return new Handle(position);
    }

    /**
     * Free a block at position specified by theHandle
     * Merge adjacent blocks
     * @param theHandle handle referring to position
     */
    public void remove(Handle theHandle) {
        memoryPool.removeStringAt(theHandle.getLocation());
    }

    /**
     * Return the record with given handle up to size
     * bytes by copying it into space
     * @param space returned record
     * @param theHandle handle to be retrieved
     * @param size size of record to be returned
     * @return number of bytes copied into space
     */
    private int get(byte[] space, Handle theHandle, int size) {
        // TODO
        return 0;
    }
    private
    // Dump a printout of the freeblock list
    void dump() {
        memoryPool.printFreeBlocks();
    }

    /**
     *
     * @param theHandle
     * @return
     */
    public String get(Handle theHandle) {

        return memoryPool.getStringAt(theHandle.getLocation());
    }
}
