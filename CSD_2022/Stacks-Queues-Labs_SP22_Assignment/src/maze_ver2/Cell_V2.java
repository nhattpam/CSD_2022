
package maze_ver2;

/**
 * 
 * @author nhattpam
 */
public class Cell_V2 {
    int row, col; //cell's position
    char value; //character in the cell
    boolean blocked= false;// whether this cell is in the wall
    boolean visited= false;//a cell is visited only one time
    Cell_V2 previous = null;// for getting the path 
    //Initiate a cell

    public Cell_V2(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
    //Blocking the cell - this cell is in the wall
    public void setBlock(){
        this.blocked = true;
    }
    //Testing whether the cell can be visited
    public boolean canBeVisited(){
        return !blocked && !visited;
    }

    @Override
    public String toString() {
        return "{"  + row + ", " + col + ", " + value + "}";
    }
}//Cell_V2 class
