import student.TestCase;

/**
 * This class test the Handle class
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 *
 */
public class HandleTest extends TestCase {

    private Handle handle1;
    private  Handle handle2;
    
    /**
     * set up local variable
     */
    public void setUp() {
        handle1 = new Handle();
        handle2 = new Handle(1);
    }
    
    /**
     * Test get method
     */
    public void testgetLocation() {
        assertEquals(1, handle2.getLocation());
    }
    
    /**
     * Test set method
     */
    public void testsetLocation() {
        handle1.setLocation(3);
        assertEquals(3, handle1.getLocation());
    }
    
    /**
     * test toString method
     */
    public void testtoString() {
        assertEquals("1", handle2.toString());
    }
    
}
