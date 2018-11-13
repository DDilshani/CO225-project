class Fractal {

	int width,height,maxIter;
    float realMax,realMin,imgMin,imgMax;

    public static void main(String[] args) {

		if (args.length > 0 && args[0].equals("Mandelbrot")) {
			Mandelbrot m;


            if (argc==1) { 				// 0 arguments >> calling default values
                //mapping(realMin,realMax,imgMin,imgMax,maxIter);
            	m = new Mandelbrot();

            } else if (argsc==5) { 		// 4 arguments >> region of interst in the complex plane
                realMax = Float.parseFloat(args[1]);
                realMin = Float.parseFloat(args[2]);
                imagMin  = Float.parseFloat(args[3]);
                imagMax  = Float.parseFloat(args[4]);
                m = new Mandelbrot(realMin,realMax,imagMin,imagMax);

            } else if (argc==6) { 		// 5 arguemnts >> number of iterations to do for a point
                realMax = Float.parseFloat(args[1]);
                realMin = Float.parseFloat(args[2]);
                imgMin  = Float.parseFloat(args[3]);
                imgMax  = Float.parseFloat(args[4]);
                maxIter = int.parseFloat(args[5]);
                m = new Mandelbrot(realMin,realMax,imgMin,imgMax,maxIter);

            } else {
                System.out.println("Wrong input - Mandelbrot");
            }

            // Print Parameters and Draw 
            m.printParameters();
        	m.calculate();
        	m.draw();

        }else {
            System.out.println("wrong input");
        }








  
    }
}


