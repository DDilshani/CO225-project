import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Julia extends FractalSuper {

    private float real, imag;

    private String title = "Julia";

    Julia() {
        super();
    }

    Julia(float real, float imag) {
        super();

        this.real = real;
        this.imag = imag;
    }

    Julia(float real, float imag, int iter) {
        super();

        this.real = real;
        this.imag = imag;
        this.iter = iter;
    }

    @Override
    public void calculate() {

        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                // Map into the region of interest

                float zX = mapX(x);
                float zY = mapY(y);

                Complex c = new Complex(this.real, this.imag);
                Complex z = new Complex(zX, zY);

                for (int k = 0; k < iter; k++) {
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
                    drawPoint((Graphics2D) g, colorCalculator(n), p);
                }
            }
        }
    }

}