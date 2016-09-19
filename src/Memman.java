import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
 * Implementation of a song and artist storage system using a memory manager The
 * class containing the main method.
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 9/6/2016
 */
public class Memman {
    /**
     * @param args
     *            Command line parameters
     */
    public static void main(String[] args) {
        int hashSize;
        int blockSize;
        String inputfileName;
        if (args == null || args.length != 3) {
            System.out.println("Usage: Memman {initial-hash-size} "
                    + "{block-size} {command-file}");
            return;
        }

        hashSize = Integer.parseInt(args[0]);
        blockSize = Integer.parseInt(args[1]);
        inputfileName = args[2];

        try {
            // initialize processor
            Processor processor =
                    new Processor(hashSize, blockSize, inputfileName);
            // process commands
            processor.process();

            File file = new File("output.txt");

            // create a scanner object
            Scanner reader;

            try {
                reader = new Scanner(file);

                String token;

                // while the file got for line execute the following commands
                while (reader.hasNextLine()) {

                    token = reader.nextLine();
                    System.out.println(token);
                }
                reader.close();
            }
            catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
