import student.TestCase;

/**
 * This class test the command class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
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
        String[] values = { "Lil Wayne", "A Mili" };
        command2 = new Command(Operation.insert, null, values);
    }

    /**
     * Tests getOp method
     */
    public void testgetOp() {
        assertEquals(command2.getOp(), Operation.insert);
    }

    /**
     * Tests setOp method
     */
    public void testsetOp() {
        command1.setOp(Operation.print);
        assertEquals(command1.getOp(), Operation.print);
    }

    /**
     * Tests getTyp method
     */
    public void testgetTyp() {
        command1.setTyp(Type.Artist);
        assertEquals(command1.getTyp(), Type.Artist);

    }

    /**
     * Tests setTyp method
     */
    public void testsetTyp() {
        command2.setTyp(Type.Block);
        assertEquals(command2.getTyp(), Type.Block);
    }

    /**
     * This tests getValues method
     */
    public void testgetValues() {
        String[] values = { "Lil Wayne", "A Mili" };
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], command2.getValues()[i]);
        }

    }

    /**
     * This tests the setValues method
     */
    public void testsetValues() {
        String[] values = { "Lil Wayne", "A Mili" };
        command1.setValues(values);
        for (int i = 0; i < values.length; i++) {
            assertEquals(command1.getValues()[i], command2.getValues()[i]);
        }

    }

    /**
     * This tests the toString method
     */
    public void testtoString() {
        assertEquals(command1.toString(), "");
        assertEquals(command2.toString(), "insert Lil Wayne A Mili");
        command2.setTyp(Type.Artist);
        assertEquals(command2.toString(), "insert artist Lil Wayne A Mili");
    }

}
