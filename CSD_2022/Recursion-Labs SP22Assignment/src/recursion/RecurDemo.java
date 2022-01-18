
package recursion;

/**
 * 
 * @author nhattpam
 */
public class RecurDemo {
    
    public static double factorial(int n){
        if(n<2) return 1;
        return n * factorial(n-1);  
    }
    
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println(factorial(122));
    }
}   
