package starter.model;

import starter.game.GameObjectType;

public class BugBug extends Bug {

  /**
   * Construct BugBug.
   * @param position the Cell in a 2D grid that contains BugBug.
   */
  public BugBug(Cell position) {
    super(position);
    super.icon = "icons/bugbug.png";
    super.type = GameObjectType.BUGBUG;
    position.getGameObjects().add(type);
  }
}
