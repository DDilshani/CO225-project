import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StockDatabase {

    // make this static ???
    // private ArrayList<Contact> contactList = new ArrayList<>();

    public StockDatabase(String filePath) {
        readCSV(filePath);
    }

    private void readCSV(String filePath) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();  // read the header line

            String[] values;
            String symbol, securityName, price;

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                values = line.split(",");

                symbol = values[0];
                securityName = values[1];
                price = values[2];

                //System.out.println(symbol + "\t\t" + price + "\t\t" + securityName);

                // Add them to a suotable collection
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Malformed CSV file");
            e.printStackTrace();
        }
    }

    public void printAllCompanies() {
        // Need to  code
    }

    public Boolean isSymbolExists(String symbol) {
        // Need to code here
        return true;
    }



    //public getCompany(){ }
}
