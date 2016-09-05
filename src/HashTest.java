import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the hash function (you should throw this away for your project)
 *
 *  @author Cheick Berthe
 *  @version Sep 5, 2016
 */
public class HashTest extends TestCase {
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        // Nothing Here
    }
    
    /**
     * Test the hash table
     */
    public void testh() {

        Hash myHash;
		try {
			myHash = new Hash(2);
			for (int i = 0; i < 20; i++) {
				myHash.insertString(randomAlphaNumeric(Math.round(6)));
			}
	        myHash.printTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
