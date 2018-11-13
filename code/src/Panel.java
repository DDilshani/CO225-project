import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;

class Panel extends JPanel { // inherit JPanel

    private int width, height;

    public Panel(int width, int height) {
        // set the panel size
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));

    }

    public static void printPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
    }

    public void paintComponent(Graphics g) {
        // call paintComponent from parent class

        super.paintComponent(g);

        Point p = new Point(width / 2, height / 2);
        printPoint((Graphics2D) g, Color.BLACK, p);


    }

/*
    private static void printPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(),
                p.getX(), p.getY()));
    }
*/

/*
    public void paintComponent(Graphics g) {
        // call paintComponent from parent class
        super.paintComponent(g);

        Point mid = Point.findMidTo(triangle[0], triangle[1], triangle[2]);


        Random ran = new Random();

        // When it refreshes this function is called again.
        // So do not change the value of static points
        int points = Panel.points;

        while(points-- > 0) {
            int r = ran.nextInt(3);
            mid = mid.findMidTo(triangle[r]);
            printPoint((Graphics2D)g, Color.BLACK, mid);
        }

    }
*/
}
