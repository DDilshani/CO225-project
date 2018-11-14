class Fractal {

    public static void main(String[] args) {

        int maxIter;
        float realMax, realMin, imagMin, imagMax;

        if (args.length > 0 && args[0].equals("Mandelbrot")) {
            Mandelbrot m;
            int argc = args.length;

            if (argc == 1) {                // 0 arguments >> calling default values
                m = new Mandelbrot();

            } else if (argc == 5) {        // 4 arguments >> region of interst in the complex plane
                realMin = Float.parseFloat(args[1]);
                realMax = Float.parseFloat(args[2]);
                imagMin = Float.parseFloat(args[3]);
                imagMax = Float.parseFloat(args[4]);
                m = new Mandelbrot(realMin, realMax, imagMin, imagMax);

            } else if (argc == 6) {        // 5 arguemnts >> number of iterations to do for a point
                realMin = Float.parseFloat(args[1]);
                realMax = Float.parseFloat(args[2]);
                imagMin = Float.parseFloat(args[3]);
                imagMax = Float.parseFloat(args[4]);
                maxIter = Integer.parseInt(args[5]);
                m = new Mandelbrot(realMin, realMax, imagMin, imagMax, maxIter);

            } else {
                System.out.println("Wrong input - Mandelbrot");
                return;
            }

            // Print Parameters and Draw
            m.printParameters();
            m.calculate();
            m.draw();

        } else if (args.length > 0 && args[0].equals("Julia")) {

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
            j.draw();

        } else {
            System.out.println("wrong input");
        }
    }
}
