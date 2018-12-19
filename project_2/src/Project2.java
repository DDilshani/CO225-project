import java.io.IOException;

public class Project2 {

    public static void main(String[] args) throws IOException {
        //StudentDB allowedUsers = new StudentDB("e12.csv","Student RegNo","Name");

        String filePath = "C:/java/stocks.csv";
        //String filePath = "stocks.csv";

        StockDatabase stocks = new StockDatabase(filePath);
        Server mainServer = new Server(Server.BASE_PORT, stocks);

        Display d = new Display(mainServer, stocks);

        //stocks.printHistory();
        //d.draw();
        mainServer.server_loop();


    }
}
