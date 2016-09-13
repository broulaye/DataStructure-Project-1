import student.TestCase;

/**
 * This class test the enum type class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 */

public class TypeTest extends TestCase {

    private Type newType;

    /**
     * Set up our variable
     */
    public void setUp() {
        newType = Type.Artist;
    }

    /**
     * Test toString method
     */
    public void testtoString() {

        assertEquals("artist", newType.toString());
        newType = Type.Block;
        assertEquals("blocks", newType.toString());
        newType = Type.Song;
        assertEquals("song", newType.toString());
    }

}
