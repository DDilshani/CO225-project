public class Mandelbrot {

    public float realMin = -1;
    public  float realMax = 1;

    public  float compMin = -1;
    public  float compMax = 1;

    public  int itter = 1000;


    Mandelbrot() {

    }

    Mandelbrot(float realMin, float realMax, float compMin, float compMax, int itter) {
        this.realMin = realMin;
        this.realMax = realMax;

        this.compMin = compMin;
        this.compMax = compMax;

        this.itter = itter;
    }


}
