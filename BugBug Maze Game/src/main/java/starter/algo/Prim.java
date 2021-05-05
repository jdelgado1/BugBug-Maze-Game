package starter.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import starter.model.Cell;
import starter.model.Grid;
//import java.util.Objects;


public class Prim {

  public static Random RND = new Random();

  private static Map<Cell, Integer> distances = new HashMap<>();
  //private static Map<Cell, Integer> weights = new HashMap<>();
  private static Map<Cell, Cell> previous = new HashMap<>();
  private static Set<Cell> explored = new HashSet<>();
  //private static Queue<Cell> trail = new LinkedList<>();





  /*
  private static class CellWeightPair implements Comparable<CellWeightPair> {
    Cell cell;
    int weight;

    CellWeightPair(Cell cell, int weight) {
      this.cell = cell;
      this.weight = weight;
    }

    @Override
    public int compareTo(CellWeightPair other) {
      return (int) (Math.signum(this.weight - other.weight));
    }


  }
   */

  private static ArrayList<Cell> neighbors(Cell s) {
    ArrayList<Cell> neighbors = new ArrayList<>();
    if (s.getNorth() != null) {
      neighbors.add(s.getNorth());
    }
    if (s.getEast() != null) {
      neighbors.add(s.getEast());
    }


    if (s.getSouth() != null) {
      neighbors.add(s.getSouth());
    }
    if (s.getWest() != null) {
      neighbors.add(s.getWest());
    }
    return neighbors;
  }

  private static void primHelper(Queue<Cell> q) {
    while (!q.isEmpty()) {
      Cell c = q.remove();
      explored.add(c);
      ArrayList<Cell> neighbors = neighbors(c);
      for (Cell n : neighbors) {
        int neighDist = RND.nextInt(1000); //infinity (4) + 1
        if (!explored.contains(n) && neighDist < distances.get(n)) {
          distances.put(n, neighDist);
          q.add(n);
          previous.put(n, c);
        }
      }
    }
  }

  /**
   * Generate a maze from the given grid.
   *
   * @param grid a 2D grid.
   */
  public static void apply(Grid grid) {
    for (Cell cell : grid) {
      distances.put(cell, Integer.MAX_VALUE);
    }

    Queue<Cell> q = new LinkedList<>();

    Cell v = grid.getCell();

    q.add(v);
    //explored.add(s);

    distances.put(v, 0);



    primHelper(q);


    for (Map.Entry<Cell, Cell> entry : previous.entrySet()) {
      entry.getKey().link(entry.getValue());
    }






  }










}
