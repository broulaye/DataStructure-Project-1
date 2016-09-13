/**
 * This class is an enum representation of the different type the command can
 * have
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/04/2016
 */
public enum Type {
    /**
     * Represent the different types
     */
    Song, Artist, Block;

    /**
     * Get a string representaion of the type
     *
     * @return a string representation of the type
     */
    public String toString() {
        if (this == Song) {
            return "song";
        }
        else if (this == Artist) {
            return "artist";
        }
        else {
            return "blocks";
        }
    }

}
