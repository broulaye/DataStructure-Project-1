import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class parse a list of commands and their values and create a Command object for each commands and create a 
 * Commands object that contains a list of commands.
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/04/2016
 * 
 */

public class Parser {
	
	
	/**
	 * parse the file name fileN
	 * @param fileN name of the file to be parsed
	 */
	public static void parse(String fileN) {
		
		//store the file name
		String fileName = fileN;
		
		//create new file using the given file name
	    File file = new File(fileName);
	    
	    //create a scanner object
	    Scanner reader = null;
	    
	    //create a new Commands object
	    Commands commandsList = new Commands();
	    

	    try {
	        reader = new Scanner(file);

	        String token = "";
	        
	        //while the file got for line execute the following commands
	        while(reader.hasNextLine()) {

	        	Command command = new Command();
	        	
	            token = reader.nextLine();
	            
	            String s = "";
	            
	            String[] value = new String[2];
	            
	            System.out.println(token);

	            String[] line = token.split(" ");
	            
	            switch(line[0]) {
	            	case "insert":
	            		command.setOp(Operation.insert);
	            		
	            		for(int i =1; i < line.length; i++) {
	            			s += line[i] + " ";
	            		}
	            		
	            		value = s.split("<SEP>");
	            		command.setValues(value);
	            		break;
	            	case "remove":
	            		
	            		command.setOp(Operation.remove);
	            		
	            		if(line[1].equals("song")) {	            			
	            			command.setTyp(Type.Song);	            			
	            		}
	            		
	            		else if(line[1].equals("artist")) {
	            			command.setTyp(Type.Artist);
	            		}
	            		
	            		else {
	            			System.out.println("Illegal type");
	            		}
	            		
	            		for(int i = 2; i < line.length; i++) {
	            			s += line[i] + " ";
	            		}
	            		
	            		value[0] = s;
	            		command.setValues(value);
	            		
	            		break;
	            	case "print":
	            		command.setOp(Operation.print);
	            		
	            		if(line[1].equals("song")) {
	            			command.setTyp(Type.Song);
	            		}
	            		else if(line[1].equals("artist")) {
	            			command.setTyp(Type.Artist);
	            		}
	            		else if(line[1].equals("blocks")) {
	            			command.setTyp(Type.Block);
	            		}
	            		else {
	            			System.out.println("Illegal type");
	            		}
	            		
	            		
	            		for(int i =2; i < line.length; i++) {
	            			s += line[i] + " ";
	            		}
	            		
	            		value = s.split("");
	            		command.setValues(value);
	            		
	            		break;
	            	default:
	            		System.out.println("Illegal Operation");
	            		break;
	            
	            }
	            
	            System.out.println("----------------------------------------------------");
	       
	            System.out.println(command);
	            
	            System.out.println("----------------------------------------------------");
	        
	            commandsList.add(command);
	            
	        }


	        reader.close();


	    } catch (IOException exception) {
	        exception.printStackTrace();
	    }
		
	}
	
	/**
	 * main method just for testing purposes 
	 * @param args contain file name
	 */
	public static void main(String args[]) {
		
		parse("P1sampleInput.txt");
	}
	
}
