
package stk_que.QDemo;

import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * @author nhattpam
 */
public class Shop {
    public static void main(String[] args) {
        LinkedList<String> queue  = new LinkedList();//setup a queue
        int duty = 30;//duty of an accountant
        Accountant acc = new Accountant(duty, queue);//acc is not a thread
        Thread accThread =  new Thread(acc);//create a thread from acc
        accThread.start();//starting the thread, accountant is ready
        int N = 10; //The shop opens, 10 customers enter the shop
        Customer[] custList = new Customer[N];//10 threads
        String custName;
        Random rand = Customer.rand;
        int delay;
        for (int i = 0; i < N; i++) {//create a thread for each customer
            custName = "cust " + (i+1);//customer's name
            delay=200 + rand.nextInt(500);//customer's delay
            custList[i] = new Customer(custName, delay, accThread, queue);
        }
        //Customer purchase products. 10 threads
        for(int i = 0; i < N; i++) custList[i].start();
    }
}//Shop class
