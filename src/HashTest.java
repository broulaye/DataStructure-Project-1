
import student.TestCase;

import java.io.PrintWriter;

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
        MemManager memManager;
        try {
            memManager = new MemManager(2);
            myHash = new Hash(2, memManager);
            for (int i = 0; i < 20; i++) {
                PrintWriter writer = null;
                myHash.insertString(randomAlphaNumeric(Math.round(6)), writer);
            }
            System.out.print(myHash.printTable());
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
            table = new Hash(-2, new MemManager(2));
            PrintWriter writer = null;
            table.insertString("cheick", writer);
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
        MemManager manager = new MemManager(3);
        try {
            Hash table = new Hash(3, manager);
            String str = "berthe";
            PrintWriter writer = null;
            boolean result1 = table.insertString(str, writer);
            boolean result2 = table.insertString(str, writer);
            assertTrue(result1);
            assertFalse(result2);
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }
    
    /**
     * TestgetElement
     */
    public void testgetElement() {
        MemManager manager = new MemManager(3);
    	try {
            Hash table = new Hash(3, manager);
            String str = "berthe";
            PrintWriter writer = null;
            boolean result = table.insertString(str, writer);
            
            assertEquals(1, table.getElement());
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }
}
