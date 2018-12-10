
public class Mandelbrot extends FractalSuper {
    private String title = "Mandelbrot";

    Mandelbrot() {
    }
    Mandelbrot(float realMin, float realMax, float imagMin, float imagMax) {
        this();

        // Load values which were given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.imagMin = imagMin;
        this.imagMax = imagMax;
    }

    Mandelbrot(float realMin, float realMax, float imagMin, float imagMax, int iter, String tName) {
        this();

        // Load values which were given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.imagMin = imagMin;
        this.imagMax = imagMax;

        this.iter = iter;

        this.threadName = tName;
    }

    @Override
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
                        PointGrid.setPoint(x, y, k);
                        break;
                    }
                }
                //System.out.println("x: " + x + " y: " + y + " " + grid[x][y]);
            }
        }
    }
}
