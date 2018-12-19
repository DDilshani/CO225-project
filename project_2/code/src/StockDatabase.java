import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Company{
    String symbol, name, bidBy;
    double price;

    public Company(String symbol, String name, double price,String bidBy){
    this.symbol=symbol;
    this.name=name;
    this.price=price;
    this.bidBy=bidBy;
    }

    public String toString() {
        return  symbol + " "+name+ " "+price+ " "+bidBy;
    }

    public String getName(){
        return name;
    }

    public String getSymbol(){
        return symbol;
    }

    public double getPrice(){
        return price;
    }

    public String getBidBy(){
        return name;
    }

    public void setPrice(double p){
        price=p;
    }

    public boolean newBid(String bidder, double p){
        if (p<=price){
            return false;
        }else{
            bidBy=bidder;
            price=p;
            return true;
        }
    }
}


class History{
    String symbol, bidBy;
    double price;
    Date time;

    public History(String symbol, double price,String bidBy,Date time){
    this.symbol=symbol;
    this.price=price;
    this.bidBy=bidBy;
    this.time=time;
    }

    public String toString() {
        return  symbol + " "+ " "+price+ " "+bidBy+" "+time;
    }
}

public class StockDatabase {

    public StockDatabase(String filePath) {
        readCSV(filePath);
    }

    private void readCSV(String filePath) {
        Scanner sc = new Scanner(System.in); 
        String csvfile = "stocks.csv"; 
        String line = "";

        LinkedList<History> myHistory = new LinkedList<History>();
        Map<String, Company> stockMarket = new Hashtable<String, Company>(); //create and map hash table

        try (BufferedReader br = new BufferedReader(new FileReader(csvfile))) {
            br.readLine(); //read first line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                stockMarket.put(data[0],new Company(data[0],data[1],Double.parseDouble(data[2]),new String())); // enter data to the hash table
                myHistory.add(new History(data[0],Double.parseDouble(data[2]),new String(),new Date()));

            }

        }catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed CSV file");
            e.printStackTrace();
        }
    }

    public void printAllCompanies() {
        Iterator<Integer> iterator = stockMarket.keySet().iterator();//iterate the hash table
        while(iterator.hasNext()){
            Company com = stockMarket.get(iterator.next());
        }

    }

    public Boolean isSymbolExists(String symbol) {
        // Need to code here
        return true;
    }



    //public getCompany(){ }

}
