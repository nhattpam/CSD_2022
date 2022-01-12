
package llpkg;

/**
 * 
 * @author nhattpam
 */
public interface MyIterator <E>{
    //checking whether any element is not visited.
    public boolean hasNext();
        //get data of the next element.
    public E next();
    //End of the MyIterator interface.
}
