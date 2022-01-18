
package recursion;

/**
 * 
 * @author nhattpam
 */
public class minimumValueInAnIntegralArray {
    // Calculate the minimum value in an integral array having n elements
    public static double min(int[] a, int n){
        if (n == 1) {
            return a[0];
        }
        int m = (int) min(a, n - 1); // 10
        return m < a[n - 1] ? m : a[n - 1];
    }
    
    public static void main(String[] args) {
        int b[] = {1, 5, 9, 7, 2, 19, 10};
        System.out.println(min(b, 7));
    }
}
