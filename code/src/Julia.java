import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;


public class Julia extends JPanel {

    public float realMin = -1f;
    public float realMax = 1f;

    public float imagMin = -1f;
    public float imagMax = 1f;

    private float real = -0.5f;
    private float imag = 0.156f;

    public int itter = 1000;

    public int screenHeight = 600;
    public int screenWidth = 800;

    private String title = "Mandelbrot";
    static JFrame frame;

    private int[][] grid = new int[screenWidth][screenHeight];

    Julia() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    Julia(float real, float imag) {

        // Load values which were given as arguments
        /*this.realMin = realMin;
        this.realMax = realMax;

        this.imagMin = imagMin;
        this.imagMax = imagMax;
        */
        this.real = real;
        this.imag = imag;

        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    private float mapX(int x) {
        // TODO: Map region of interest
        // return 2 * (x - screenWidth / 2f) / (float) screenWidth;
        return realMin + x * (realMax - realMin) / (float) screenWidth;
    }

    private float mapY(int y) {
        // TODO: Map region of interest
        //return 2 * (y - screenHeight / 2f) / (float) screenHeight;
        return imagMax - y * (imagMax - imagMin) / (float) screenHeight;
    }

    public void calculate() {

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                // Map into the region of interest

                float zX = mapX(x);
                float zY = mapY(y);

                Complex c = new Complex(this.real, this.imag);
                Complex z = new Complex(zX, zY);

                for (int k = 0; k < itter; k++) {
                    //z = z^2 + c
                    z = Complex.add(Complex.square(z), c);

                    if (z.absSquare() > 4) {
                        grid[x][y] = k;
                        break;
                    }
                }
                //System.out.println("x: (" + x + ") " + zX + " y: (" + y + ") " + zY + " --> ");
                //System.out.println("x: " + x + " y: " + y + " " + grid[x][y]);

            }
        }
    }

    public void draw() {

        // Draw the window
        frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private static void drawPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
        //frame.drawOval((int) p.getX(), (int) p.getY(), 2, 2);

    }

    @Override
    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Point p = new Point(x, y);

                int n = grid[x][y];

                if (n == 0) {
                    drawPoint((Graphics2D) g, Color.RED, p);
                } else {
                    //n = Math.min(127, (n * 255 *5) / itter);
                    //printPoint((Graphics2D) g, new Color(n, n, n), p);
                    drawPoint((Graphics2D) g, Color.BLACK, p);
                }
            }
        }
    }

    public void printParameters() {
        // Print all the parameters into Stdout
        System.out.println(">> Julia Set");
        System.out.println("Screen  " + screenWidth + " x " + screenHeight);
        System.out.println("Real    [" + realMin + "," + realMax + "]");
        System.out.println("Complex [" + imagMin + "," + imagMax + "]");
        System.out.println("Iter:   " + itter);

    }
}