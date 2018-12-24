import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Display
        extends JPanel implements ActionListener {

    //JTextArea textArea;
    //encapsulating instance variables cannot access outsude the class
    private Server server;
    private static StockDatabase stock = null;
    private static JFrame frame;

    private final int screenWidth = 600;
    private final int screenHeight = 400;
    private final int noOfLables = 8;

    //VisualServer vServer;
    /*public enum compName {
        FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA, TXN
    }*/

    private JLabel lblStockSelect;
    private JTextArea lblSearchRes;
    private JTextField txtStockSelect;
    private JButton enterButton;

    private JLabel[] lblNames = new JLabel[noOfLables];
    private JLabel[] lblValues = new JLabel[noOfLables];

    private static final String[] lableName = {"FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};

    public Display(Server server, StockDatabase stock) {
        super(new GridBagLayout());
        this.stock = stock;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        draw();

        Timer timer = new Timer(500, this);
        timer.start();

        this.server = server;

    }

    public void draw() {

        // Draw the window
        frame = new JFrame("Auction Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        // Title
        JLabel title = new JLabel("Auction Server");
        title.setBounds(50, 30, 300, 20);
        title.setFont(new Font("Verdana", Font.PLAIN, 24));
        frame.add(title);

        // Search Label
        lblStockSelect = new JLabel("Security:");
        lblStockSelect.setBounds(250, 75, 70, 25);
        //lblStockSelect.setFont(new Font("Verdana", Font.PLAIN, 24));
        frame.add(lblStockSelect);

        // Search Box
        txtStockSelect = new JTextField("");
        txtStockSelect.setBounds(320, 75, 110, 25);
        frame.add(txtStockSelect);

        // Enter Button
        enterButton = new JButton("Enter");
        enterButton.setBounds(435, 75, 70, 25);
        frame.add(enterButton);

        // Search Result
        lblSearchRes = new JTextArea("");
        lblSearchRes.setBounds(250, 110, 260, 200);
        //lblSearchRes.setAutoscrolls(true);
        frame.add(lblSearchRes);


        // Event Listener : Enter button, onClick
        enterButton.addActionListener(new ActionListener() {
            //TODO: This should be optimize

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String symbol = txtStockSelect.getText();
                //StringBuilder sb = new StringBuilder("Results:\n\n");

                if (stock.isSymbolExists(symbol)) {
                    ArrayList<String> ls = stock.getSymbolHistory(symbol);

                    for (int i = 0; i < ls.size(); i++) {
                        //System.out.println(">> " + ls.get(i));
                        //sb.append(ls.get(i));
                        //sb.append("\n");
                        lblSearchRes.append(ls.get(i) + "\n");
                    }
                    //lblSearchRes.setText(sb.toString());

                } else {
                    //System.out.println("The symbol, " + symbol + " is not exists.");
                    lblSearchRes.setText("The symbol, " + symbol + " is not exists.");
                }
            }
        });


        for (int i = 0; i < noOfLables; i++) {
            // Draw lables
            lblNames[i] = new JLabel(lableName[i]);
            lblNames[i].setBounds(50, 75 + (30 * i), 100, 15);
            lblNames[i].setBackground(new Color(82, 82, 82));
            lblNames[i].setForeground(new Color(0, 0, 180));
            frame.add(lblNames[i]);

            // Draw text boxes
            lblValues[i] = new JLabel("0");
            lblValues[i].setBounds(150, 75 + (30 * i), 100, 15);
            lblValues[i].setBackground(new Color(82, 82, 82));
            lblValues[i].setForeground(new Color(0, 0, 180));
            frame.add(lblValues[i]);
        }

        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < noOfLables; i++) {
            String price = Double.toString(stock.getCompany(lableName[i]).getPrice());
            //System.out.println(lableName[i] + " > " + price);
            lblValues[i].setText(price);
        }
    }
}
