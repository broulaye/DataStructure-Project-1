import student.TestCase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * tests for free block list
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016.
 */
public class MemoryPoolTest extends TestCase {
    /**
     * memory pool used in tests
     */
    private MemoryPool pool;
    private byte[] string;
    private String word;
    private PrintWriter writer = null;

    /**
     * initialize member variables
     */
    public void setUp() {
        word = "";
        pool = new MemoryPool(10);
        try {
            writer = new PrintWriter("memoryPoolTest.txt", "UTF-8");
        }
        catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert then print free blocks
     */
    public void testPut() {
        word = "CheickBerthe";
        string = word.getBytes();
        pool.put(string, word.length(), writer);
        assertEquals(1, pool.numbOfFreeBlocks());
        assertEquals(20, pool.getSize());
        pool.toString();
    }

    /**
     * Get String test
     */
    public void testGet() {
        string = "BroulayeDoumbia".getBytes();
        int where = pool.put(string, 5, writer);
        pool.toString();
        assertEquals("Broul", pool.getStringAt(where));
    }

    /**
     * Test removal of string
     */
    public void testRemove() {
        string = "VirginiaTech".getBytes();

        int at = pool.put(string, 6, writer);
        System.out.println("Before Remove");
        pool.toString();
        pool.printFreeBlocks();
        pool.removeStringAt(at);
        System.out.println("\nAfter Remove");
        pool.printFreeBlocks();
        assertEquals("(0,9)", pool.printFreeBlocks());

    }
}