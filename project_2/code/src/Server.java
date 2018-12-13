import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int BASE_PORT = 2000;  // do not change
    private ServerSocket serverSocket = null;  // server Socket for main server
    private StockDatabase stocks = null;     // who are allowed to chat

    public Server(int socket, StockDatabase stocks) {
        try {
            this.serverSocket = new ServerSocket(socket);
            this.stocks = stocks;

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isAuthorized(String regNo) {
        return false;   //this.allowedUsers.findName(regNo) != null;
    }


    public void postMSG(String msg) {
        // all threads print to same screen
        System.out.println(msg);
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
