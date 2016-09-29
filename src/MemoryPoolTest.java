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
        pool.printFreeBlocks();
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
        System.out.println(pool.printFreeBlocks());
        int at = pool.put(string, 6, writer);
        System.out.println(pool.printFreeBlocks());
        pool.toString();
        pool.printFreeBlocks();
        pool.removeStringAt(at);
        System.out.println(pool.printFreeBlocks());
        assertEquals("(0,10)", pool.printFreeBlocks());

    }
    /**
     * Remove on empty pool
     */
    public void testRemoveOnEmpty() {
        string = "VirginiaTech".getBytes();
        System.out.println(pool.printFreeBlocks());
        int at = 2;
        pool.removeStringAt(at);
        String noString = pool.getStringAt(at);
        assertEquals("", noString);
    }
    /**
     * Fill up space then print free blocks
     */
    public void testFill() {
        pool = new MemoryPool(8);
        word = "Cheick";
        string = word.getBytes();
        pool.put(string, word.length(), writer);
        assertEquals(0, pool.numbOfFreeBlocks());
        assertEquals(8, pool.getSize());
        pool.printFreeBlocks();
    }
    
}