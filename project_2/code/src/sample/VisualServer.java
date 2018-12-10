import java.util.*; 

class VisualServer extends MainServer { 
    private static LinkedList<String> msgs; 

    public VisualServer(int socket, StudentDB user) { 
	super(socket, user); 
	msgs = new LinkedList<String>(); 
    }

    @Override 
    public synchronized void postMSG(String str) { 
	// I can override and make function synchronized 
	msgs.add(str); 
    }

    public String getMSG() { 
	if(!msgs.isEmpty()) return msgs.remove(); 	    
	return null; 
    }
}
	