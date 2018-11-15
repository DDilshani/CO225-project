import java.awt.*;

class Fractal {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int argc = args.length;
        int maxIter = 1000;

        int noOfThreads = 2;

        Thread[] tr = new Thread[noOfThreads];

        if (args.length > 0 && args[0].equals("Mandelbrot")) {

            Mandelbrot[] m = new Mandelbrot[noOfThreads];
            float realMax = 1f, realMin = -1f, imagMin = -1f, imagMax = 1f;

            if (argc == 1 || argc == 5 || argc == 6) {
                if (argc >= 5) {        // 4 arguments >> region of interst in the complex plane
                    realMin = Float.parseFloat(args[1]);
                    realMax = Float.parseFloat(args[2]);
                    imagMin = Float.parseFloat(args[3]);
                    imagMax = Float.parseFloat(args[4]);
                }
                if (argc == 6) maxIter = Integer.parseInt(args[5]);

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
            m[0].printParameters();
            Plotter p = new Plotter(m[0].getGrid(), "Mandelbrot");
            p.draw();

        } else if (args.length > 0 && args[0].equals("Julia")) {

            Julia[] j = new Julia[noOfThreads];
            float real = 1f, imag = -1f;

            if (argc == 1 || argc == 3 || argc == 4) {
                if (argc >= 3) {
                    real = Float.parseFloat(args[1]);
                    imag = Float.parseFloat(args[2]);
                }
                if (argc == 4) maxIter = Integer.parseInt(args[3]);
            } else {
                System.out.println("Wrong input - Julia set");
                return;
            }

            for (int i = 0; i < noOfThreads; i++) {
                j[i] = new Julia(real, imag, maxIter, "Thread" + i);
                j[i].applicableArea(new Rectangle((800 / noOfThreads) * i, 0, (800 / noOfThreads), 600));

                tr[i] = new Thread(j[i], "Thread" + i);
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
            j[0].printParameters();
            Plotter p = new Plotter(j[0].getGrid(), "Julia Set");
            p.draw();

        } else

        {
            System.out.println("wrong input");
            return;
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Execution Time: " + duration + "ms");
    }
}
