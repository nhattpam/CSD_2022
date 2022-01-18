
package recursion;

/**
 * 
 * @author nhattpam
 */
public class geometricProgression {
    //Compute the nth item of a geometric progression having the first item a and common multiplier q:
    public static double gp(int n , double a, double q){
        return a*Math.pow(q, n-1);
    }
    
    
    public static void main(String[] args) {
        System.out.println(gp(6, 1.5, 2));
    }
}
