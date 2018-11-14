import java.awt.*;

public class Mandelbrot extends FractalSuper {

    Mandelbrot() {
        super();
        title = "Mandelbrot";
    }

    Mandelbrot(float realMin, float realMax, float imagMin, float imagMax) {
        super();

        // Load values which were given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.imagMin = imagMin;
        this.imagMax = imagMax;

        title = "Mandelbrot";

    }

    Mandelbrot(float realMin, float realMax, float imagMin, float imagMax, int iter) {
        super();

        // Load values which were given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.imagMin = imagMin;
        this.imagMax = imagMax;

        this.iter = iter;

        title = "Mandelbrot";
    }

    @Override
    public void calculate() {
        calculate(0, screenWidth, 0, screenHeight);
    }

    public void calculate(int xFrom, int xTo, int yFrom, int yTo) {

        for (int x = xFrom; x < xTo; x++) {
            for (int y = yFrom; y < yTo; y++) {
                // Map into the region of interest

                float zX = mapX(x);
                float zY = mapY(y);

                Complex c = new Complex(zX, zY);
                Complex z = new Complex(0, 0);

                for (int k = 0; k < iter; k++) {
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

    @Override
    public void paintComponent(Graphics g) {

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
