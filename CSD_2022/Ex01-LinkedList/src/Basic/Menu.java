
package Basic;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author nhattpam
 */
public class Menu extends ArrayList<String>{
    public int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu to choose:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i+1) + ". " + this.get(i) + ".");
        }
        System.out.println("\tChoose an option: ");
        int choice = sc.nextInt();
        return choice;
    }
}