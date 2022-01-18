
package recursion_NQueens;

import java.util.ArrayList;

/**
 * 
 * @author nhattpam
 */
public class N_Queen_Problem {
    int size, nQueens; //size of chessboard, number of queens
    ArrayList<QueenPosition> positions = null; //chessboard
    ArrayList<QueenPosition> solution;
    boolean solved = false; //is the problem solved yet?
    boolean succeeded = false; //is the problem solved successfully?

    public N_Queen_Problem(int size, int nQueens) {
        this.size = size;
        this.nQueens = nQueens;
        //setup Chessboard;
        positions = new ArrayList();
        int i, j;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                positions.add(new QueenPosition(i, j));
            }
        }
    }
    //Checking a position (pos) is valid for the index position
    public boolean reverseValid(QueenPosition pos, int index){
        if(index==0) return true;
        for (int i=index-1;  i >= 0; i--) {//test with previous positions
            QueenPosition p = solution.get(i);
            if(!pos.valid(p)) return false;
        }
        return true;
    }
    //Recursion approach for solving the n_queens problem
    private void recuriveSolve(int index){
        if(solved) return; //stop solving immediately
        int i,j;
        //Choose a position for the index queen
        for (i = 0; i<positions.size() && !solved; i++) {
            //Remove positions from the index
            j = solution.size()-1;
            while(j>=index) solution.remove(j--);
            QueenPosition pos = positions.get(i);//Choose a position
            if(reverseValid(pos, index)){//if the position is valid
                solution.add(pos);//add it to the solution
                if(index==nQueens-1)//Check completness, finished
                    solved = succeeded = true;
                else recuriveSolve(index+1);
            }
        }
    }
    private void recuriveSolve(){
        solved = succeeded = false;
        if(this.size<this.nQueens) solved = true;
        else{
            solution = new ArrayList();//Initiated solution
            recuriveSolve(0);//solve from the first queen, index=0
        }
    }
    //Test
    public static void main(String[] args) {
        int size=8;
        int nQueens=8;
        N_Queen_Problem problem = new N_Queen_Problem(size, nQueens);
        //Using recursive approach to solve the problem for only ONE solution
        problem.recuriveSolve();
        if(problem.solved){
            if(problem.succeeded){
                System.out.println("A solution:");
                System.out.println(problem.solution);
            }
            else System.out.println("No solution!");
        }
        else System.out.println("The problem is not solved yet.");
        
    }
}//N_Queen_Problem class
