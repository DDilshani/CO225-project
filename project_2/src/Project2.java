import java.io.IOException; 

public class Project2 {

    public static void main(String[] args) throws IOException {

        //String filePath = "C:/java/stocks.csv";
        String filePath = "stocks.csv";

        //creating instance of StockDatabase
        StockDatabase stocks = new StockDatabase(filePath);

        //creating instance of Server
        Server mainServer = new Server(Server.BASE_PORT, stocks);

        //creating instance of Display
        Display disp = new Display(mainServer, stocks);

        mainServer.server_loop();

    }
}
