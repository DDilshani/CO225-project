
public class Julia extends FractalSuper {
    private float real, imag;
    private String title = "Julia Set";

    Julia() {
        this.real = -0.4f;
        this.imag = 0.6f;
    }

    Julia(float real, float imag) {
        this();

        this.real = real;
        this.imag = imag;

    }

    Julia(float real, float imag, int iter, String tName) {
        this();

        this.real = real;
        this.imag = imag;
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

                Complex c = new Complex(this.real, this.imag);
                Complex z = new Complex(zX, zY);

                for (int k = 0; k < iter; k++) {
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