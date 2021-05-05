package starter.model;

import starter.game.GameObjectType;

public class LadyBug extends Bug {

  /**
   * Construct LadyBug.
   * @param position the Cell in a 2D grid that contains LadyBug.
   */
  public LadyBug(Cell position) {
    super(position);
    super.icon = "icons/ladybug.png";
    super.type = GameObjectType.LADYBUG;
    position.getGameObjects().add(type);
  }
}
