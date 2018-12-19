import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Display
        extends JPanel implements ActionListener {

    //JTextArea textArea;
    Server server;
    static JFrame frame;

    private int screenWidth = 600;
    private int screenHeight = 400;

    private int noOfLables = 8;

    public enum compName {
        FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA, TXN
    }

    private JLabel[] lable = new JLabel[noOfLables];
    private final String[] lableName = {"FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};

    public Display(Server server) {
        super(new GridBagLayout());

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
        frame = new JFrame("Auction Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        //frame.setBackground(new Color(212, 38, 34));

        // Button
        //JButton b = new JButton("click");
        //b.setBounds(10, 10, 100, 40);
        //frame.add(b);

        for (int i = 0; i < noOfLables; i++) {
            lable[i] = new JLabel(lableName[i]);
            lable[i].setBounds(100, 50 + (25 * i), 400, 20);
            //lable[i].setBackground(new Color(82, 82, 82));
            lable[i].setForeground(new Color(68, 168, 22));
            frame.add(lable[i]);
        }
        //frame.add(l2);
    }

    public void actionPerformed(ActionEvent e) {
        String newline = server.getMSG();
        if (newline != null) {
            //textArea.append(newline + "\n");
            //textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}
