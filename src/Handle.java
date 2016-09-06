/**
 * This class will represent the handle
 * that will store the location of the
 * values in the mem manager
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 *
 */

public class Handle {
    
    private int location;

    /**
     * Constructor that set up the
     * filed with given values
     * @param location given location
     */
    public Handle(int location) {
        this.location = location;
    }
    
    /**
     * Default that set the field 
     * to a default value
     */
    public Handle() {
        location = 0;
    }

    /**
     * get the location
     * @return the location
     */
    public int getLocation() {
        return location;
    }

    /**
     * set the location to a given value
     * @param location given value
     */
    public void setLocation(int location) {
        this.location = location;
    }
    
    
    /**
     * print the location
     * @return a string representation of the location
     */
    public String toString() {
        return "" + location;
    }
    
    
    
}
