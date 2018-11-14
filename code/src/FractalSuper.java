
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public abstract class FractalSuper extends JPanel{

    public float realMin = -1f;
    public float realMax = 1f;

    public float imagMin = -1f;
    public float imagMax = 1f;

    public int iter = 1000;

    static int screenHeight = 600;
    static int screenWidth = 800;

    private String title = "Fractals";
    static JFrame frame;

    protected static int[][] grid = new int[screenWidth][screenHeight];

    FractalSuper(){
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    protected float mapX(int x) {
        // return 2 * (x - screenWidth / 2f) / (float) screenWidth;
        return realMin + x * (realMax - realMin) / (float) screenWidth;
    }

    protected float mapY(int y) {
        //return 2 * (y - screenHeight / 2f) / (float) screenHeight;
        return imagMax - y * (imagMax - imagMin) / (float) screenHeight;
    }

    public void calculate() {

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

    protected static void drawPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
        //frame.drawOval((int) p.getX(), (int) p.getY(), 2, 2);
    }

    protected Color colorCalculator(int k){
        return new Color(Color.HSBtoRGB(Math.min(255, (int) (220 + k / 64f)), 1, k / (k + 8f)));
    }

    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);

        /*for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Point p = new Point(x, y);

                int n = grid[x][y];

                if (n == 0) {
                    drawPoint((Graphics2D) g, Color.RED, p);
                } else {
                    //n = Math.min(127, (n * 255 *5) / itter);
                    //printPoint((Graphics2D) g, new Color(n, n, n), p);

                    Color clr = new Color(Color.HSBtoRGB(Math.min(255, (int) (220 + n / 64f)), 1, n / (n + 8f)));
                    drawPoint((Graphics2D) g, clr, p);

                }
            }
        }*/
    }

    public void printParameters() {
        // Print all the parameters into Stdout
        System.out.println(">> " + title);
        System.out.println("Screen  " + screenWidth + " x " + screenHeight);
        System.out.println("Real    [" + realMin + "," + realMax + "]");
        System.out.println("Complex [" + imagMin + "," + imagMax + "]");
        System.out.println("Iter:   " + iter);

    }
}
