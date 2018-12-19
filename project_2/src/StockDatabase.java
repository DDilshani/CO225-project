import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;

class Company {
    String symbol, name, bidBy;
    double price;

    public Company(String symbol, String name, double price, String bidBy) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.bidBy = bidBy;
    }

    public String toString() {
        return symbol + " " + name + " " + price + " " + bidBy;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getBidBy() {
        return name;
    }

    public void setPrice(double p) {
        price = p;
    }

    public boolean newBid(String bidder, double p) {
        if (p <= price) {
            return false;
        } else {
            bidBy = bidder;
            price = p;
            return true;
        }
    }
}

class History {
    String symbol, bidBy;
    double price;
    Date time;

    public History(String symbol, double price, String bidBy, Date time) {
        this.symbol = symbol;
        this.price = price;
        this.bidBy = bidBy;
        this.time = time;
    }

    public String toString() {
        return symbol + " " + " " + price + " " + bidBy + " " + time;
    }
}

public class StockDatabase {

    LinkedList<History> myHistory;
    Map<String, Company> stockMarket;

    public StockDatabase(String filePath) {
        readCSV(filePath);
    }

    private void readCSV(String filePath) {

        String line = "";

        myHistory = new LinkedList<History>();
        stockMarket = new Hashtable<String, Company>(); //create and map hash table

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); //read first line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                stockMarket.put(data[0], new Company(data[0], data[1], Double.parseDouble(data[2]), new String())); // enter data to the hash table
                myHistory.add(new History(data[0], Double.parseDouble(data[2]), new String(), new Date()));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed CSV file");
            e.printStackTrace();
        }
    }

    public void printAllCompanies() {

        for (String name : stockMarket.keySet()) {
            String key = name.toString();
            String value = stockMarket.get(name).toString();
            System.out.println(key + " " + value);
        }

    }

    public void printHistory() {
        System.out.println(myHistory.toString().replace(",", "\n"));
    }

    public Boolean isSymbolExists(String s) {
        return stockMarket.containsKey(s);
    }

    public boolean newHistoryRecord(String symbol, double value,String bidBy) {
        myHistory.add('symbol',value,'bidBy',new Date());
        return true;
    }

    


    //public getCompany(){ }

}
