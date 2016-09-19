import student.TestCase;

/**
 * tests for free block list
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016.
 */
public class FreeBlockListTest extends TestCase {
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
        list.add(0, new Block(2, 5));
        list.add(0, new Block(3, 6));
        assertEquals("(3,4) -> (2,4) -> (0,6)", list.printBlocks());
    }

    /**
     * Test for available blocks
     */
    public void testNextAvailable() {
        System.out.println("Test next availabe: ");

        System.out.println(list.printBlocks()); // 0,6

        assertEquals(0, list.getNextAvailable(4));

        System.out.println(list.printBlocks()); // 0,4

        assertEquals(-1, list.getNextAvailable(3));

        System.out.println(list.printBlocks()); // 0,4

        assertEquals(4, list.getNextAvailable(2));

        System.out.println(list.printBlocks()); // 6,0

        list.freeUpSpace(0, 3);

        System.out.println(list.printBlocks()); // 0,3

        list.expand(5, 6);

        System.out.println(list.printBlocks()); // 0,3 -> 6,5

        list.getNextAvailable(4);

        System.out.println(list.printBlocks()); // 0,3 -> 6,5

        list.getNextAvailable(1);

        System.out.println(list.printBlocks()); // 0,3 -> 6,5

        list.printBlocks();

        FreeBlockList list1 = new FreeBlockList(0);

        assertEquals(-1, list1.getNextAvailable(7));
    }

    /**
     * List expansion test first case
     */
    public void testExpansionA() {
        FreeBlockList list1 = new FreeBlockList(0);
        list1.expand(5, 0);
        list1.getNextAvailable(4);
        list1.expand(6, 5);
        System.out.println(list1.printBlocks());
        assertEquals(1, list1.size());
        assertEquals(7, list1.get(0).getSize());
        list1.getNextAvailable(7);
        assertEquals("(11,0)", list1.printBlocks());
        list1.freeUpSpace(0, 4);
        System.out.println(list1.printBlocks());
        assertEquals("(0,4)", list1.printBlocks());
        list1.expand(3, list1.getPoolLenght());
        assertEquals("(0,4) -> (11,3)", list1.printBlocks());
        list1.getNextAvailable(3);
        assertEquals("(0,4)", list1.printBlocks());
        list1.expand(5, list1.getPoolLenght());
        list1.getNextAvailable(3);
        assertEquals("(3,1) -> (14,5)", list1.printBlocks());

    }

    /**
     * List expansion test second case
     */
    public void testExpansionB() {
        list.getNextAvailable(6);
        list.expand(5, 6);
        assertEquals(6, list.getNextAvailable(3));
        System.out.println(list.printBlocks());
    }

    /**
     * Empty list test case
     */
    public void testEmpty() {
        System.out.println(list.printBlocks());
        assertFalse(list.isEmpty());
    }

    /**
     * Make free space
     */
    public void testFreeSpace() {
        list.remove(0);
        // (2,3) -> (8,12) -> (18,20)
        list.add(0, new Block(2, 3));
        list.add(1, new Block(8, 12));
        list.add(2, new Block(18, 20));
        // (0,3) -> (8,12) -> (18,20)
        list.freeUpSpace(0, 2);
        assertEquals(0, list.get(0).getX());
        assertEquals(3, list.get(0).getY());
        // (0,3) -> (8,20)
        list.freeUpSpace(13, 5);
        assertEquals(8, list.get(1).getX());
        assertEquals(20, list.get(1).getY());
        assertEquals(2, list.size);
        // (0,3) -> (8,25)
        list.freeUpSpace(21, 5);
        assertEquals(8, list.get(1).getX());
        assertEquals(25, list.get(1).getY());
        // (0,3) -> (8,25) -> (27,31)
        list.freeUpSpace(27, 5);
        assertEquals(27, list.get(2).getX());
        assertEquals(31, list.get(2).getY());
        // (0,3) -> (5,5) -> (8,25) -> (27,31)
        list.freeUpSpace(5, 1);
        assertEquals(5, list.get(1).getX());
        assertEquals(5, list.get(1).getY());
        // (0,3) -> (5,5) -> (7,25) -> (27,31)
        list.freeUpSpace(7, 1);
        assertEquals(7, list.get(2).getX());
        assertEquals(25, list.get(2).getY());
        // (0,3) -> (5,5) -> (7,25) -> (27,31) -> (35,36)
        list.add(4, new Block(35, 2));
        // (0,3) -> (5,5) -> (7,25) -> (27,33) -> (35,36)
        list.freeUpSpace(32, 2);
        assertEquals(27, list.get(3).getX());
        assertEquals(33, list.get(3).getY());

        list.remove(0);

        list.freeUpSpace(1, 2);

        assertEquals(1, list.get(0).getX());
        assertEquals(2, list.get(0).getY());

    }
}