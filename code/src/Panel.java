import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.Random;

class Panel extends JPanel { // inherit JPanel

    private int width, height;
    private Mandelbrot m;

    public Panel(int width, int height, Mandelbrot m) {
        // set the panel size
        this.width = width;
        this.height = height;

        this.m = m;

        setPreferredSize(new Dimension(width, height));
    }

    public static void printPoint(Graphics2D frame, Color c, Point p) {

        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
    }

    public void paintComponent(Graphics g) {
        // call paintComponent from parent class

        super.paintComponent(g);

        Complex z = new Complex(0, 0);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                float zX = 2 * (x - width / 2f) / (float) width;//((2 * x) - width) / width;
                float zY = 2 * (y - height / 2f) / (float) height; //((2 * y) - height) / height;
                boolean flag = false;

                Complex c = new Complex(zX, zY);

                // now run 1000 iterations
                for (int k = 0; k < 1000; k++) {

                    // z square
                    z = new Complex((z.re() * z.re() - z.im() * z.im()) + zX, 2 * z.re() * z.im() + zY);//   z.times(z).plus(c);

                    if (z.abs() > 2) {
                        flag = true;
                        break;
                    }
                }

                System.out.println("x: (" + x + ") " + zX + " y: (" + y + ") " + zY + " --> " + flag);
                Point p = new Point(x, y);

                if (flag == true) {
                    printPoint((Graphics2D) g, Color.WHITE, p);
                } else {
                    printPoint((Graphics2D) g, Color.BLACK, p);
                }

            }
        }




        /*for (int i = 0; i < 100; i++) {

            Point p = new Point((width + i) / 2, (height + i) / 2);
            printPoint((Graphics2D) g, Color.BLACK, p);

        }*/


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
