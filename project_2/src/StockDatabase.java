//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;

class Company {
    // The object for store company details

    //encapsulating instance variables cannot access outsude the class
    private String symbol, name, bidBy;
    private double price;

    //making a constructor
    public Company(String symbol, String name, double price, String bidBy) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.bidBy = bidBy;
    }

    public String toString() {
        return symbol + " " + name + " " + price + " " + bidBy;
    }

    //encapsulation - getName method
    public String getName() {
        return name;
    }

    //encapsulation - getSymbol method
    public String getSymbol() {
        return symbol;
    }

    //encapsulation - getPrice method
    public double getPrice() {
        return price;
    }

    //encapsulation - getBidBy method
    public String getBidBy() {
        return bidBy;
    }

    //encapsulation - setPrice method
    public void setPrice(double price) {
        this.price = price;
    }

    //encapsulation - setBidBy method
    public void setBidBy(String bidBy) {
        this.bidBy = bidBy;
    }

    //update the bid price and bidder if the new_price > current_price
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

    //encapsulating instance variables cannot access outsude the class
    private String symbol, bidBy;
    private double price;
    private Date time;

    //making a constructor
    public History(String symbol, double price, String bidBy, Date time) {
        this.symbol = symbol;
        this.price = price;
        this.bidBy = bidBy;
        this.time = time;
    }

    public String toString() {
        return symbol + " " + " " + price + " " + bidBy + " " + time;
    }

    //encapsulation - getSymbol method
    public String getSymbol() {
        return symbol;
    }
}

public class StockDatabase {

    //linkedlist to store the history or the data that change
    private static LinkedList<History> history;

    //hashtable to store the details of the csv file
    private static Map<String, Company> stockMarket;

    public StockDatabase(String filePath) {
        readCSV(filePath);
    }

    //encapsulation - getName method
    private void readCSV(String filePath) {
        String line = "";

        history = new LinkedList<History>(); //creating instance of linkedList
        stockMarket = new Hashtable<String, Company>(); //creating instance of hashtable

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); //read first line
            while ((line = br.readLine()) != null) {

                // append data into each list
                String[] data = line.split(",");
                stockMarket.put(data[0], new Company(data[0], data[1], Double.parseDouble(data[2]), ""));//storing data into stockMarket hashtable
                history.add(new History(data[0], Double.parseDouble(data[2]), "<starting-price>", new Date()));//storing data into history linkedList
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
        //print hashtable
        for (String name : stockMarket.keySet()) {
            String key = name.toString();
            String value = stockMarket.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void printHistory() {
        //print linkedlist
        System.out.println(history.toString().replace(",", "\n"));
    }

    // Utility Functions ----------------------------------------------------------------------------------------

    //update the bid price and bidder if the new_price > current_price in the stockMarket hashtable
    public boolean newBidEntry(String symbol, String bidBy, double bidVal) {
        Company c = stockMarket.get(symbol);
        c.setPrice(bidVal);
        c.setBidBy(bidBy);

        //System.out.println("     " + bidBy);
        stockMarket.replace(symbol, c);
        return true;
    }

    //check whether the symbol is exist in the stockMarket hashtable
    public boolean isSymbolExists(String s) {
        return stockMarket.containsKey(s);
    }

    //add new details to the history linkedList
    public boolean newHistoryRecord(String symbol, double value, String bidBy) {
        history.add(new History(symbol, value, bidBy, new Date()));
        System.out.println("history: " + bidBy + ", " + value + ", " + symbol);
        return true;
    }

    //encapsulation - getCompany method
    public Company getCompany(String symbol) {
        return stockMarket.get(symbol);
    }

    //encapsulation - getPriceOnCompany method
    public double getPriceOnCompany(String symbol) {
        return getCompany(symbol).getPrice();
    }

    //print history of a particular Symbol
    public static void printSymbolHistory(String s) {
        //iterate through the history linkedList
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
