/**
 * Implementation of a song and artist storage system using a
 * memory manager
 * <p>
 * The class containing the main method.
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 * <p>
 * The class containing the main method.
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 * <p>
 * The class containing the main method.
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 * <p>
 * The class containing the main method.
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 * <p>
 * The class containing the main method.
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 * <p>
 * The class containing the main method.
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 * <p>
 * The class containing the main method.
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 */

/**
 * The class containing the main method.
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * Main class: entry point
 */
public class Memman {
    /**
     * @param args
     *     Command line parameters
     */
    public static void main(String[] args) {
        int hashSize = -1;
        int blockSize = -1;
        String fileName = "|";
        if (args == null || args.length < 3) {
            System.out.println("Usage: Memman {initial-hash-size} {block-size} {command-file}");
            return;
        }
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    hashSize = Integer.parseInt(args[i]);
                    break;
                case 1:
                    blockSize = Integer.parseInt(args[i]);
                    break;
                case 2:
                    fileName = args[i];
                    break;
            }
        }

        try {
            // initialize processor
            Processor processor = new Processor(hashSize, blockSize, fileName);
            // process commands
            processor.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
