
package maze_ver2;

/**
 * 
 * @author nhattpam
 */
public class MyMaze01 {
    //Main program
    public static void main(String[] args) {
        String filename = "maze01.txt";
        Maze_V2 maze = new Maze_V2('E', 'M', '0', '1');
        maze.loadFromFile(filename);
        maze.print();
        maze.solve();
        if(maze.succeeded){
            System.out.println("Result path");
            System.out.println(maze.getPath());
        }
        else System.out.println("Failed");
    }//main()
}//MyMaze01
