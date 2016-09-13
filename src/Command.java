/**
 * This class represent a command. each command contain an operation value that
 * store what operation the command is executing. each command, beside the
 * insert command, also has a type that specify if the command is being run on a
 * song, artist, or block. finaly each command also as a value that present the
 * argument of the command
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/04/2016
 */
public class Command {

    private Operation op;
    private Type typ;
    private String[] values;

    /**
     * Constructor that set the field to provided values
     *
     * @param op
     *            represent operation
     * @param typ
     *            represent type
     * @param values
     *            represent values
     */
    public Command(Operation op, Type typ, String[] values) {
        this.op = op;
        this.typ = typ;
        this.values = values;
    }

    /**
     * Default constructor set fields to default values
     */
    public Command() {
        op = null;
        typ = null;
        values = null;
    }

    /**
     * Get the operation of the command
     *
     * @return the command operation
     */
    public Operation getOp() {
        return op;
    }

    /**
     * Set the operation of the command
     *
     * @param op
     *            value the operation is going to be set to
     */
    public void setOp(Operation op) {
        this.op = op;
    }

    /**
     * Get the type of the command
     *
     * @return the command type
     */
    public Type getTyp() {
        return typ;
    }

    /**
     * Set the type of the command
     *
     * @param typ
     *            value the type is going to be set to
     */
    public void setTyp(Type typ) {
        this.typ = typ;
    }

    /**
     * get the values of the command
     *
     * @return the values of the command
     */
    public String[] getValues() {
        return values;
    }

    /**
     * Set the values of the command
     *
     * @param values
     *            value the values is going to be set to
     */
    public void setValues(String[] values) {
        this.values = values;
    }

    /**
     * get a string representaion of the command
     *
     * @return a string representation of the command
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (op != null) {
            s.append(op.toString()).append(" ");
        }

        if (typ != null) {
            s.append(typ.toString()).append(" ");
        }

        if (values != null) {

            for (int i = 0; i < values.length; i++) {
                s.append(values[i]);
                if (i != values.length - 1) {
                    s.append(" ");
                }
            }

        }

        return s.toString();

    }
}
