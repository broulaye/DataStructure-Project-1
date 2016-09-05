/**
 * Hash table class.
 *
 * @author Cheick Berthe
 * @version Sep 5, 2016
 */

public class Hash {

    /**
     * Create a new Hash object.
     * @throws Exception thrown when negative size value is passed
     */
    public Hash(int initialSize) throws Exception {
    	if (initialSize <= 0) {
    		throw new Exception("WARN: invalid size type");
    	}
        valueArray = new String[initialSize];
        numbElements = 0;
    }
    /**
     * 
     * @param arr (array to be resized)
     * @param newSize (new size)
     * @return new array
     */
    private String[] resizeArray(String[] arr, int newSize) {
    	String[] newArray = new String[newSize];
    	for (int it = 0; it < arr.length;  it++) {
    		if (arr[it] != null) {
    			newArray[it] = arr[it];
    		}
    	}
    	return newArray;
    }
    
    // Array of strings
    private String[] valueArray;
    // number of elements in table
    private int numbElements;
    /**
     * 
     * @param str (string to insert)
     * @return position of insertion
     * @throws Exception when all possible slots have been proved and are occupied
     */
    public int insertString(String str) throws Exception {
    	int index;
    	int pos = index = hash(str, valueArray.length);
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
        	valueArray = resizeArray(valueArray, valueArray.length * 2);
        }
       	return index;
    }
    /**
     * 
     */
    public void printTable() {
    	for (String string : valueArray) {
			if (string != null) {
				System.out.println(string);
			} else { 
				System.out.println("**NULL**");
			}
		}
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

        return (int)(Math.abs(sum) % m);
    }
}