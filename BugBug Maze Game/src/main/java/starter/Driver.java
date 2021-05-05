package starter;

//import starter.model.Grid;

//import starter.algo.Explore;
import starter.algo.Maze;
import starter.algo.Search;
import starter.model.Cell;
import starter.model.Grid;

public class Driver {

  /**
   * Demo starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {


    /*
    Grid grid = new Grid(3, 3);
    Maze.apply(grid);
    System.out.println(grid);

    Cell source = grid.getCell(0, 0);
    Explore.run(source, 2);
    System.out.println(Explore.getTrail());
     */


    Grid grid = new Grid(5, 5);
    Maze.apply(grid);
    System.out.println(grid);

    Cell source = grid.getCell(0, 0); // or grid.getCell() to get a random cell
    Cell target = grid.getCell(4, 3); // or grid.getCell() to get a random cell
    Search.run(source);

    System.out.print("Lenght of shortest path: ");
    System.out.println(Search.getDistance(target));

    Search.markPath(target);
    System.out.println(grid);


  }
}
