import student.TestCase;

import java.util.LinkedList;

/**
 * This class test the commands class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 */

public class CommandsTest extends TestCase {

    /**
     * the list we will use
     */
    private Commands commandsList;

    /**
     * run before every test case
     */
    public void setUp() {
        commandsList = new Commands();
    }

    /**
     * Tests getCommandList
     */
    public void testgetCommandList() {
        Command command2;
        String[] values = { "Lil Wayne", "A Mili" };
        command2 = new Command(Operation.insert, null, values);
        LinkedList<Command> list = new LinkedList<>();
        list.add(command2);

        commandsList.getCommandList().add(command2);

        assertEquals(list, commandsList.getCommandList());

    }

    /**
     * Tests setCommandList
     */
    public void testsetCommandList() {
        Command command2;
        String[] values = { "Lil Wayne", "A Mili" };
        command2 = new Command(Operation.insert, null, values);
        LinkedList<Command> list = new LinkedList<>();
        list.add(command2);

        commandsList.setCommandList(list);

        assertEquals(list, commandsList.getCommandList());
    }

    /**
     * Tests the add method. Ensures that it adds the commands to the list
     */
    public void testAdd() {
        Command command2;
        String[] values = { "Lil Wayne", "A Mili" };
        command2 = new Command(Operation.insert, null, values);
        LinkedList<Command> list = new LinkedList<>();
        // list.add(command2);

        commandsList.setCommandList(list);
        commandsList.add(command2);

        assertEquals(command2, commandsList.getCommandList().get(0));

    }

}
