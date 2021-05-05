package starter.model;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Random;

public class Grid implements Iterable<Cell> {

  private final int rows;
  private final int columns;
  private Cell[][] cells;

  /**
   * Construct a 2D grids.
   * @param rows number of rows.
   * @param columns number of columns.
   */
  public Grid(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    prepareGrid();
    configureCells();
  }

  private void prepareGrid() {
    cells = new Cell[rows][columns];
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        cells[row][column] = new Cell(row, column);
      }
    }
  }

  private void configureCells() {
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        if (row > 0) {
          cells[row][column].setNorth(cells[row - 1][column]);
        }
        if (row < rows - 1) {
          cells[row][column].setSouth(cells[row + 1][column]);
        }
        if (column > 0) {
          cells[row][column].setWest(cells[row][column - 1]);
        }
        if (column < columns - 1) {
          cells[row][column].setEast(cells[row][column + 1]);
        }
      }
    }
  }

  /**
   * Get a cell at the given index in this grid.
   * @param row 0-based row index.
   *        Pre: 0 <= row < rows.
   * @param col 0-based column index.
   *        Pre: 0 <= col < columns.
   * @return cell at [row,col]
   */
  public Cell getCell(int row, int col) {
    return cells[row][col];
  }

  /**
   * Get a random cell in this grid.
   * @return a random cell in this grid.
   */
  public Cell getCell() {
    Random rnd = new Random();
    int row = rnd.nextInt(rows);
    int col = rnd.nextInt(columns);
    return cells[row][col];
  }

  /**
   * Get the number of columns in this grid.
   * @return number of columns in this grid.
   */
  public int getNumColumns() {
    return columns;
  }

  /**
   * Get the number of rows in this grid.
   * @return number of rows in this grid.
   */
  public int getNumRows() {
    return rows;
  }

  /**
   * Get the total number of cells in this grid.
   * @return the total number of cells in this grid.
   */
  public int getSize() {
    return rows * columns;
  }

  @Override
  public Iterator<Cell> iterator() {
    return new GridIterator();
  }

  @Override
  public String toString() {
    StringBuilder sb = printHeader();

    for (int row = 0; row < rows; row++) {
      StringBuilder top = new StringBuilder().append("|");
      StringBuilder bottom = new StringBuilder().append("+");

      for (int col = 0; col < columns; col++) {
        Cell cell = cells[row][col];
        String space = cell.isMarked() ? " X " : "   ";
        top.append(space);
        String eastBoundary = cell.isLinked(cell.getEast()) ? " " : "|";
        top.append(eastBoundary);
        String southBoundary = cell.isLinked(cell.getSouth()) ? "   " : "---";
        bottom.append(southBoundary);
        bottom.append("+");
      }

      sb.append(top).append("\n");
      sb.append(bottom).append("\n");
    }

    return sb.toString();
  }

  private StringBuilder printHeader() {
    StringBuilder sb = new StringBuilder();
    sb.append("+");
    for (int col = 0; col < columns; col++) {
      sb.append("---+");
    }
    sb.append("\n");
    return sb;
  }

  /**
   * Draw this grid.
   * @param graphics object.
   */
  public void draw(Graphics graphics) {
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        cells[row][column].draw(graphics);
      }
    }
  }

  private class GridIterator implements Iterator<Cell> {
    private int row;
    private int col;
    private int counter;

    GridIterator() {
      row = 0;
      col = 0;
      counter = 0;
    }

    @Override
    public boolean hasNext() {
      return counter < getSize();
    }

    @Override
    public Cell next() {
      Cell cell = cells[row][col++];
      if (col == columns) {
        col = 0;
        row++;
      }
      counter++;
      return cell;
    }
  }
}
