import javax.swing.JFrame;


public class Drawings {

    private int screenHeight = 800;
    private int screenWidth = 800;

    private String title;

    static JFrame frame;

    Drawings(int width, int height, String title) {

    }

    public void start() {

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel(this.screenWidth, this.screenHeight));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
