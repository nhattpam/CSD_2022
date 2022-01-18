
package backtrack_gen_Suggestion;

import java.util.ArrayList;

/**
 * 
 * @author nhattpam
 */
//A customer likes to buy 3 TVS, 2 refrigerators, 1 fan
//with max budget = 30000000
public class ProductConsultantTest {
    public static void main(String[] args) {
        int noOfTVs = 3;
        int noOfRefrigerators = 2;
        int noOfFans = 1;
        //Domains may be created from a datasource (file, database).
        //Create domain of TVs
        ArrayList<Product> tvDom = new ArrayList();
        tvDom.add(new Product("TV01", "Sony 42", 4800000));
        tvDom.add(new Product("TV02", "LG 42", 3800000));
        tvDom.add(new Product("TV03", "Samsung 48", 7500000));
        tvDom.add(new Product("TV04", "Sony 55", 14800000));
        tvDom.add(new Product("TV05", "LG 65", 16800000));
        tvDom.add(new Product("TV06", "Samsung 55", 11200000));
        
        //Create domain of refrigerators
        ArrayList<Product> rfDom = new ArrayList();
        rfDom.add(new Product("RF01", "National 420", 11800000));
        rfDom.add(new Product("RF02", "General 380", 10800000));
        rfDom.add(new Product("RF03", "Panasonic 180", 3500000));
        rfDom.add(new Product("RF04", "Hitachi 380", 9800000));
        rfDom.add(new Product("RF05", "Beko 250", 6700000));
        
        //Create domain of fans
        ArrayList<Product> fanDom = new ArrayList();
        fanDom.add(new Product("F01", "National F11", 800000));
        fanDom.add(new Product("F02", "General F02", 750000));
        fanDom.add(new Product("F03", "Panasonic F80", 1050000));
        //create a problem
        double budget = 30000000;
        ProductConsultantProblem problem = new ProductConsultantProblem(budget);
        //3 TVs, 2 refrigerators, 1 fan
        int i;
        for (i = 0; i < noOfTVs; i++) problem.addRequest(tvDom);//3 TVs
        for (i = 0; i < noOfRefrigerators; i++) problem.addRequest(rfDom); //2 refr
        for (i = 0; i < noOfFans; i++) problem.addRequest(fanDom); //1 fan
        //Solve the problem 
        problem.solve();
        problem.printSolutions();
    }
}//ProductConsultanTest class
