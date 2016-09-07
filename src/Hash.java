/**
 * Hash table class.
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 09/05/2015
 */

public class Hash {

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
        valueArray = new String[initialSize];
        numbElements = 0;
        manager = memManager;
    }
    String handle2String(Handle theHandle) {

        return manager.get(theHandle);
    }

    // Array of strings
    private String[] valueArray;

    // number of elements in table
    private int numbElements;

    // memory manager
    private MemManager manager;
    /**
     * 
     * @param str
     *            (string to insert)
     * @return position of insertion
     * @throws Exception
     *             when all possible slots have been proved and are occupied
     */
    public boolean insertString(String str) throws Exception {
        int index = hash(str, valueArray.length);
        int pos = index;
        int i = 0;
        while (valueArray[pos] != null) {
            pos = (pos + ++i * i) % valueArray.length;
            if (pos == index) {
                throw new Exception("WARN: Cannot insert string");
            }
        }
        valueArray[pos] = str;
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
        for (String string : valueArray) {
            if (string != null) {
                builder.append(string);
            } 
            else {
                builder.append("**NULL**");
            }
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

    public boolean removeString(String str) {
        //TODO:
        return false;
    }
}