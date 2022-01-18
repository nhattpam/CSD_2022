package backtrack_gen;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nhattpam
 */
public class BackTrackGenerator {

    protected int noOfSet = 0;//number of sets
    ArrayList<IntMinMaxSet> iDomains = new ArrayList();//set of int domains
    Configuration curConfig = new Configuration();//current configuration
    boolean initiated = false;//whether the generator is initiated or not?

    //Add a domain specified by min and max values: [min, max]
    public void addDomain(int min, int max) {
        IntMinMaxSet aSet = new IntMinMaxSet(min, max);
        iDomains.add(aSet);
        noOfSet++;
    }

    //Add a domain specified by a list of real objects
    public void addDomain(Collection list) {
        IntMinMaxSet aSet = new IntMinMaxSet(list);
        iDomains.add(aSet);
        noOfSet++;
    }

    //Initiate the generator
    public void init() {
        //Set up the initial configuration
        curConfig.clear();
        for (int i = 0; i < noOfSet; i++) {
            IntMinMaxSet set = iDomains.get(i);//get a set
            set.reset();//reset the state of the set
            //setup the first configuration 00000
            curConfig.add(set.nextValue());
        }
        initiated = true;
    }

    //Reset the generator
    public void reset() {
        initiated = false;
    }

    //Check whether the generator is in the last configuration
    private boolean isLastConfiguration() {//999999999
        for (int i = 0; i < noOfSet; i++) 
            if (!iDomains.get(i).isLastValue()) return false;
        return true;
    }

    //Get a copy of the current configuration
    //The curConfiguration of the generator must be reversed.
    private Configuration copyConfiguration() {
        Configuration result = new Configuration(curConfig.size());
        for (int i = 0; i < curConfig.size(); i++) {
            result.add(curConfig.get(i).intValue());
        }
        return result;
    }

    //get the first configuration
    public Configuration getFirstConfig() {
        init();
        return copyConfiguration();
    }

    //get the next configuration from the current configuration
    public Configuration nextConfiguration() {
        if (!initiated || isLastConfiguration()) return null;
        int lastIndex = noOfSet - 1;//n-1; //reverse order
        //Get the last set which is not in the end position
        //0123[4]99999 , 9: the last value --> lastIndex = 4
        while (lastIndex >= 0 && iDomains.get(lastIndex).isLastValue()) {
            lastIndex--;
        }
        //Setup next configuration: //0123[5]99999
        int nextValue = iDomains.get(lastIndex).nextValue();//5
        curConfig.update(lastIndex, nextValue);//[4] -> [5]
        if (lastIndex >= 0) {//01235[99999]
            //Reset all set after the position lastIndex: 01235[99999]
            for (int i = lastIndex+1; i < noOfSet; i++) {
                //01235[99999] --> 0123500000
                IntMinMaxSet aSet = iDomains.get(i);
                aSet.reset();//reset domain
                curConfig.update(i, aSet.nextValue());//9 -> 0
            }
        }
        return copyConfiguration();
    }
}// BackTrackGenerator class