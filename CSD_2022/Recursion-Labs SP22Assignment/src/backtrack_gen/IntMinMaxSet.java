
package backtrack_gen;

import java.util.Collection;

/**
 * 
 * @author nhattpam
 */
public class IntMinMaxSet {
    private int minVal=0;
    private int maxVal=0;
    int curVal = minVal-1; //current value
    //create an IntMinMaxSet = {min,...,max}
    public IntMinMaxSet(int min, int max){
        if(min>max){
            int t = min;
            min = max;
            max = t;
        }
        this.minVal=min;
        this.maxVal=max;
        curVal=minVal-1;
    }
    //Create an IntMinMaxSet from a collection
    public IntMinMaxSet(Collection collection){
        this.minVal=0;
        this.maxVal=collection.size()-1;
        curVal=minVal-1;
    }
    //Checking whether the set still has a next value
    public boolean hasNext(){
        return curVal+1<maxVal;
    }
    //Getting the next value in the set
    public int nextValue(){
        if(this==null) throw new RuntimeException("The set is empty");
        if(this.isLastValue()) throw new RuntimeException("End of the set");
        return ++curVal;
    }
    //Checking whether the curVal is the last value or not
    public boolean isLastValue(){
        return curVal==maxVal;
    }
    //reset the set to the original state
    public void reset(){
        curVal=minVal-1;
    }
}//IntMinMaxSet class
