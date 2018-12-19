import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.*;

public class Display
        extends JPanel implements ActionListener {

    //JTextArea textArea;
    Server server;
    StockDatabase stock = null;
    static JFrame frame;

    private int screenWidth = 600;
    private int screenHeight = 400;

    private int noOfLables = 8;

    public enum compName {
        FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA, TXN
    }

    private JLabel[] lblNames = new JLabel[noOfLables];
    private JLabel[] lblValues = new JLabel[noOfLables];

    private final String[] lableName = {"FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};

    public Display(Server server, StockDatabase stock) {
        super(new GridBagLayout());

        Date d = new Date();
        this.stock = stock;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        draw();



        /*JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        */

        /*textArea = new JTextArea(20, 20);
        textArea.setEditable(false);
        */
        /*JScrollPane scrollPane = new JScrollPane(textArea);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
*/
        Timer timer = new Timer(500, this);
        timer.start();

        this.server = server;
    }


    public void draw() {

        // Draw the window
        frame = new JFrame("Auction Server 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        //frame.pack();

        //frame.setLocationRelativeTo(null);
        JLabel title = new JLabel("Auction Server");
        title.setBounds(100, 50, 300, 20);
        title.setFont(new Font("Verdana", Font.PLAIN, 24));
        frame.add(title);

        for (int i = 0; i < noOfLables; i++) {
            lblNames[i] = new JLabel(lableName[i]);
            lblNames[i].setBounds(100, 100 + (30 * i), 100, 15);
            lblNames[i].setBackground(new Color(82, 82, 82));
            lblNames[i].setForeground(new Color(0, 0, 180));
            frame.add(lblNames[i]);

            lblValues[i] = new JLabel("0");
            lblValues[i].setBounds(200, 100 + (30 * i), 100, 15);
            lblValues[i].setBackground(new Color(82, 82, 82));
            lblValues[i].setForeground(new Color(0, 0, 180));
            frame.add(lblValues[i]);
        }

        frame.setSize(screenWidth, screenHeight);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        //frame.add(l2);
    }

    public void actionPerformed(ActionEvent e) {
        //String newline = server.getMSG();

        for (int i = 0; i < noOfLables; i++) {
            //System.out.println(stock.stockMarket.get(lableName[i]).getPrice());
            String price = Double.toString(stock.stockMarket.get(lableName[i]).getPrice());
            lblValues[i].setText(price);
        }

        /*if (newline != null) {
            //textArea.append(newline + "\n");
            //textArea.setCaretPosition(textArea.getDocument().getLength());
        }*/


    }
}
