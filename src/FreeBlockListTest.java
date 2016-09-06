import student.TestCase;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016.
 */

/**
 * tests for free block list
 */
public class FreeBlockListTest extends TestCase{
    /**
     * list instance used in tests
     */
    private FreeBlockList list;

    /**
     * initialize member variables
     */
    public void setUp() {
        list = new FreeBlockList(6);
    }
    /**
     * Insert then print free blocks
     */
    public void testPrintBlocks() {
        list.add(0, new Helper.Tuple(2, 5));
        list.add(0, new Helper.Tuple(3, 6));
        list.printBlocks();
    }

    /**
     * Test for available blocks
     */
    public void testNextAvailable() {
        assertEquals(0, list.getNextAvailable(4));
        assertEquals(-1, list.getNextAvailable(3));
        assertEquals(4, list.getNextAvailable(2));
        list.printBlocks();
        FreeBlockList list1 = new FreeBlockList(0);
        assertEquals(0, list1.getNextAvailable(7));
    }
    /**
     * List expansion test first case
     */
    public void testExpansionA() {
        FreeBlockList list1 = new FreeBlockList(0);
        list1.expand(5, 0);
        list1.getNextAvailable(4);
        list1.expand(6, 5);
        list1.printBlocks();
        assertEquals(1, list1.size());
    }
    /**
     * List expansion test second case
     */
    public void testExpansionB() {
        list.getNextAvailable(6);
        list.expand(5, 6);
        assertEquals(6, list.getNextAvailable(3));
        list.printBlocks();
    }
}