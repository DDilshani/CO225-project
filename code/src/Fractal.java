class Fractal {

    public static void main(String[] args){

        Mandelbrot m = new Mandelbrot();
        Debugger.debugArgs(m);

        m.draw();
    }
}

class Debugger {

    public static void debugArgs(Mandelbrot m) {
        System.out.println("Mandelbrot");
        System.out.println("Screen width " + m.screenWidth);
        System.out.println("Real min " + m.realMin);
        System.out.println("Real max " + m.realMax);
        System.out.println("Complex min " + m.compMin);
        System.out.println("Complex max " + m.compMax);
        System.out.println("Iterations " + m.itter);

    }

    /*public  static  void debugArgs(Julia j) {
        System.out.println("Julia");
        System.out.println("Screen width " + j.screenWidth);
        System.out.println("Real min " + j.realMin);
        System.out.println("Real max " + j.realMax);
        System.out.println("Complex min " + j.compMin);
        System.out.println("Complex max " + j.compMax);
        System.out.println("Iterations " + j.itter);
    }*/
}
