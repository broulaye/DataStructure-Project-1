/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/5/2016.
 */
public class MemManager {
    // Freeblock list
    private DLLinkedList<byte> freeBlockList;

    /**
     * constructor
     * @param poolsize size of memory pool in bytes
     */
    public MemManager(int poolsize) {
        freeBlockList = new DLLinkedList<>();
    }


    /**
     * Insert a record and return its position handle
     * @param space record to be inserted
     * @param size length of the record
     * @return
     */
    public Handle insert(byte[] space, int size) {
        return null;
    }

    /**
     * Free a block at position specified by theHandle
     * Merge adjacent blocks
     * @param theHandle handle referring to position
     */
    public void remove(Handle theHandle) {
        // TODO:
    }

    /**
     * Return the record with given handle up to size
     * bytes by copying it into space
     * @param space returned record
     * @param theHandle handle to be retrieved
     * @param size size of record to be returned
     * @return record with
     */
    public int get(byte[] space, Handle theHandle, int size) {
        // TODO
        return 0;
    }

    // Dump a printout of the freeblock list
    void dump() {
        freeBlockList.toString();
    }
}
