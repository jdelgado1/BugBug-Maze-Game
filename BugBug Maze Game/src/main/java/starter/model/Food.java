package starter.model;

import java.awt.Graphics;
import starter.Util;
import starter.game.GameObjectType;

public class Food {
  private final String icon;
  private final Cell position;
  private final GameObjectType type;

  /**
   * Construct Food.
   * @param position the Cell in a 2D grid that contains Food.
   */
  public Food(Cell position) {
    this.position = position;
    type = GameObjectType.FOOD;
    position.getGameObjects().add(type);
    icon = "icons/food.png";
  }

  public Cell getPosition() {
    return position;
  }

  /**
   * Draw the food icon.
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
}
