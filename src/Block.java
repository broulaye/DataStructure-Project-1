/**
 * This class represent Tuple object holding a pair of integers
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berther
 * @version 09/11/2016
 *
 */
public class Block {
    /**
     * Start position
     */
    private int x;

    /**
     * End position
     */
    private int y;

    /**
     * @param x
     *            first integer
     * @param y
     *            second integer
     */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns size of block
     *
     * @return size of block
     */
    public int getSize() {
        return y - x + 1;
    }

    /**
     * Get the start position
     *
     * @return first integer
     */
    public int getX() {
        return x;
    }

    /**
     * Get the end position
     *
     * @return End position
     */
    public int getY() {
        return y;
    }

    /**
     * Set the End position
     *
     * @param y
     *            new value of y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return formatted string representing object
     */
    public String toString() {
        return "(" + x + "," + (y - x + 1) + ")";
    }
}
