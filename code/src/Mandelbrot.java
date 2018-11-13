import javax.swing.JFrame;


public class Mandelbrot {

    public float realMin = -1;
    public float realMax = 1;

    public float compMin = -1;
    public float compMax = 1;

    public int itter = 10;
    public int screenHeight = 600;
    public int screenWidth = 600;

    private String title = "Mandelbrot";

    static JFrame frame;

    Mandelbrot() {

        // Load default values
        this.realMin = -1;
        this.realMax = 1;

        this.compMin = -1;
        this.compMax = 1;

        this.itter = 1;

    }

    Mandelbrot(float realMin, float realMax, float compMin, float compMax, int itter) {

        // Load values given as arguments
        this.realMin = realMin;
        this.realMax = realMax;

        this.compMin = compMin;
        this.compMax = compMax;

        this.itter = itter;
    }

    public void draw() {

        // Draw the window
        frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel(this.screenWidth, this.screenHeight,this));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
