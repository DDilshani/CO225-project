
public class Complex {

    private final double real;
    private final double imag;

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imag + b.imag);
    }

    public static Complex sub(Complex a, Complex b) {
        return new Complex(a.real - b.real, a.imag - b.imag);
    }

    public static Complex multiply(Complex a, Complex b) {
        return new Complex(a.real * b.real - a.imag * b.imag, a.real * b.imag + a.imag * b.real);
    }

    public static Complex square(Complex a) {
        //return new Complex(Math.pow(a.real, 2) - Math.pow(a.imag, 2), 2 * a.real * a.imag);
        return new Complex(a.real*a.real-a.imag*a.imag , 2*a.real*a.imag);
    }

    public double abs() {
        //return Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
        return Math.hypot(real, imag);
    }

    public double absSquare() {
        return (Math.pow(real, 2) + Math.pow(imag, 2));
    }
}
