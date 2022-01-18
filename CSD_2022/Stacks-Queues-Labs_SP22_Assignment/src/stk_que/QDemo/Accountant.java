
package stk_que.QDemo;

import java.util.LinkedList;

/**
 * 
 * @author nhattpam
 */
public class Accountant implements Runnable{
    LinkedList<String> queue;//linked list is used as a queue
    int duty;//number invoices per working session
    int count = 0;//number of processing invoices
    //Creating an accountant with a duty and pre-defined queue

    public Accountant(int duty, LinkedList<String> queue) {
        this.duty = duty;
        this.queue = queue;
    }
    
    //Activity of the accountant --> printing invoices
    @Override
    public void run() {
        while (count<duty) {            
            try {
                if(!queue.isEmpty()){//there are still invoices
                    count++;//one more invoice must be processed
                    String invoiceInfo =  "Invoice " + count + "/" + duty +
                            ": " + queue.removeFirst();//customer info + money
                    System.out.println(invoiceInfo);//printing 1 invoice
                }
                //the duty completed, accountant stops working 
                //current thread is the thread which is using this method
                if(count==duty) Thread.currentThread().yield();
                else Thread.currentThread().sleep(500);//pause 0.5 sec
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
}//Accountant class
