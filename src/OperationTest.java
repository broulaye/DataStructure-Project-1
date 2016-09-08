import student.TestCase;

/**
 * This class test the enum Operation class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 */

public class OperationTest extends TestCase {

    private Operation newOperation;

    /**
     * Set up our variable
     */
    public void setUp() {
        newOperation = Operation.insert;
    }

    /**
     * Test toString method
     */
    public void testtoString() {
        assertEquals("insert", newOperation.toString());
        newOperation = Operation.print;
        assertEquals("print", newOperation.toString());
        newOperation = Operation.remove;
        assertEquals("remove", newOperation.toString());
    }
}
