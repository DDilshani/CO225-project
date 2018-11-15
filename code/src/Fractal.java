import java.awt.*;

class Fractal {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int noOfThreads =1;
        int maxIter = 1000;
        float realMax = 1f, realMin = -1f, imagMin = -1f, imagMax = 1f;

        Mandelbrot[] m = new Mandelbrot[noOfThreads];
        Thread[] tr = new Thread[noOfThreads];

        if (args.length > 0 && args[0].equals("Mandelbrot")) {
            int argc = args.length;

            if (argc == 1) {                // 0 arguments >> calling default values
                // All default arguments

            } else if (argc == 5) {        // 4 arguments >> region of interst in the complex plane
                realMin = Float.parseFloat(args[1]);
                realMax = Float.parseFloat(args[2]);
                imagMin = Float.parseFloat(args[3]);
                imagMax = Float.parseFloat(args[4]);

            } else if (argc == 6) {        // 5 arguemnts >> number of iterations to do for a point
                realMin = Float.parseFloat(args[1]);
                realMax = Float.parseFloat(args[2]);
                imagMin = Float.parseFloat(args[3]);
                imagMax = Float.parseFloat(args[4]);
                maxIter = Integer.parseInt(args[5]);

            } else {
                System.out.println("Wrong input - Mandelbrot");
                return;
            }

            for (int i = 0; i < noOfThreads; i++) {

                m[i] = new Mandelbrot(realMin, realMax, imagMin, imagMax, maxIter, "Thread" + i);
                m[i].applicableArea(new Rectangle((800 / noOfThreads) * i, 0, (800 / noOfThreads), 600));

                tr[i] = new Thread(m[i], "Thread" + i);
                tr[i].start();
            }

            try {

                for (int i = 0; i < noOfThreads; i++) {
                    tr[i].join();
                }

            } catch (InterruptedException e) {
                System.out.println("Not good");
            }

            // Draw the matrix on display
            Plotter p = new Plotter(m[0].getGrid());
            p.draw();


        } else if (args.length > 0 && args[0].
                equals("Julia")) {

            Julia j = new Julia();
            int argc = args.length;

            if (argc == 1) {
                j = new Julia();

            } else if (argc == 3) {
                j = new Julia(Float.parseFloat(args[1]), Float.parseFloat(args[2]));

            } else if (argc == 4) {
                j = new Julia(Float.parseFloat(args[1]), Float.parseFloat(args[2]), Integer.parseInt(args[3]));

            } else {
                System.out.println("Wrong input - Julia set");
                return;
            }

            j.printParameters();
            j.calculate();
            //j.draw();

        } else {
            System.out.println("wrong input");
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Execution Time: " + duration + "ms");
    }
}
