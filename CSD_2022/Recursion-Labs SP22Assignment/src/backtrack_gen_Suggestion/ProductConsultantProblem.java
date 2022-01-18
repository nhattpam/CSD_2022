
package backtrack_gen_Suggestion;

import backtrack_gen.BackTrackGenerator;
import backtrack_gen.Configuration;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author nhattpam
 */
public class ProductConsultantProblem {
    double maxBudget;//maximum budget for buying products
    int noProduct;//number of bought products
    ArrayList<ArrayList<Product>> domains; //set of domains
    boolean solved=false;
    ArrayList<Suggestion> solutions;
    //setup the problem using pre-defined budget

    public ProductConsultantProblem(double maxBudget) {
        this.maxBudget=maxBudget;
        noProduct=0;
        domains=new ArrayList();//Init the set of domains
        solutions = new ArrayList();//Init the set of solutions
    }
    //Add a product type (domain) which is required to buy
    public void addRequest(ArrayList<Product> domain){
        noProduct++;
        domains.add(domain);
    }    
    
    //Get a real solution from a configuration
    public Suggestion getSuggestion(Configuration conf){
        Suggestion suggestion = new Suggestion();
        double sumCost=0;
        for (int i = 0; i < conf.size(); i++) {
            ArrayList<Product> domain = domains.get(i);//get real domain        }
            int productIndex=conf.get(i);
            Product p = domain.get(productIndex);//get real product
            suggestion.add(p);//add real product to suggestion
            sumCost+=domain.get(productIndex).price;//compute total cost
        }
        suggestion.setCost(sumCost);//set cost of this suggestion
        return suggestion;
    }    
    //Test whether a suggestion is accepted or not
    public boolean isAccepted(Suggestion suggestion){
        return suggestion.getCost() <= this.maxBudget;
    }
    //Solve the problem
    public void solve(){
        solved=false;
        //create backtrack generator
        BackTrackGenerator gen = new BackTrackGenerator();
        //setup all domains
        for (int i = 0; i < noProduct; i++) gen.addDomain(domains.get(i));
        gen.init();//inititate the generator
        Configuration config = gen.getFirstConfig();//first configuration
        while (config!=null) { //get all accepted suggestions
            Suggestion suggestion = this.getSuggestion(config);
            if(isAccepted(suggestion)) solutions.add(suggestion);
            config = gen.nextConfiguration();
        }
        solved=true;
    }
    //Print solutions
    public void printSolutions(){
        if(solved=false) System.out.println("The problem is not solved yet!");
        else if(solutions.isEmpty()) System.out.println("No solution!");
        else{
            System.out.format("Maximum budget: %.0f\n", maxBudget);
            Collections.sort(solutions);
            for (int i = 0; i < solutions.size(); i++) {
                Suggestion sol = solutions.get(i);
                System.out.format("Solution %d: %.0f\n", (i+1), sol.getCost());
                System.out.println(sol);
            }
            System.out.println(solutions.size() + " solution(s).");
        }
    }
}//ProductConsultantProblem class
