package backtrack_gen_uses;

import backtrack_gen.BackTrackGenerator;
import backtrack_gen.Configuration;
import java.util.ArrayList;

/**
 *
 * @author nhattpam
 */
public class BuffaloesProblem {

    int noOfBuffaloes;//generalize the buffaloes problem
    int noOfBunches;
    ArrayList<Configuration> solutions;//set of solutions
    boolean solved = false;

    //create a buffaloes problem
    public BuffaloesProblem(int noOfBuffaloes, int noOfBunches) {
        this.noOfBuffaloes = noOfBuffaloes;
        this.noOfBunches = noOfBunches;
        solutions = new ArrayList();//Init solution set
    }

    //Checking a suggestion is accepted or not
    private boolean isAccepted(Configuration suggestion) {
        int small = suggestion.get(0);
        int big = suggestion.get(1);
        int old = suggestion.get(2);
        if (old % 2 == 1) {
            return false;
        }
        int noBuffaloes = small + big + old;
        return (noBuffaloes == this.noOfBuffaloes)
                && (small * 1 + big * 2 + old / 2 == this.noOfBunches);
    }

    public void solve() {
        solved = false;
        //Create backtrack generator
        BackTrackGenerator gen = new BackTrackGenerator();
        //setup all domains
        gen.addDomain(0, noOfBunches);//Domain of calfs (little buffaloes)
        gen.addDomain(0, noOfBunches / 2);//Domain of Big buffaloes
        gen.addDomain(0, noOfBunches);//Domain of old buffaloes
        gen.init();//initiate the generator
        Configuration suggestion = gen.getFirstConfig();//first configuration
        while (suggestion!=null) {            
            if(isAccepted(suggestion)) solutions.add(suggestion);
            suggestion=gen.nextConfiguration();
        }
        solved=true;
    }
    //Print solutions
    public void printSolutions(){
        if(!solved) System.out.println("The problem is not solved yet");
        else if(solutions.isEmpty()) System.out.println("No solution!");
        else{
            System.out.println("SOLUTIONS:\n<small, big, old>");
            for (Configuration sol : solutions) System.out.println(sol);
            System.out.println(solutions.size() + " solution(s).");
        }
    }
    //Test program
    public static void main(String[] args) {
        BuffaloesProblem prob = new BuffaloesProblem(50, 50);
        prob.solve();
        prob.printSolutions();
    }
}//BuffaloesProblem class
