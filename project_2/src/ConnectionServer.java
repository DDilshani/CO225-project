import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ConnectionServer implements Runnable {
    // some constants 
    public static final int WAIT_NAME = 0;
    public static final int WAIT_SYMBOL = 1;
    public static final int ALLOW_BID = 2;

    /*
    public static final String WAIT_AUTH_MSG = "Registration Number pls!\n";
    public static final String AUTH_DONE_MSG = "You are authorised to post\n";
    public static final String MSG_POSTED = "Your message posted\n";
    */

    private Socket mySocket;
    private int currentState;
    private String clientName;
    private String clientSymbol;
    private Server mainServer;

    private static StockDatabase stock = null;

    public ConnectionServer(Server mainServer, StockDatabase stock) {
        this.mySocket = null;
        this.currentState = WAIT_NAME;
        this.clientName = "User";
        this.clientSymbol = null;
        this.mainServer = mainServer;


        this.stock = stock;
    }

    public boolean handleConnection(Socket socket) {
        this.mySocket = socket;
        Thread newThread = new Thread(this);
        newThread.start();
        return true;
    }

    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(mySocket.getOutputStream()));
            String line, reply;

            for (line = in.readLine();
                 line != null && !line.equals("quit");
                 line = in.readLine()) {

                switch (currentState) {
                    case WAIT_NAME:
                        if (line.length() != 0) {
                            // userName = line;
                            this.clientName = line;

                            reply = "Enter the Symbol you wish to bid:\n";
                            currentState = WAIT_SYMBOL;
                        } else {
                            //mainServer.postMSG("Please enter an non-empty string as your name: ");
                            reply = "Please enter an non-empty string as your name:\n";
                        }
                        break;

                    case WAIT_SYMBOL:

                        // check is there exists a symbol equal to the 'line'
                        // If the provided Symbol is found the server should reply back with the
                        // current cost of the security or -1 to indicate that the Symbol is invalid.

                        if (line.trim().compareTo("AAL") == 0) {
                            int cost = 100;
                            clientSymbol = line.trim();

                            reply = "You are now able to bid for " + clientSymbol + "\n";
                            reply += "Current stock cost is " + cost + "\n";

                            currentState = ALLOW_BID;

                        } else {
                            reply = "-1\n";
                        }

                        mainServer.postMSG(this.clientName + " > " + line);
                        break;

                    case ALLOW_BID:

                        reply = "You said: " + line + "\n";
                        mainServer.postMSG(this.clientName + " > " + clientSymbol + " > " + line);
                        break;
                    default:
                        System.out.println("Undefined state");
                        reply = "Undefined state";
                        return;
                }
                out.print(reply);
                out.flush();
            }
            out.close();
            in.close();
            this.mySocket.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

    
    

