package starter.model;

import java.awt.Graphics;
import starter.Util;
import starter.game.GameObjectType;

public abstract class Bug {
  protected String icon;
  protected GameObjectType type;
  private Cell position;

  /**
   * Construct a bug.
   * @param position the Cell in a 2D grid that contains this bug.
   */
  public Bug(Cell position) {
    this.position = position;
  }

  /**
   * Get the cell which this bug is positioned in.
   * @return the cell which this bug is positioned in.
   */
  public Cell getPosition() {
    return position;
  }

  /**
   * Draw the bug icon.
   * @param graphics object.
   */
  public void draw(Graphics graphics) {
    graphics.drawImage(
        Util.getImage(icon),
        position.getScreenHorizontalCoord(),
        position.getScreenVerticalCoord(),
        Cell.CELL_PIXELS,
        Cell.CELL_PIXELS,
        null
    );
  }

  /**
   * Move this bug to the given cell.
   * @param cell a cell.
   * @return true if move succeeded or false otherwise.
   */
  public boolean moveTo(Cell cell) {
    if (cell == this.position) {
      return true;
    }
    if (cell.equals(this.position.getNorth())) {
      return moveUp();
    }
    if (cell.equals(this.position.getSouth())) {
      return moveDown();
    }
    if (cell.equals(this.position.getEast())) {
      return moveRight();
    }
    if (cell.equals(this.position.getWest())) {
      return moveLeft();
    }
    return false;
  }

  /**
   * Move this bug to the cell to its north.
   * @return true if move succeeded or false otherwise.
   */
  public boolean moveUp() {
    if (position.isLinked(position.getNorth())) {
      position.getGameObjects().remove(this.type);
      position = position.getNorth();
      position.getGameObjects().add(this.type);
      return true;
    }
    return false;
  }

  /**
   * Move this bug to the cell to its south.
   * @return true if move succeeded or false otherwise.
   */
  public boolean moveDown() {
    if (position.isLinked(position.getSouth())) {
      position.getGameObjects().remove(this.type);
      position = position.getSouth();
      position.getGameObjects().add(this.type);
      return true;
    }
    return false;
  }

  /**
   * Move this bug to the cell to its right.
   * @return true if move succeeded or false otherwise.
   */
  public boolean moveRight() {
    if (position.isLinked(position.getEast())) {
      position.getGameObjects().remove(this.type);
      position = position.getEast();
      position.getGameObjects().add(this.type);
      return true;
    }
    return false;
  }

  /**
   * Move this bug to the cell to its left.
   * @return true if move succeeded or false otherwise.
   */
  public boolean moveLeft() {
    if (position.isLinked(position.getWest())) {
      position.getGameObjects().remove(this.type);
      position = position.getWest();
      position.getGameObjects().add(this.type);
      return true;
    }
    return false;
  }
}
