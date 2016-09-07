import java.io.PrintWriter;

/**
 * Hash table class.
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 09/05/2015
 */

public class Hash {
    // initial size
    private int initialSize;
    // Array of strings
    private Handle[] valueArray;

    // number of elements in table
    private int numbElements;

    // memory manager
    private MemManager manager;

    /**
     * Create a new Hash object.
     *
     * @throws Exception
     *             thrown when negative size value is passed
     * @param initialSize
     *            represent the hash table's initial size
     */
    public Hash(int initialSize, MemManager memManager) throws Exception {
        if (initialSize <= 0) {
            throw new Exception("WARN: invalid size type");
        }
        this.initialSize = initialSize;
        valueArray = new Handle[initialSize];
        numbElements = 0;
        manager = memManager;
    }

    /**
     *
     * @param str
     * @return
     */
    public int get(String str) {
        int index = hash(str, initialSize);
        int pos = index;
        int i = 0;
        do {
            if (valueArray[pos] != null && str.equals(handle2String(valueArray[pos]))) {
                return pos;
            }
            pos = (pos + ++i * i) % valueArray.length;
        }
        while (pos != index);
        return -1;
    }
    /**
     * get string for given handle
     * @param theHandle handle associated with returned string
     * @return retrieved string
     */
    public String handle2String(Handle theHandle) {

        return manager.get(theHandle);
    }

    /**
     *
     * @param str
     *            (string to insert)
     * @param writer
     * @return position of insertion
     * @throws Exception
     *             when all possible slots have been proved and are occupied
     */
    public boolean insertString(String str, PrintWriter writer) throws Exception {
        if (get(str) != -1) {
            return false;
        }
        int index = hash(str, initialSize);
        int pos = index;
        int i = 0;
        while (valueArray[pos] != null && !valueArray[pos].isTombStone()) {
            pos = (pos + ++i * i) % valueArray.length;
            if (pos == index) {
                throw new Exception("WARN: Cannot insert string");
            }
        }
        // store handle after storing string in memory pool
        valueArray[pos] = manager.insert(str, writer);
        numbElements++;
        if (numbElements >= (valueArray.length >> 1)) {
            valueArray = Helper.resizeArray(valueArray, valueArray.length * 2);
        }
        return true;
    }

    /**
     * print the hash table
     */
    public String printTable() {
        StringBuilder builder = new StringBuilder();
        int pos = 0;
        for (Handle handle : valueArray) {
            if (handle != null && !handle.isTombStone()) {
                builder.append("|").append(handle2String(handle)).append("| ")
                        .append(pos).append("\n");
            }
            pos++;
        }
        return builder.toString();
    }

    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    // This is private for distributing hash function in a way that will
    // pass milestone 1 without change.
    private int hash(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (char aC : c) {
                sum += aC * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (char aC : c) {
            sum += aC * mult;
            mult *= 256;
        }
        return (int) (Math.abs(sum) % m);
    }

    /**
     * get the number of elements
     * @return the number of elements
     */
    public int getElement() {
        return numbElements;
    }

    /**
     *
     * @param str
     * @return
     */
    public boolean removeString(String str) {
        int where = get(str);
        if (where == -1) {
            return false;
        }
        valueArray[where].setTombstone();
        manager.remove(valueArray[where]);
        return true;
    }
}