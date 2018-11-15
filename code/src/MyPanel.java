import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class MyPanel extends JPanel {

    static int screenHeight = 800, screenWidth = 800;

    private PointGrid grid;

    protected String title = "Fractals";
    static JFrame frame;

    MyPanel(PointGrid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(screenWidth, screenHeight));
    }
    MyPanel(PointGrid grid, String title) {
        this(grid);
        this.title = title;
    }

    MyPanel(PointGrid grid, int height, int width, String title) {
        this(grid);
        screenHeight = height;
        screenWidth = width;

        this.title = title;
    }

    protected static void drawPoint(Graphics2D frame, Color c, Point p) {
        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
        //frame.drawOval((int) p.getX(), (int) p.getY(), 2, 2);
    }

    protected Color colorCalculator(int k) {
        return new Color(Color.HSBtoRGB(k / 256f, 1, k / (k + 8f)));
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                Point p = new Point(x, y);

                int n = PointGrid.getPoint(x, y);

                if (n == 0) {
                    drawPoint((Graphics2D) g, Color.BLACK, p);

                } else {
                    drawPoint((Graphics2D) g, colorCalculator(n), p);
                }
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
}
