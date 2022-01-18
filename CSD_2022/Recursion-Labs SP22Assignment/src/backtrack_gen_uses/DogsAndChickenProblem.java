
package backtrack_gen_uses;

import backtrack_gen.BackTrackGenerator;
import backtrack_gen.Configuration;
import java.util.ArrayList;

/**
 * 
 * @author nhattpam
 */
public class DogsAndChickenProblem {
    int nLegs=0; //number of legs
    ArrayList<Configuration> solutions; //set of solutions
    boolean solved=false;
    
    //create a dogandchicken problem
    public DogsAndChickenProblem(int nLegs){
        if(nLegs>0) this.nLegs=nLegs;
        solutions = new ArrayList();//Init solution set
    }
    
    //Checking a suggestion is accepted or not.
    private boolean isAccepted(Configuration suggestion){
        int dogs = suggestion.get(0);
        int chickens = suggestion.get(1);
        return (dogs*4 + chickens*2) == nLegs;
    }
    
    public void solve(){
        solved=false;
        //Create backtrack generator
        BackTrackGenerator gen = new BackTrackGenerator();
        //setup domains for dogs and chickens
        gen.addDomain(1, (nLegs-2)/4); //Dog domain: At least ONE chicken
        gen.addDomain(1, (nLegs-2)/2); //Chicken domain: At least ONE dog
        gen.init(); //initiate the generator
        Configuration suggestion = gen.getFirstConfig(); //first configuration
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
            System.out.println("SOLUTIONS: \n<dogs, chickens>");
            for (Configuration sol : solutions) System.out.println(sol);
            System.out.println(solutions.size() + " solution(s).");
        }
    }
    //Test program
    public static void main(String[] args) {
        int nLegs = 100;
        DogsAndChickenProblem prob = new DogsAndChickenProblem(nLegs);
        prob.solve();
        prob.printSolutions();
    }
}//DogsAndChickenProblem class
