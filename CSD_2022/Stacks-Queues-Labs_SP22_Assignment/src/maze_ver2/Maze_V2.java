
package maze_ver2;

import java.io.BufferedReader;//for reading lines
import java.io.File;
import java.io.FileReader;//for reading characters
import java.util.ArrayList;//for storing adjacent cells
import java.util.LinkedList;//for a stack

/**
 * 
 * @author nhattpam
 */
public class Maze_V2 {
    char entryChar = 'E', destChar = 'M';
    char emptyChar = '0', blockedChar  = '1';
    int rows= 0, cols = 0; //size of the maze
    Cell_V2[][] cells = null;//map of the maze
    Cell_V2 entryCell = null; //Entry position of the maze
    Cell_V2 destCell = null; //exit cell or destination cell
    boolean completed = false;// solving completed or not
    boolean succeeded = false;// solving succeeded or not
   //Constructor using default characters

    public Maze_V2() {
    }
    //Constructor using specificed characters
    public Maze_V2(char entryChar, char destChar,
                   char emptryChar, char blockedChar) {
        this.entryChar = entryChar;
        this.destChar = destChar;
        this.entryChar = entryChar;
        this.blockedChar = blockedChar;
    }
    //Printing the maze
    public void print(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(cells[i][j].value);
            }
            System.out.println();
        }
    }
    //Testing whether a position is valid or not
    private boolean isValid(int row, int col){
        return row>=0 && row < rows && //it is in the board
                col >=0 && col < cols &&//and the cell at this position
                cells[row][col].canBeVisited();//can be moved to
    }
    //Getting 4 adjacency cells of the current cell
    private ArrayList<Cell_V2> getAdjs (Cell_V2 curCell){
        ArrayList<Cell_V2> adjs = new ArrayList();
        int row = curCell.row, col = curCell.col;
        //Testing 4 adjacency cells of the current cell
        if(isValid(row-1, col)){//UPPER adjacency cell
            cells[row-1][col].previous = curCell;
            adjs.add(cells[row-1][col]);
        }
        if(isValid(row+1, col)){//LOWER adjacency cell
            cells[row+1][col].previous = curCell;
            adjs.add(cells[row+1][col]);
        }
        if(isValid(row,col-1)){//LEFT adjacency cell
            cells[row][col-1].previous = curCell;
            adjs.add(cells[row][col-1]);
        }
        if(isValid(row,col+1)){//RIGHT adjacency cell
            cells[row][col+1].previous = curCell;
            adjs.add(cells[row][col+1]);
        }
        return adjs;
    }
    //Load a maze from a text file
    public boolean loadFromFile(String filename){
        File f = new File(filename);
        if(!f.exists()){//checking existence of the file
            System.out.println("The file " + filename + " doesn't existed!");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader(f);//reading characters
            BufferedReader bf  = new BufferedReader(fr);//reading lines
            ArrayList<String> list = new ArrayList();//File --> string list
            String line;
            //Loading all lines in the file to list
            while ((line = bf.readLine()) !=null ) {                
                line = line.trim();//normalizing each line
                if(line.length()>0) list.add(line.toUpperCase());
            }
            //Closing the file
            bf.close();
            fr.close();
            //Creating the matrix from the list
            this.rows = list.size(); //Determining number of rows
            this.cols = list.get(0).length();//Determining number of columns
            this.cells = new Cell_V2[rows][cols];//Allocating memory of matrix
            for (int i = 0; i < list.size(); i++) {//Creating cells
                line = list.get(i);//Getting a line: 101M1010101
                for (int j = 0; j < cols; j++) {//Creating cells in the row i
                    char ch = line.charAt(j);//101M1010101
                    cells[i][j] = new Cell_V2(i, j, ch);
                    if(ch==blockedChar) cells[i][j].setBlock();
                    //Determining the entry and destination of the maze
                    else if (ch==entryChar) this.entryCell = cells[i][j];
                    else if (ch==destChar) this.destCell = cells[i][j];
                    
                }
                
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        return true;
    }//loadFromFile (String filename)
    
    //Solving the maze
    public boolean solve(){
        LinkedList<Cell_V2> stack = new LinkedList(); //Initiating a stack
        Cell_V2 curCell = this.entryCell;//starting at the entry of the maze
        while (!completed) { //finding a solution
            curCell.visited = true;//marking curCell as visited
            if(curCell==this.destCell)//succeeded
                completed = succeeded = true;
            else{
                ArrayList<Cell_V2> adjs = getAdjs(curCell);// adjacency cells
                //if there are adjacency cells to move to
                if(adjs.size()>0){
                    curCell = adjs.get(0);// move to the next cell
                    for (int i = 0; i < adjs.size(); i++) {// save others to stack
                        stack.addFirst(adjs.get(i));;
                    }
                }
                //else if stack contains a cell which can try
                else if(!stack.isEmpty()) //if having a cell can be examined.
                    curCell=stack.removeFirst();//Popping a cell from stack
                else{// stack is empty --> No solution, failed
                    completed = true;
                    succeeded = true;
                }
            }
        }
        return completed;
    }//solved
    
    //Getting the path of solution
    public LinkedList<Cell_V2> getPath(){
        if(!succeeded) return null;
        LinkedList<Cell_V2> path = new LinkedList();
        //Reverse traversing to get the result path
        Cell_V2 cell = this.destCell;
        while (cell!=null) {            
            path.addFirst(cell);
            cell = cell.previous;
        }
        return path;
    }
}//Maze_V2 class
