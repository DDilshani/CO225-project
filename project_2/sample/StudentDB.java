import java.io.*;
import java.util.*;

class StudentDB { 

    private Map<String, String> classList; 
    private String [] fields; 

    public StudentDB(String cvsFile, String key, String val)  { 
	FileReader fileRd=null; 
	BufferedReader reader=null; 

	try { 
	    fileRd = new FileReader(cvsFile); 
	    reader = new BufferedReader(fileRd); 

	    /* read the CSV file's first line which has 
	     * the names of fields. 
	     */
	    String header = reader.readLine(); 
	    fields = header.split(",");// keep field names 

	    // find where the key and the value are 
	    int keyIndex = findIndexOf(key); 
	    int valIndex = findIndexOf(val); 

	    if(keyIndex == -1 || valIndex == -1) 
		throw new IOException("CVS file does not have data"); 
	    // note how you can throw a new exception 

	    // get a new hash map
	    classList = new HashMap<String, String>(); 

	    /* read each line, getting it split by , 
	     * use the indexes to get the key and value 
	     */
	    String [] tokens; 
	    for(String line = reader.readLine(); 
		line != null; 
		line = reader.readLine()) { 
		tokens = line.split(","); 
		classList.put(tokens[keyIndex], tokens[valIndex]); 
	    }
	    
	    if(fileRd != null) fileRd.close();
	    if(reader != null) reader.close();
	    
	    // I can catch more than one exceptions 
	} catch (IOException e) { 
	    System.out.println(e);
	    System.exit(-1); 
	} catch (ArrayIndexOutOfBoundsException e) { 
	    System.out.println("Malformed CSV file");
	    System.out.println(e);
	}
    }

    private int findIndexOf(String key) { 
	for(int i=0; i < fields.length; i++) 
	    if(fields[i].equals(key)) return i; 
	return -1; 
    }
	
    // public interface 
    public String findName(String key) { 
	return classList.get(key); 
    }

}
	    