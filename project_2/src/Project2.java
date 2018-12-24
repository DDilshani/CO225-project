import java.io.IOException; 

public class Project2 {

    public static void main(String[] args) throws IOException {

        String filePath = "stocks.csv";

        StockDatabase stocks = new StockDatabase(filePath);
        Server mainServer = new Server(Server.BASE_PORT, stocks);
        Display disp = new Display(mainServer, stocks);

        mainServer.server_loop();

    }
}
