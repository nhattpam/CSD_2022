
package backtrack_gen_uses;

import backtrack_gen.BackTrackGenerator;
import backtrack_gen.Configuration;
import java.util.ArrayList;

/**
 * 
 * @author nhattpam
 */
public class BitStringProblem {
    int nBit;
    ArrayList<Configuration> solutions;// set of solutions
    boolean solved=false;
    //Construct the problem with nBit
    public BitStringProblem(int nBit) {
        this.nBit = nBit;
        solutions = new ArrayList();
    }
    //Checking a suggestion is accepted or not.
    private boolean isAccepted(Configuration conf){
        return true;
    }
    //Solve the problem
    public void solve(){
        solved=false;
        //Create backtrack generator
        BackTrackGenerator gen = new BackTrackGenerator();
        //setup all domains are Boolean set
        for (int i = 0; i < nBit; i++) gen.addDomain(0,1);//Boolean set {0, 1}
        gen.init();//initiate the generator
        Configuration suggestion = gen.getFirstConfig();//first config.
        while (suggestion!=null) {            
            if(isAccepted(suggestion)) solutions.add(suggestion);
            suggestion = gen.nextConfiguration();
        }
        solved=true;
    }
    //Print solutions
    public void printSolutions(){
        if(!solved) System.out.println("The problem is not solved yet");
        else if(solutions.isEmpty()) System.out.println("No solution!");
        else{
            System.out.println("SOLUTION: " + this.nBit + " bit strings");
            for (Configuration sol : solutions) System.out.println(sol);
            System.out.println(solutions.size() + " solution(s).");
        }
    }
    //Test program
    public static void main(String[] args) {
        BitStringProblem prob = new BitStringProblem(5);//5
        prob.solve();
        prob.printSolutions();
    }
}//BitStringProblem class