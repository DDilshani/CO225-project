import java.awt.*;

public abstract class FractalSuper  extends Thread{

    static PointGrid grid = new PointGrid(800, 600);

    protected int xF, xT, yF, yT;

    protected Thread t;
    protected String threadName;

    protected float realMin = -1f;
    protected float realMax = 1f;

    protected float imagMin = -1f;
    protected float imagMax = 1f;

    protected int iter = 1000;

    static int screenHeight = 800;
    static int screenWidth = 800;

    protected String title = "Fractals";

    FractalSuper() {
        // Null default constructor
    }

    protected float mapX(int x) {
        return realMin + x * (realMax - realMin) / (float) screenWidth;
    }

    protected float mapY(int y) {
        return imagMax - y * (imagMax - imagMin) / (float) screenHeight;
    }

    public void calculate() {
        calculate(0, screenWidth, 0, screenHeight);
    }
    public void calculate(int xFrom, int xTo, int yFrom, int yTo){

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

    public void printParameters() {
        // Print all the parameters into Stdout
        System.out.println(">> " + title);
        System.out.println("Screen  " + screenWidth + " x " + screenHeight);
        System.out.println("Real    [" + realMin + "," + realMax + "]");
        System.out.println("Complex [" + imagMin + "," + imagMax + "]");
        System.out.println("Iter:   " + iter);

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
