class Fractal {     
    public static void main(String [] args) {
        
    	if(args.length>0 && args[0]=="Mandelbrot"){
       		if( args.length==1){ // o arguments >> calling default values
       			//-------default values
       			//Mandelbrot();

       		}else if( args.length==4){ //4 arguments >> region of interst in the complex plane
      			//-------complex plane
      			//(float realMin,float realMax,float imgMin,float imgMax)
      		
      		}else if( args.length==5){ //5 arguemnts >> number of iterations to do for a point
      			//--------iterations
      			//(float realMin,float realMax,float imgMin,float imgMax,int maxIter)

      		}else {
      			System.out.pritnln("wrong input-Mandelbrot");
      		}

      	}else if (args.length>0 && args[0]=="Julia"){
      		if( args.length==1){ // o arguments >> calling default values
       			//-------default values
      			//Julia();

       		}else if( args.length==2){ //4 arguments >> region of interst in the complex plane
      			//-------real and comlex part for c
      			//(float cR,float cI)
      		
      		}else {
      			System.out.pritnln("wrong input-Julia");
      		}
      	}else {
      			System.out.pritnln("wrong input");
      	}

    }
}
