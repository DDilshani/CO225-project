import com.sun.deploy.util.SyncAccess;

import java.awt.*;

public class Mandelbrot extends FractalSuper {

    static PointGrid grid = new PointGrid(800, 600);
    private int xF, xT, yF, yT;

    Mandelbrot() {
        //super();
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

    Mandelbrot(float realMin, float realMax, float imagMin, float imagMax, int iter, String tName) {
        super();

        // Load values which were given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.imagMin = imagMin;
        this.imagMax = imagMax;

        this.iter = iter;

        title = "Mandelbrot";
        this.threadName = tName;
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
                        PointGrid.setPoint(x, y, k);
                        break;
                    }
                }
                //System.out.println("x: (" + x + ") " + zX + " y: (" + y + ") " + zY + " --> " + flag);
                //System.out.println("x: " + x + " y: " + y + " " + grid[x][y]);
            }
        }
    }

    public PointGrid getGrid() {
        return grid;
    }

    public void applicableArea(Rectangle a) {
        //System.out.println(xF + " " + xT + " " + yF + " " + yT);

        xF = (int) a.getMinX();
        xT = (int) a.getMaxX();

        yF = (int) a.getMinY();
        yT = (int) a.getMaxY();
    }

    public void run() {
        System.out.println(threadName + " running...");
        calculate(xF, xT, yF, yT);
        //calculate();
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
