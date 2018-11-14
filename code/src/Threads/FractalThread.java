import java.awt.*;

class FractalThread extends Mandelbrot implements Runnable {

    private Thread t;
    private String tName;

    private int xF, xT, yF, yT;

    FractalThread(String name, Rectangle a) {
        tName = name;

        xF = (int) a.getMinX();
        xT = (int) a.getMaxX();

        yF = (int) a.getMinY();
        yT = (int) a.getMaxY();
    }

    public void run() {
        calculate(xF, xT, yF, yT);
        System.out.println("Thread " + tName + " completed.");
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, tName);
            t.start();
        }
    }

    @Override
    public void draw(){
        super.draw();
    }

}
