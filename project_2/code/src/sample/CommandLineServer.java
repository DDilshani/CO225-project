public class CommandLineServer { 
    public static void main(String [] args) { 
	StudentDB allowedUsers = new StudentDB("e12.csv","Student RegNo","Name");
	MainServer mainServer = new MainServer(MainServer.BASE_PORT,
					       allowedUsers); 
	mainServer.server_loop(); 
    }
}
	