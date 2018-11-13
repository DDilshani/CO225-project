import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

public class Mandelbrot extends JPanel {

    public float realMin = -1;
    public float realMax = 1;

    public float compMin = -1;
    public float compMax = 1;

    public int itter = 10;
    public int screenHeight = 300;
    public int screenWidth = 400;

    String title = "Mandelbrot";

    static JFrame frame;

    Mandelbrot() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    Mandelbrot(float realMin, float realMax, float compMin, float compMax, int itter) {

        // Load values given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.compMin = compMin;
        this.compMax = compMax;

        this.itter = itter;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public void draw() {

        // Draw the window
        frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void printPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
    }

    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Point p = new Point(x, y);

                float zX = 2 * (x - screenWidth / 2f) / (float) screenWidth;
                float zY = 2 * (y - screenHeight / 2f) / (float) screenHeight;
                boolean flag = false;

                Complex c = new Complex(zX, zY);
                Complex z = new Complex(0, 0);

                // now run 1000 iterations
                for (int k = 0; k < 1000; k++) {

                    //z = z^2 + c
                    z = Complex.add(Complex.square(z), c);

                    if (z.abs() > 2) {
                        flag = true;
                        break;
                    }
                }

                System.out.println("x: (" + x + ") " + zX + " y: (" + y + ") " + zY + " --> " + flag);

                if (flag) {
                    printPoint((Graphics2D) g, Color.BLACK, p);
                } else {
                    printPoint((Graphics2D) g, Color.WHITE, p);
                }

            }
        }
    }


}
