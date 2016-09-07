import student.TestCase;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016.
 */

/**
 * tests for free block list
 */
public class MemoryPoolTest extends TestCase{
    /**
     * memory pool used in tests
     */
    private MemoryPool pool;
    byte[] string;
    String word;
    /**
     * initialize member variables
     */
    public void setUp() {
        word = new String();
        pool = new MemoryPool(10);
    }
    /**
     * Insert then print free blocks
     */
    public void testPut() {
        word = "CheickBerthe";
        string = word.getBytes();
        pool.put(string, word.length());
        assertEquals(1, pool.numbOfFreeBlocks());
        assertEquals(20, pool.getSize());
        pool.toString();
    }

    /**
     * Get String test
     */
    public void testGet() {
        string = "BroulayeDoumbia".getBytes();
        int where = pool.put(string, 5);
        pool.toString();
        System.out.println(pool.getStringAt(where));
    }

    /**
     * Test removal of string
     */
    public void testRemove() {
        string = "VirginiaTech".getBytes();
        int at = pool.put(string, 6);
        System.out.println("Before Remove");
        pool.toString();
        pool.printFreeBlocks();
        pool.removeStringAt(at);
        System.out.println("\nAfter Remove");
        pool.printFreeBlocks();
        pool.toString();

    }
}