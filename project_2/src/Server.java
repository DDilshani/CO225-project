import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    //private static LinkedList<String> msgs;
    public static final int BASE_PORT = 2000;

    private ServerSocket serverSocket = null;
    private static StockDatabase stocks = null;

    public Server(int socket, StockDatabase stocks) {
        try {
            this.serverSocket = new ServerSocket(socket);
            this.stocks = stocks;
            //msgs = new LinkedList<String>();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /*public String getMSG() {
        //if (!msgs.isEmpty()) return msgs.remove();
        return null;
    }*/

    public synchronized void postMSG(String str) {
        // I can override and make function synchronized
        //msgs.add(str);
        System.out.println(str);
    }

    public void server_loop() {
        try {
            while (true) {
                Socket socket = this.serverSocket.accept();
                ConnectionServer worker = new ConnectionServer(this, stocks);
                worker.handleConnection(socket);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
