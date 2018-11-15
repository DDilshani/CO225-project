import java.awt.*;

public abstract class FractalSuper extends Thread {

    // ---- Attributes -----------------------------------------------------------------------

    protected int xF, xT, yF, yT;

    protected Thread t;
    protected String threadName;

    protected float realMin = -1f, realMax = 1f;
    protected float imagMin = -1f, imagMax = 1f;
    protected int iter = 1000;

    private static int screenWidth = 800, screenHeight = 800;
    static PointGrid grid;

    protected String title = "Fractals";

    // ---- Methods -------------------------------------------------------------------------

    FractalSuper() {
        grid = new PointGrid(screenWidth, screenHeight);
    }

    protected float mapX(int x) {
        // Map the x coordinate to the complex plane
        return realMin + x * (realMax - realMin) / (float) screenWidth;
    }

    protected float mapY(int y) {
        // Map the y coordinate to the complex plane
        return imagMax - y * (imagMax - imagMin) / (float) screenHeight;
    }

    public void calculate() {
        calculate(0, screenWidth, 0, screenHeight);
    }

    public void calculate(int xFrom, int xTo, int yFrom, int yTo) {
        // Will be overloaded by child class
    }

    public PointGrid getGrid() {
        return grid;
    }

    public void applicableArea(Rectangle a) {
        // This is the region calculated by the current thread
        xF = (int) a.getMinX();
        xT = (int) a.getMaxX();

        yF = (int) a.getMinY();
        yT = (int) a.getMaxY();

        //System.out.println(xF + " " + xT + " " + yF + " " + yT);
    }

    public void printParameters() {
        // Print all the parameters into Stdout
        System.out.println(">> " + title);
        System.out.println("Screen  " + screenWidth + " x " + screenHeight);
        System.out.println("Real    [" + realMin + "," + realMax + "]");
        System.out.println("Complex [" + imagMin + "," + imagMax + "]");
        System.out.println("Iter:   " + iter);

    }


    // ---- Thread Related Methods --------------------------------------------------------

    @Override
    public void run() {
        System.out.println(threadName + " running...");
        calculate(xF, xT, yF, yT);
    }

    @Override
    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

}
