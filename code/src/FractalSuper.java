
public abstract class FractalSuper  extends Thread{

    protected Thread t;
    protected String threadName;

    public float realMin = -1f;
    public float realMax = 1f;

    public float imagMin = -1f;
    public float imagMax = 1f;

    public int iter = 1000;

    static int screenHeight = 600;
    static int screenWidth = 800;

    protected String title = "Fractals";

    protected static int[][] grid = new int[screenWidth][screenHeight];

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
    }

    public void printParameters() {
        // Print all the parameters into Stdout
        System.out.println(">> " + title);
        System.out.println("Screen  " + screenWidth + " x " + screenHeight);
        System.out.println("Real    [" + realMin + "," + realMax + "]");
        System.out.println("Complex [" + imagMin + "," + imagMax + "]");
        System.out.println("Iter:   " + iter);

    }

}
