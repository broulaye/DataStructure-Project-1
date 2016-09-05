import student.TestCase; 


/**
 * This class test the command class
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 *
 */


public class CommandTest extends TestCase {
	
	/**
     * the list we will use
     */
    private Command command1;
    private Command command2;

    /**
     * run before every test case
     */

    public void setUp() {
        command1 = new Command();
        String[] values = {"Lil Wayne", "A Mili"};
        command2 = new Command(Operation.insert, null, values);
    }

    /**
     * Tests getOp method
     */
    public void testgetOp() {
       
    }

    /**
     * Tests setOp method
     */
    public void testsetOp() {
        
    }

    /**
     * Tests getTyp method
     */
    public void testgetTyp() {
        

    }

    /**
     * Tests setTyp method
     */
    public void testsetTyp() {
        
    }

    /**
     * This tests getValues method
     */
    public void testgetValues() {
        
    }

    /**
     * This tests the setValues method
     */
    public void testsetValues() {
        
    }

    /**
     * This tests the toString method
     */
    public void testtoString() {
        
    }

	
}
