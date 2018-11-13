import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Mandelbrot extends JPanel {

    public float realMin = -1;
    public float realMax = 1;

    public float compMin = -1;
    public float compMax = 1;

    public int itter = 1000;

    public int screenHeight = 600;
    public int screenWidth = 800;

    private String title = "Mandelbrot";
    static JFrame frame;

    private int[][] grid = new int[screenWidth][screenHeight];

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

    public void calculate() {

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {

                float zX = 2 * (x - screenWidth / 2f) / (float) screenWidth;
                float zY = 2 * (y - screenHeight / 2f) / (float) screenHeight;

                Complex c = new Complex(zX, zY);
                Complex z = new Complex(0, 0);

                for (int k = 0; k < itter; k++) {
                    //z = z^2 + c
                    z = Complex.add(Complex.square(z), c);

                    if (z.absSquare() > 4) {
                        grid[x][y] = k;
                        break;
                    }
                }
                //System.out.println("x: (" + x + ") " + zX + " y: (" + y + ") " + zY + " --> " + flag);
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

    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Point p = new Point(x, y);

                int n = grid[x][y];

                if (n == 0) {
                    drawPoint((Graphics2D) g, Color.WHITE, p);
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
        System.out.println(">> Mandelbrot");
        System.out.println("Screen  " + screenWidth + " x " + screenHeight);
        System.out.println("Real    [" + realMin + "," + realMax + "]");
        System.out.println("Complex [" + compMin + "," + compMax + "]");
        System.out.println("Iter:   " + itter);

    }

}
