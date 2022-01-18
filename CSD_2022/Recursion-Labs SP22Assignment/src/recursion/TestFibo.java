
package recursion;

/**
 * 
 * @author nhattpam
 */
public class TestFibo {
    //Calculate the nth Fibonacci number
    public static int fibo(int n){
        if(n<2) return 1;
        return fibo(n-2) + fibo(n-1);
    }
    //Testing whether x is in the Fibonacci sequence or not
    public static boolean testFibo(int x){
        if(x<1) return false;
        int aFibo = 1;
        int n = 2;
        while(aFibo<x) aFibo = fibo(n++);
        return x==aFibo;
    }
    //Test 1  1 2 3 5 8 13 21 34 55 89 144
    public static void main(String[] args) {
        System.out.println(testFibo(55));
        System.out.println(testFibo(144));
        System.out.println(testFibo(120));
    }
}
