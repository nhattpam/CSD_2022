
package recursion_NQueens;

/**
 * 
 * @author nhattpam
 */
public class QueenPosition {
    int x, y;

    public QueenPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Checking whether 2 queen positions are valid or not
    public boolean valid(QueenPosition p){
        //violations in vertical, horizontal and diagonal directions
        int dx = this.x - p.x;
        if(dx<0) dx=-dx;
        int dy = this.y-p.y;
        if(dy<0) dy=-dy;    
        return (dx==0 || dy ==0 || dx==dy) ? false : true;
    }

    @Override
    public String toString() {
        return "{" +  x + ", " + y + "}";
    }
}//QueenPosition class
