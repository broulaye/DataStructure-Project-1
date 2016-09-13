import java.util.LinkedList;

/**
 * This class store a list of commands
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/04/2016
 */
public class Commands {

    private LinkedList<Command> commandList;

    /**
     * Default constructor that create a default linkedlist of commands
     */
    public Commands() {
        commandList = new LinkedList<>();
    }

    /**
     * Get the current list of command
     *
     * @return a list of command
     */
    public LinkedList<Command> getCommandList() {
        return commandList;
    }

    /**
     * set the commandlist to a new list provided
     *
     * @param commandList
     *            new list to be used
     */
    public void setCommandList(LinkedList<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * add a new command to the list
     *
     * @param c
     *            command to be added
     */
    public void add(Command c) {
        commandList.add(c);
    }

}
