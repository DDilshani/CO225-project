import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int BASE_PORT = 2000;  // do not change
    private ServerSocket serverSocket = null;  // server Socket for main server


    public Server(int socket) {
        try {
            this.serverSocket = new ServerSocket(socket);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isAuthorized(String regNo) {
        return false;   //this.allowedUsers.findName(regNo) != null;
    }

    public String getName(String regNo) {
        // should these be synchronized?
        //return this.allowedUsers.findName(regNo);
        return "";
    }


    public void postMSG(String msg) {
        // all threads print to same screen
        System.out.println(msg);
    }

    public String authorizedOnce(String a) {
        // need to implement this.
        return null;
    }

    public void server_loop() {
        try {
            while (true) {
                Socket socket = this.serverSocket.accept();
                ConnectionServer worker = new ConnectionServer(this);
                worker.handleConnection(socket);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
