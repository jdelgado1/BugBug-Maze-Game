package starter.algo;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import starter.model.Cell;


public class Search {

  private static Map<Cell, Integer> distance;
  private static Map<Cell, Cell> previous;
  private static Set<Cell> explored;
  private static Queue<Cell> trail;
  private static Cell first;

  /**
   * Run search process.
   * @param start Cell where search starts.
   */
  public static void run(Cell start) {
    first = start;
    distance = new HashMap<>();
    previous = new HashMap<>();
    trail = new LinkedList<>();
    explored = new HashSet<>();
    modifiedBfs(start);
  }

  private static void modifiedBfs(Cell start) {
    trail.add(start);
    distance.put(start, 0);
    explored.add(start);
    while (!trail.isEmpty()) {
      Cell v = trail.poll();
      for (Cell c : v.getLinks()) {
        if (!explored.contains(c)) {
          explored.add(c);
          trail.add(c);
          previous.put(c, v);
          distance.put(c, getDistance(v) + 1);
        }
      }
    }
  }

  /**
   * Get the distance from source to target cell.
   * Pre: this.run() is already executed.
   * @param target the target cell.
   * @return the distance from source to target cell.
   */
  public static Integer getDistance(Cell target) {
    return distance.get(target);
  }

  /**
   * Mark the shortest path from source to target,
   * Pre: this.run() is already executed.
   * @param target the target cell.
   */
  public static void markPath(Cell target) {

    Cell c = target;
    Stack<Cell> stack = new Stack<>();
    stack.push(c);
    while (!c.equals(first)) {
      c = previous.get(c);
      stack.push(c);
    }

    for (Cell t : stack) {
      t.setMarked(true);
    }


    // TODO Implement me!

    // Hint: you should iterate over all the cells
    // from source to target and for each cell do
    //   cell.setMarked(true)
  }
}
