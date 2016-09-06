/**
 * Created by berth on 9/6/2016.
 */
public class Helper {
    /**
     * Resizes
     *             (array to new size)
     * @param arr
     *            (array to be resized)
     * @param newSize
     *            (new size)
     * @return new array
     */
    public static String[] resizeArray(String[] arr, int newSize) {
        String[] newArray = new String[newSize];
        for (int it = 0; it < arr.length; it++) {
            if (arr[it] != null) {
                newArray[it] = arr[it];
            }
        }
        return newArray;
    }
    public static byte[] resizeArray(byte[] bytes, int newSize) {
        byte[] newArray = new byte[newSize];
        for (int it = 0; it < bytes.length; it++) {
            newArray[it] = bytes[it];
        }
        return newArray;
    }
    /**
     * Tuple object holding a pair of integers
     */
    public static class Tuple {
        /**
         * Start position
         */
        private int x;



        /**
         * End position
         */
        private int y;

        /**
         * @param x first integer
         * @param y second integer
         */
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Get the start position
         * @return first integer
         */
        public int getX() {
            return x;
        }
        /**
         * Get the end position
         * @return End position
         */
        public int getY() {
            return y;
        }

        /**
         * Set the End position
         * @param y
         */
        public void setY(int y) {
            this.y = y;
        }
        /**
         *
         * @return formatted string representing object
         */
        public String toString() {
            StringBuilder string = new StringBuilder();
            string.append("(").append(x).append(",").append(y - x + 1).append(")");
            return string.toString();
        }
    }
}
