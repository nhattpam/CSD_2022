package recursion;

/**
 *
 * @author nhattpam
 */
public class arithmeticProgression {

    //Compute the nth item of an arithmetic progression having the first item a and common difference d: 
    public static double ap(int n, double a, double d) {
        return a+(n-1)*d;
    }
    public static void main(String[] args) {
        System.out.println(ap(6, 1.5, 2));
    }

}
