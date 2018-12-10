public class Project2 {
    public static void main(String[] args) {

        //StudentDB allowedUsers = new StudentDB("e12.csv","Student RegNo","Name");

        Server mainServer = new Server(Server.BASE_PORT);
        mainServer.server_loop();
    }
}
