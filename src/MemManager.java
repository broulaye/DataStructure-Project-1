import java.io.PrintWriter;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/5/2016.
 */
public class MemManager {

    // memory pool
    private MemoryPool memoryPool;

    /**
     * constructor
     *
     * @param poolSize
     *            size of memory pool in bytes
     */
    public MemManager(int poolSize) {
        memoryPool = new MemoryPool(poolSize);
    }

    /**
     * Insert a record and return its position handle
     *
     * @param str
     *            string to be stored
     * @param writer
     *            used to return status of operation
     * @return handle for str
     */
    public Handle insert(String str, PrintWriter writer) {
        int position = memoryPool.put(str.getBytes(), str.length(), writer);
        return new Handle(position);
    }

    /**
     * Free a block at position specified by theHandle Merge adjacent blocks
     *
     * @param theHandle
     *            handle referring to position
     */
    public void remove(Handle theHandle) {
        memoryPool.removeStringAt(theHandle.getLocation());
    }

    /**
     * Dump content of memory pool
     * 
     * @return a string representation of the memory pool
     */
    public String dump() {
        return memoryPool.printFreeBlocks();
    }

    /**
     * get string corresponding to given handle
     *
     * @param theHandle
     *            handle used for retrieval
     * @return string from memory pool
     */
    public String get(Handle theHandle) {

        return memoryPool.getStringAt(theHandle.getLocation());
    }
}
