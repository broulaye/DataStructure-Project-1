import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class parse a list of commands and their values and create a Command
 * object for each commands and create a Commands object that contains a list of
 * commands.
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/04/2016
 */

public class Parser {

    /**
     * parse the file name fileN
     *
     * @param fileN name of the file to be parsed
     * @return a list of commands
     */
    public static Commands parse(String fileN) {

        // create new file using the given file name
        File file = new File(fileN);

        // create a scanner object
        Scanner reader;

        // create a new Commands object
        Commands commandsList = new Commands();
        try {
            reader = new Scanner(file);
            String token;
            // while the file got for line execute the following commands
            while (reader.hasNextLine()) {
                Command command = new Command();
                token = reader.nextLine();
                token = token.trim();
                String s = "";
                String[] value = new String[2];
                String[] line = token.split(" ");
                switch (line[0]) {
                    case "insert":
                        command.setOp(Operation.insert);
                        for (int i = 1; i < line.length; i++) {
                            s += line[i] + " ";
                        }
                        value = s.split("<SEP>");
                        command.setValues(value);
                        break;
                    case "remove":
                        command.setOp(Operation.remove);
                        if (line[1].equals("song")) {
                            command.setTyp(Type.Song);
                        }
                        else if (line[1].equals("artist")) {
                            command.setTyp(Type.Artist);
                        }
                        else {
                            System.out.println("Illegal type: " + line[1]);
                        }

                        for (int i = 2; i < line.length; i++) {
                            s += line[i] + " ";
                        }
                        value[0] = s;
                        command.setValues(value);
                        break;
                    case "print":
                        command.setOp(Operation.print);

                        if (line[1].equals("song")) {
                            command.setTyp(Type.Song);
                        }
                        else if (line[1].equals("artist")) {
                            command.setTyp(Type.Artist);
                        }
                        else if (line[1].equals("blocks")) {
                            command.setTyp(Type.Block);
                        }
                        else {
                            System.out.println("Illegal type: " + line[1]);
                        }

                        value = s.split("");
                        command.setValues(value);
                        break;
                    default:
                        break;
                }
                commandsList.add(command);
            }
            reader.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return commandsList;
    }

}
