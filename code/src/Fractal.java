class Fractal {

    public static void main(String[] args) {


        if (args.length > 0 && args[0].equals("Mandelbrot")) {

            if (args.length == 1) { // o arguments >> calling default values
                //-------default values
                Mandelbrot m = new Mandelbrot();
                Debugger.debugArgs(m);

            } else if (args.length == 4) { //4 arguments >> region of interst in the complex plane

                //-------default values
                Mandelbrot m = new Mandelbrot();
                Debugger.debugArgs(m);

                //-------complex plane
                //(float realMin,float realMax,float imgMin,float imgMax)

            } else if (args.length == 5) { //5 arguemnts >> number of iterations to do for a point
                //--------iterations
                //(float realMin,float realMax,float imgMin,float imgMax,int maxIter)

                //-------default values
                Mandelbrot m = new Mandelbrot();
                Debugger.debugArgs(m);

            } else {

                Mandelbrot m = new Mandelbrot();
                Debugger.debugArgs(m);

                m.draw();


                //System.out.println("wrong input-Mandelbrot");
            }

        } else if (args.length > 0 && args[0].equals("Julia")) {
            if (args.length == 1) { // o arguments >> calling default values
                //-------default values
                //Julia();

            } else if (args.length == 2) { //4 arguments >> region of interst in the complex plane
                //-------real and comlex part for c
                //(float cR,float cI)

            } else {


                System.out.println("wrong input-Julia");
            }
        } else {
            System.out.println("wrong input");
        }

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
