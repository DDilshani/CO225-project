/* --------------------------------------------------------------------------------------

   CO225 Software Construction Project - Fractals: Natures building blocks

   Authors:
       Jaliyagoda. A.J.N.M.    (E/15/140)
       Karunarathne S.D.D.D.   (E/15/173)

   Classes:
       Fractal (main)
       Mandelbrot  <-- FractalSuper    <-- Thread
       Julia       <-- FractalSuper    <-- Thread
       MyPanel     <-- JPanel
       PointGrid
       Complex

   Last Update:
       15/11/2018

   Source Code:
       https://github.com/DDilshani/CO225-project

-------------------------------------------------------------------------------------- */


import java.awt.*;

class Fractal {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int argc = args.length;
        int maxIter = 1000;
        int screenHeight = 800, screenWidth = 800;

        // Using Threads
        int noOfThreads = 2;
        Thread[] tr = new Thread[noOfThreads];

        // ----- Mandelbrot --------------------------------------------------------------------------------------
        if (argc > 0 && args[0].equals("Mandelbrot")) {

            Mandelbrot[] m = new Mandelbrot[noOfThreads];
            float realMax = 1f, realMin = -1f, imagMin = -1f, imagMax = 1f;

            // Read and update arguments given from the commandline
            if (argc == 1 || argc == 5 || argc == 6) {
                if (argc >= 5) {
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
                // Starting a Thread
                m[i] = new Mandelbrot(realMin, realMax, imagMin, imagMax, maxIter, "Thread" + i);
                m[i].applicableArea(new Rectangle((screenWidth / noOfThreads) * i, 0, (screenWidth / noOfThreads), screenHeight));

                tr[i] = new Thread(m[i], "Thread" + i);
                tr[i].start();
            }

            try {
                // Wait until all the threads complete execution
                for (int i = 0; i < noOfThreads; i++) {
                    tr[i].join();
                }

            } catch (InterruptedException e) {
                System.out.println("Not good");
            }

            // Draw the calculated values on the display
            m[0].printParameters();
            MyPanel p = new MyPanel(m[0].getGrid(), screenHeight, screenWidth, "Mandelbrot");
            p.draw();

            // ----- Julia Set -------------------------------------------------------------------------------
        } else if (argc > 0 && args[0].equals("Julia")) {

            Julia[] j = new Julia[noOfThreads];
            float real = -0.4f, imag = 0.6f;

            // Read and update arguments given from the commandline
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
                j[i].applicableArea(new Rectangle((screenWidth / noOfThreads) * i, 0, (screenWidth / noOfThreads), screenHeight));

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

            // Draw the calculated values on the display
            j[0].printParameters();
            MyPanel p = new MyPanel(j[0].getGrid(), screenHeight, screenWidth, "Julia Sets");
            p.draw();

        } else {
            System.out.println("Wrong input");
            return;
        }

        // Print the time spend for execution in milliseconds
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("\nExecution Time: " + duration + "ms");
    }
}
