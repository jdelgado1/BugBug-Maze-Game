package starter.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import starter.game.GameObjectType;

public class Cell {
  // size of cell in screen coordinates
  public static final int CELL_PIXELS = 30;
  public static final int CELL_OFFSET = 10;

  private final Set<GameObjectType> gameObjects;
  private final Set<Cell> links;
  private final int row;
  private final int column;
  private Cell north;
  private Cell south;
  private Cell east;
  private Cell west;
  private boolean isMarked;

  /**
   * Construct a Cell.
   * @param row the row index in a 2D grid containing this cell.
   * @param column the column index in a 2D grid containing this cell.
   */
  public Cell(int row, int column) {
    this.row = row;
    this.column = column;
    links = new LinkedHashSet<>();
    isMarked = false;
    gameObjects = new LinkedHashSet<>();
  }

  /**
   * Get links.
   * @return an unmodifiable set of cells that are linked to this cell.
   */
  public Set<Cell> getLinks() {
    return Collections.unmodifiableSet(links);
  }

  /**
   * Establishes a link between this and the given cell.
   * @param cell to be linked to this cell.
   */
  public void link(Cell cell) {
    if (!this.isLinked(cell)) {
      links.add(cell);
    }
    if (!cell.isLinked(this)) {
      cell.link(this);
    }
  }

  /**
   * Removes the link between this and the given cell.
   * @param cell which is linked to this cell.
   */
  public void unlink(Cell cell) {
    if (this.isLinked(cell)) {
      links.remove(cell);
    }
    if (cell.isLinked(this)) {
      cell.unlink(this);
    }
  }

  /**
   * Check if a cell is linked to this cell.
   * @param cell a Cell.
   * @return true if given cell is linked to this, false otherwise.
   */
  public boolean isLinked(Cell cell) {
    return links.contains(cell);
  }

  /**
   * Get the column index in a 2D grid containing this cell.
   * @return zero-based column index.
   */
  public int getColumn() {
    return column;
  }

  /**
   * Get the row index in a 2D grid containing this cell.
   * @return zero-based row index.
   */
  public int getRow() {
    return row;
  }

  /**
   * Get the cell to the north of this cell.
   * @return the cell to the north of this cell.
   */
  public Cell getNorth() {
    return north;
  }

  /**
   * Get the cell to the south of this cell.
   * @return the cell to the south of this cell.
   */
  public Cell getSouth() {
    return south;
  }

  /**
   * Get the cell to the west of this cell.
   * @return the cell to the west of this cell.
   */
  public Cell getWest() {
    return west;
  }

  /**
   * Get the cell to the east of this cell.
   * @return the cell to the east of this cell.
   */
  public Cell getEast() {
    return east;
  }

  /**
   * Set the cell which is to the north of this cell.
   * @param north the cell to the north of this cell.
   */
  public void setNorth(Cell north) {
    this.north = north;
    if (north.getSouth() != this) {
      north.setSouth(this);
    }
  }

  /**
   * Set the cell which is to the south of this cell.
   * @param south the cell to the south of this cell.
   */
  public void setSouth(Cell south) {
    this.south = south;
    if (south.getNorth() != this) {
      south.setNorth(this);
    }
  }

  /**
   * Set the cell which is to the east of this cell.
   * @param east the cell to the east of this cell.
   */
  public void setEast(Cell east) {
    this.east = east;
    if (east.getWest() != this) {
      east.setWest(this);
    }
  }

  /**
   * Set the cell which is to the west of this cell.
   * @param west the cell to the west of this cell.
   */
  public void setWest(Cell west) {
    this.west = west;
    if (west.getEast() != this) {
      west.setEast(this);
    }
  }

  /**
   * Check if this cell is marked.
   * @return true if this cell is marked or false otherwise.
   */
  public boolean isMarked() {
    return isMarked;
  }

  /**
   * Set if this cell is marked.
   * @param marked boolean flag to indicate if this cell is marked.
   */
  public void setMarked(boolean marked) {
    isMarked = marked;
  }

  /**
   * Get the set of GameObjectTypes in this cell.
   * @return set of GameObjectTypes in this cell.
   */
  public Set<GameObjectType> getGameObjects() {
    return gameObjects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cell cell = (Cell) o;
    return row == cell.row && column == cell.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }

  @Override
  public String toString() {
    return "[" + row + "," + column + "]";
  }

  /**
   * Draw this cell.
   * @param graphics object.
   */
  public void draw(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;

    if (isMarked()) {
      graphics2D.setColor(Color.PINK);
    } else {
      graphics2D.setColor(Color.white);
    }

    graphics2D.fillRect(
        getScreenHorizontalCoord(),
        getScreenVerticalCoord(),
        CELL_PIXELS,
        CELL_PIXELS
    );

    graphics2D.setColor(Color.black);
    drawNorthWall(graphics2D);
    drawSouthWall(graphics2D);
    drawWestWall(graphics2D);
    drawEastWall(graphics2D);
  }

  private void drawEastWall(Graphics2D g) {
    if (this.isLinked(this.getEast())) {
      dashLineStroke(g);
    } else {
      solidLineStroke(g);
    }

    g.drawLine(
        getScreenHorizontalCoord() + CELL_PIXELS,
        getScreenVerticalCoord(),
        getScreenHorizontalCoord() + CELL_PIXELS,
        getScreenVerticalCoord() + CELL_PIXELS
    );
  }

  private void drawWestWall(Graphics2D g) {
    if (this.isLinked(this.getWest())) {
      dashLineStroke(g);
    } else {
      solidLineStroke(g);
    }

    g.drawLine(
        getScreenHorizontalCoord(),
        getScreenVerticalCoord(),
        getScreenHorizontalCoord(),
        getScreenVerticalCoord() + CELL_PIXELS
    );
  }

  private void drawSouthWall(Graphics2D g) {
    if (this.isLinked(this.getSouth())) {
      dashLineStroke(g);
    } else {
      solidLineStroke(g);
    }

    g.drawLine(
        getScreenHorizontalCoord(),
        getScreenVerticalCoord() + CELL_PIXELS,
        getScreenHorizontalCoord() + CELL_PIXELS,
        getScreenVerticalCoord() + CELL_PIXELS
    );
  }

  private void drawNorthWall(Graphics2D g) {
    if (this.isLinked(this.getNorth())) {
      dashLineStroke(g);
    } else {
      solidLineStroke(g);
    }

    g.drawLine(
        getScreenHorizontalCoord(),
        getScreenVerticalCoord(),
        getScreenHorizontalCoord() + CELL_PIXELS,
        getScreenVerticalCoord()
    );
  }

  private void solidLineStroke(Graphics2D g) {
    g.setStroke(new BasicStroke(5));
  }

  private void dashLineStroke(Graphics2D g) {
    g.setStroke(new BasicStroke(1,
        BasicStroke.CAP_BUTT,
        BasicStroke.JOIN_BEVEL,
        0,
        new float[]{9}, 0
    ));
  }

  /**
   * Get horizontal screen coordinate of top-left corner of this cell.
   * @return horizontal screen coordinate of top-left corner of this cell.
   */
  public int getScreenHorizontalCoord() {
    return CELL_OFFSET + getColumn() * CELL_PIXELS;
  }

  /**
   * Get vertical screen coordinate of top-left corner of this cell.
   * @return vertical screen coordinate of top-left corner of this cell.
   */
  public int getScreenVerticalCoord() {
    return CELL_OFFSET + getRow() * CELL_PIXELS;
  }

}
