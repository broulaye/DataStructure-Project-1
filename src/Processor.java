import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * This class process the commands and write
 * the outputs to a file
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
     * Constructor
     */
    public Processor(int hashSize, int BlockSize, String fileName) throws Exception {
        this.commands = Parser.parse(fileName);
        memoryManager = new MemManager(BlockSize);
        this.songHashTable = new Hash(hashSize, memoryManager);
        this.artistHashTable = new Hash(hashSize, memoryManager);

    }

    /**
     * @throws Exception
     */
    public void process() throws Exception {
        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            LinkedList<Command> list = commands.getCommandList();
            for (Command command : list) {
                switch (command.getOp()) {
                    case insert:
                        insert(command.getValues()[0], command.getValues()[1], writer);
                        break;
                    case remove:
                        remove(command.getTyp(), command.getValues()[0], writer);
                        break;
                    case print:
                        printContent(command.getTyp(), writer);
                        break;

                }
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     *
     * @param type
     * @param writer
     */
    private void printContent(Type type, PrintWriter writer) {
        switch (type) {
            case Song:
                writer.print(songHashTable.printTable());
                writer.println("total songs: "+ songHashTable.getElement());
                break;
            case Artist:
                writer.print(artistHashTable.printTable());
                writer.println("total artists: "+ artistHashTable.getElement());
                break;
            case Block:
                writer.println(memoryManager.dump());
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param what
     * @param str
     * @param writer
     */
    private void remove(Type what, String str, PrintWriter writer) {
        str = str.trim();
        switch (what) {
            case Song:
                if (songHashTable.removeString(str)) {
                    writer.println("|" + str + "| is removed from the song database.");
                }
                else {
                    writer.println("|" + str + "| does not exist in the song database.");
                }
                break;
            case Artist:
                if(artistHashTable.removeString(str)) {
                    writer.println("|" + str + "| is removed from the artist database.");
                }
                else {
                    writer.println("|" + str + "| does not exist in the artist database.");
                }
                break;
        }
    }

    /**
     *
     * @param artist
     * @param song
     * @param writer
     * @throws Exception
     */
    private void insert(String artist, String song, PrintWriter writer) throws Exception {
        artist = artist.trim();
        song = song.trim();
        if (artistHashTable.insertString(artist, writer)) {
            writer.println("|" + artist + "| is added to the artist database.");
        }
        // TODO: (Broulaye) what to do if insertion fails in case of duplicates
        if (songHashTable.insertString(song, writer)) {
            writer.println("|" + song + "| is added to the song database.");
        }


    }


}
