package starter.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import starter.algo.Explore;
//import starter.algo.Maze;
import starter.algo.Prim;
import starter.algo.Search;
import starter.model.BugBug;
import starter.model.Cell;
import starter.model.Food;
import starter.model.Grid;
import starter.model.LadyBug;

public class Game {

  private static final double FOOD_PORTION = 0.04;
  private static final double BUG_PORTION = 0.02;
  private final Grid grid;
  private final List<LadyBug> ladyBugs;
  private final List<Food> foods;
  private final Random random;
  private BugBug bugBug;
  private boolean gameOver;
  private GameStatus gameStatus;
  private HashMap<LadyBug, Queue<Cell>> ladyBugTrails;

  /**
   * Instantiate a Game.
   * @param rows number of rows.
   * @param columns number of columns.
   */
  public Game(int rows, int columns) {
    grid = new Grid(rows, columns);
    // TODO once you have implemented Prim's algorithm,
    //  replace "Maze" with "Prim" in the line below.
    Prim.apply(grid);
    //Maze.apply(grid);
    random = new Random();
    ladyBugs = new ArrayList<>();
    foods = new ArrayList<>();
    placeBugBug();
    distributeFood();
    distributeLadyBugs();
    setupLadyBugTrails();
    gameOver = false;
    gameStatus = GameStatus.ONGOING;
  }

  // Place bugbug at top left corner.
  private void placeBugBug() {
    Cell cell = grid.getCell(0, 0);
    bugBug = new BugBug(cell);
  }

  private void distributeFood() {
    int numFood = (int) (FOOD_PORTION * grid.getSize());
    numFood = Math.max(numFood, 1); // we need at least one food!
    // always place a food on the bottom right corner
    Cell bottomRight = grid.getCell(grid.getNumRows() - 1,
        grid.getNumColumns() - 1);
    foods.add(new Food(bottomRight));
    numFood--;
    while (numFood > 0) {
      int row = random.nextInt(grid.getNumRows());
      int col = random.nextInt(grid.getNumColumns());
      Cell cell = grid.getCell(row, col);
      if (cell.getGameObjects().isEmpty()) {
        foods.add(new Food(cell));
        numFood--;
      }
    }
  }

  private void distributeLadyBugs() {
    int numBugs = (int) (BUG_PORTION * grid.getSize());
    numBugs = Math.max(numBugs, 1); // we need at least one lady bug!
    while (numBugs > 0) {
      int row = random.nextInt(grid.getNumRows());
      int col = random.nextInt(grid.getNumColumns());
      if (row == 0 || col == 0) {
        continue; // don't place lady bugs too close to bug bug
      }
      Cell cell = grid.getCell(row, col);
      if (cell.getGameObjects().isEmpty()) {
        ladyBugs.add(new LadyBug(cell));
        numBugs--;
      }
    }
  }

  // Pre: distributeLadyBugs() is already called.
  private void setupLadyBugTrails() {
    ladyBugTrails = new HashMap<>();
    int rows = grid.getNumRows();
    int cols = grid.getNumColumns();
    int dist = Math.min(rows, cols) / 2;
    for (LadyBug ladyBug : ladyBugs) {
      Explore.run(ladyBug.getPosition(), dist);
      ladyBugTrails.put(ladyBug, Explore.getTrail());
    }
  }

  /**
   * Get the status of game.
   * @return the status of game.
   */
  public GameStatus getGameStatus() {
    return gameStatus;
  }

  /**
   * Check if game is over.
   * @return tru if game is over and false otherwise.
   */
  public boolean isOver() {
    return gameOver;
  }

  /**
   * Get Bugbug.
   * @return Bugbug, the caterpillar.
   */
  public BugBug getBugBug() {
    return bugBug;
  }

  /**
   * Get the collections of ladybugs.
   * @return the collections of ladybugs.
   */
  public List<LadyBug> getLadyBugs() {
    return Collections.unmodifiableList(ladyBugs);
  }

  /**
   * Get the collection of foods.
   * @return the collection of foods.
   */
  public List<Food> getFoods() {
    return Collections.unmodifiableList(foods);
  }

  /**
   * Get the underlying grid.
   * @return the underlying grid.
   */
  public Grid getGrid() {
    return grid;
  }

  /**
   * Update the game.
   */
  public void update() {
    removeEatenFoods();

    if (foods.isEmpty()) {
      gameOver = true;
      gameStatus = GameStatus.WON;
      return;
    }

    for (LadyBug ladyBug : ladyBugs) {
      if (bugBug.getPosition() == ladyBug.getPosition()) {
        gameOver = true;
        gameStatus = GameStatus.LOST;
        return;
      }
    }
    moveBugs();
  }

  private void removeEatenFoods() {
    foods.removeIf(food -> bugBug.getPosition() == food.getPosition());
  }

  private void moveBugs() {
    for (LadyBug ladyBug : ladyBugs) {
      Cell next = ladyBugTrails.get(ladyBug).poll();
      ladyBug.moveTo(next);
      //next.setMarked(true);
      ladyBugTrails.get(ladyBug).add(next);
    }
  }

  /**
   * Mark the cells on the shortest path from
   * where bugbug is to the closest food.
   */
  public void markPathToClosestFood() {
    Search.run(bugBug.getPosition());
    Food closest = null;
    int dist = Integer.MAX_VALUE;
    for (Food food : foods) {
      Integer d = Search.getDistance(food.getPosition());
      if (d != null && d < dist) {
        dist = d;
        closest = food;
      }
    }
    if (closest != null) {
      Search.markPath(closest.getPosition());
    }
  }
}
