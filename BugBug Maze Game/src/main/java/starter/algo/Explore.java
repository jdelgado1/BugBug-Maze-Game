package starter.algo;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import starter.model.Cell;

public class Explore {

  private static Deque<Cell> trail;
  private static Set<Cell> explored;
  private static Map<Cell, Integer> distance;
  private static int distanceLimit;

  /**
   * Run exploration process.
   * @param source Cell where exploration starts.
   * @param limit distance limit.
   */
  public static void run(Cell source, int limit) {
    distanceLimit = limit;
    explored = new HashSet<>();
    distance = new HashMap<>();
    trail = new LinkedList<>();
    trail.addLast(source);
    distance.put(source, 0);
    visit(source);
  }

  /**
   * Get the trail.
   * Pre: this.run() is already executed.
   * @return the trail.
   */
  public static Queue<Cell> getTrail() {
    return trail;
  }

  private static void visit(Cell cell) {
    if (distance.get(cell) >= distanceLimit) {
      return;
    }
    explored.add(cell);

    for (Cell c : cell.getLinks()) {
      if (!explored.contains(c)) {
        distance.put(c, distance.get(cell) + 1);
        trail.addLast(c);
        visit(c);
        trail.addLast(cell);
      }
    }

  }
}
