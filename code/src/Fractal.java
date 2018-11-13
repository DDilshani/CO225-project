class Fractal {

    public static void main(String[] args) {

        Mandelbrot m = new Mandelbrot();
        m.printParameters();
        m.calculate();
        m.draw();
    }
}


