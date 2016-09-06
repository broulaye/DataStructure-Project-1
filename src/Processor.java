import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * This class process the commands and write
 * the outputs to a file
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 *
 */
public class Processor {
	
	private Commands commands;
	private Hash songHashTable;
	private Hash artistHashTable;
	private MemManager memoryManager;
	
	/**
	 * Constructor
	 */
	public Processor(int hashSize, int BlockSize, Commands commandList) throws Exception {
		this.commands = commandList;
        memoryManager = new MemManager(BlockSize);
		this.songHashTable = new Hash(hashSize, memoryManager);
		this.artistHashTable = new Hash(hashSize, memoryManager);

	}

    /**
     *
     * @throws Exception
     */
	public void process() throws Exception {
		try {
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			LinkedList<Command> list = commands.getCommandList();
			Command command;
			String[] values;
			for(int i = 0; i < list.size(); i++) {
				command = list.get(i);
				switch(command.getOp()) {
				    case insert:
				    	values = command.getValues();
				    	songHashTable.insertHandle(values[0]);
				    	writer.println("|" + values[0] + "| is added to the song database.");
				    	artistHashTable.insertHandle(values[1]);
				    	writer.println("|" + values[0] + "| is added to the artist database.");
				    case remove:
				    	//implement later
				    case print:
				    	switch (command.getTyp()) {
						case Song:
							writer.println("Total Song: " + songHashTable.getElement());
							break;
						case Artist:
							writer.println("Total artists: " + artistHashTable.getElement());
							break;
						case Block:
							//todo later
							break;
						default:
							break;
						}
				    	
				}
			}
			writer.close();
		}
		catch(IOException exception) {
			exception.printStackTrace();
		}
		
	}
	
	
}
