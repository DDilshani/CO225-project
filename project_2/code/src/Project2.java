public class Project2 {
    public static void main(String[] args) {

        //StudentDB allowedUsers = new StudentDB("e12.csv","Student RegNo","Name");

        String filePath = "C:/java/stocks.csv";
        //String filePath = "stocks.csv";

        StockDatabase stocks = new StockDatabase(filePath);
        Server mainServer = new Server(Server.BASE_PORT, stocks);

        
        mainServer.server_loop();
    }
}
