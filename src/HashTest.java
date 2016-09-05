
import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  Test the hash function (you should throw this away for your project)
 *
 *  @author Cheick Berthe
 *  @author Broulaye Doumbia
 *  @version 09/05/2016
 */
public class HashTest extends TestCase {
    private static final String ALPHA_NUMERIC_STRING = 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    /**
     * this method generate random strings
     * @param count represent the string length
     * @return a random string 
     */
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random() * 
                    ALPHA_NUMERIC_STRING.length());
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
    public void testInsert() {

        Hash myHash;
        try {
            myHash = new Hash(2);
            for (int i = 0; i < 20; i++) {
                myHash.insertString(randomAlphaNumeric(Math.round(6)));
            }
            myHash.printTable();
        } 
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
        
    }
    
    /**
     * Test the hash table with negative size
     */
    public void testNegativeSize() {
        
        Hash table;
        try {
            table = new Hash(-2);
            table.insertString("cheick");
        } 
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }

    /**
     * Test for duplicate strings.
     * TODO: implement get method
     */
    public void testDuplicateStrings() {
        try {
            Hash table = new Hash(3);
            String str = "berthe";
            int position1 = table.insertString(str);
            int position2 = table.insertString(str);
            assertEquals(position2, position1);
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }
}
