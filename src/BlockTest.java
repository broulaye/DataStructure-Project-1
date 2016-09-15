import student.TestCase;

/**
 * This class test the block class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/14/2016
 */
public class BlockTest extends TestCase {

    private Block newBlock;

    /**
     * Set up our variable
     */
    public void setUp() {
        newBlock = new Block(0, 0);
    }

    /**
     * Test get size
     */
    public void testgetSize() {
        assertEquals(1, newBlock.getSize());
    }

    /**
     * test get x
     */
    public void testgetX() {
        assertEquals(0, newBlock.getX());
    }

    /**
     * test get y
     */
    public void testgetY() {
        assertEquals(0, newBlock.getY());
    }

    /**
     * test set y
     */
    public void testsetY() {
        newBlock.setY(1);
        assertEquals(1, newBlock.getY());
    }

    /**
     * Test toString method
     */
    public void testtoString() {
        assertEquals("(0,1)", newBlock.toString());
    }

}
