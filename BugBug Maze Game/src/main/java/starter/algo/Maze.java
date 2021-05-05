package starter.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import starter.model.Cell;
import starter.model.Grid;

public class Maze {

  public static Random RND = new Random(37);

  /**
   * Generate a maze from the given grid.
   * @param grid a 2D grid.
   */
  public static void apply(Grid grid) {
    for (Cell cell : grid) {
      List<Cell> neighbors = new ArrayList<>();
      if (cell.getNorth() != null) {
        neighbors.add(cell.getNorth());
      }
      if (cell.getEast() != null) {
        neighbors.add(cell.getEast());
      }
      if (neighbors.size() != 0) {
        int index = RND.nextInt(neighbors.size());
        cell.link(neighbors.get(index));
      }
    }
  }
}
