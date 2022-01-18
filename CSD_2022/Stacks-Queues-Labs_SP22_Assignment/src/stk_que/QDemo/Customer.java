
package stk_que.QDemo;

import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * @author nhattpam
 */
public class Customer extends Thread{
    //for randomize money number
    static Random rand = new Random(System.currentTimeMillis());
    long delay; //milisec for delay after each purchase
    LinkedList<String> queue;//customer know where the queue is.
    Thread acc;//acc must existed before a customer can buy products
    //Create a customer
    public Customer(String cusName, long delay,
            Thread acc, LinkedList<String> queue){
        super(cusName);//Customer is given a name
        this.delay=delay;
        this.acc=acc;
        this.queue=queue;
    }
    //Activity for buying products

    @Override
    public void run() {
        while (acc.isAlive()) {//accountant is working 
            try {
                //money amount will be paid
                int price = 100 + rand.nextInt(100); //at least 100$
                //info. in an invoice
                String msg  = this.getName() + ", " + price + "$";
                //Sending a request to the queue for paying 
                queue.addLast(msg);//wait for invoice
                //If acc stopped working, customer stops buying
                if(!acc.isAlive()) this.yield();//Yield CPU
                //Else customer delays a duration before next request
                else this.sleep(delay);
            } catch (Exception e) {
                System.out.println(e);
            }   
        }
    }
}// Customer class
