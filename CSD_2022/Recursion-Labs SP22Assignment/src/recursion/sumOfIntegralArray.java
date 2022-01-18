
package recursion;

/**
 * 
 * @author nhattpam
 */
public class sumOfIntegralArray {
    //Calculate sum of integral array having n elements
    public static double sum(double a[], int n){
        n = a.length ;
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        return sum;
    }
    
    public static void main(String[] args) {
        double a[] = {1.5, 2, 4, 5, 2, 6.5};
        System.out.println(sum(a, 6));
    }
 
}
