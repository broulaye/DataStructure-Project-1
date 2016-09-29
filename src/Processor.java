import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * This class process the commands and write the outputs to a file
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 */
public class Processor {

    private Commands commands;
    private Hash songHashTable;
    private Hash artistHashTable;
    private MemManager memoryManager;

    /**
     * Constructor that set the fields to provided values
     * 
     * @param hashSize
     *            represent the size of the hash table
     * @param blockSize
     *            represent the size of the memory manager
     * @param fileName
     *            represent the file to be parsed
     * @throws Exception
     *             exception is thrown if the parsing failed
     */
    public Processor(int hashSize, int blockSize, String fileName)
            throws Exception {
        this.commands = Parser.parse(fileName);
        memoryManager = new MemManager(blockSize);
        this.songHashTable = new Hash(hashSize, memoryManager, "song");
        this.artistHashTable = new Hash(hashSize, memoryManager, "artist");

    }

    /**
     * @throws Exception
     *             various exception from nested calls
     */
    public void process() throws Exception {
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            LinkedList<Command> list = commands.getCommandList();
            for (Command command : list) {
                switch (command.getOp()) {
                    case insert:
                        insert(command.getValues()[0], command.getValues()[1],
                                writer);
                        break;
                    case remove:
                        remove(command.getTyp(), command.getValues()[0],
                                writer);
                        break;
                    case print:
                        printContent(command.getTyp(), writer);
                        break;
                    default:
                        break;

                }
            }
            writer.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * print content of given type of database
     *
     * @param type
     *            requested type of database
     * @param writer
     *            used for output
     */
    private void printContent(Type type, PrintWriter writer) {
        switch (type) {
            case Song:
                writer.print(songHashTable.printTable());
                break;
            case Artist:
                writer.print(artistHashTable.printTable());
                break;
            case Block:
                writer.println(memoryManager.dump());
                break;
            default:
                break;
        }
    }

    /**
     * @param what
     *            type to be removed
     * @param str
     *            string to be removed
     * @param writer
     *            for status report
     */
    private void remove(Type what, String str, PrintWriter writer) {
        str = str.trim();
        if (what == Type.Song) {
            if (songHashTable.removeString(str)) {
                writer.println(
                        "|" + str + "| is removed from the song database.");
            }
            else {
                writer.println(
                        "|" + str + "| does not exist in the song database.");
            }
        }
        else if (what == Type.Artist) {
            if (artistHashTable.removeString(str)) {
                writer.println(
                        "|" + str + "| is removed from the artist database.");
            }
            else {
                writer.println(
                        "|" + str + "| does not exist in the artist database.");
            }

        }
    }

    /**
     * Insert
     *
     * @param artist
     *            artist name
     * @param song
     *            song title
     * @param writer
     *            used for status output
     * @throws Exception
     *             exception from inner calls
     */
    private void insert(String artist, String song, PrintWriter writer)
            throws Exception {
        artist = artist.trim();
        song = song.trim();
        if (artistHashTable.insertString(artist, writer)) {
            writer.println("|" + artist + "| is added to the artist database.");
        }
        else {
            writer.println("|" + artist
                    + "| duplicates a record already in the artist database.");
        }
        if (songHashTable.insertString(song, writer)) {
            writer.println("|" + song + "| is added to the song database.");
        }
        else {
            writer.println("|" + song
                    + "| duplicates a record already in the song database.");
        }

    }

}