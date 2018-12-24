import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;

class Company {
    // The object for store company details

    private String symbol, name, bidBy;
    private double price;

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
        return bidBy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBidBy(String bidBy) {
        this.bidBy = bidBy;
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
    // An object for store transaction details

    private String symbol, bidBy;
    private double price;
    private Date time;

    public History(String symbol, double price, String bidBy, Date time) {
        this.symbol = symbol;
        this.price = price;
        this.bidBy = bidBy;
        this.time = time;
    }

    public String toString() {
        return symbol + " " + " " + price + " " + bidBy + " " + time;
    }

    public String getSymbol() {
        return symbol;
    }
}

public class StockDatabase {

    private static LinkedList<History> history;
    private static Map<String, Company> stockMarket;

    public StockDatabase(String filePath) {
        readCSV(filePath);
    }

    private void readCSV(String filePath) {
        String line = "";

        history = new LinkedList<History>();
        stockMarket = new Hashtable<String, Company>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); //read first line
            while ((line = br.readLine()) != null) {

                // append data into each list
                String[] data = line.split(",");
                stockMarket.put(data[0], new Company(data[0], data[1], Double.parseDouble(data[2]), ""));
                history.add(new History(data[0], Double.parseDouble(data[2]), "<starting-price>", new Date()));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed CSV file");
            e.printStackTrace();
        }
    }

    // Functions for debugging ----------------------------------------------------------------------------------

    public void printAllCompanies() {

        for (String name : stockMarket.keySet()) {
            String key = name.toString();
            String value = stockMarket.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void printHistory() {
        System.out.println(history.toString().replace(",", "\n"));
    }

    // Utility Functions ----------------------------------------------------------------------------------------

    public boolean newBidEntry(String symbol, String bidBy, double bidVal) {
        Company c = stockMarket.get(symbol);
        c.setPrice(bidVal);
        c.setBidBy(bidBy);

        //System.out.println("     " + bidBy);
        stockMarket.replace(symbol, c);
        return true;
    }

    public boolean isSymbolExists(String s) {
        return stockMarket.containsKey(s);
    }

    public boolean newHistoryRecord(String symbol, double value, String bidBy) {
        history.add(new History(symbol, value, bidBy, new Date()));
        System.out.println("history: " + bidBy + ", " + value + ", " + symbol);
        return true;
    }

    public Company getCompany(String symbol) {
        return stockMarket.get(symbol);
    }

    public double getPriceOnCompany(String symbol) {
        return getCompany(symbol).getPrice();
    }


    //----------------------------------------//
    public static void printSymbolHistory(String s) {
        for (int num = 0; num < history.size(); num++) {
            if (history.get(num).getSymbol().equals(s)) {
                System.out.println(history.get(num));
            }
        }
    }

    public ArrayList<String> getSymbolHistory(String s) {
        ArrayList<String> ls = new ArrayList<String>();
        // TODO: This shoule implement more
        for (int num = 0; num < history.size(); num++) {
            if (history.get(num).getSymbol().equals(s)) {
                //System.out.println(history.get(num));
                ls.add(history.get(num).toString());
            }
        }
        return ls;

    }
}
