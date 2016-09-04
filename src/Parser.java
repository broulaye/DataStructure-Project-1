import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
	
	public void parse() {
		
		String fileName = "P1sampleInput.txt";
	    File file = new File(fileName);
	    Scanner reader = null;
	    

	    try {
	        reader = new Scanner(file);

	        String token = reader.nextLine();

	        int offset = token.length()+1;
	        
	        System.out.println(token);
	        
	        //System.out.println(reader.nextLine());

	        while(reader.hasNextLine()) {

	            token = reader.nextLine();

	            String[] line = token.split("");
	            
	            
	        
	        }


	        reader.close();


	    } catch (IOException exception) {
	        exception.printStackTrace();
	    }
		
	}
	
}
