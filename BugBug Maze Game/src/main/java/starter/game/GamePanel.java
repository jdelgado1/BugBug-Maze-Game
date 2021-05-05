package starter.game;

import java.awt.Graphics;
import javax.swing.JPanel;
import starter.Util;
import starter.model.Bug;
import starter.model.Food;

public class GamePanel extends JPanel {

  private final Game game;
  private final String gameWonIcon;
  private final String gameOverIcon;

  /**
   * Construct GamePanel.
   * @param game object.
   */
  public GamePanel(Game game) {
    super();
    this.game = game;
    gameWonIcon = "icons/youwon.png";
    gameOverIcon = "icons/gameover.png";
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    if (game.getGameStatus().equals(GameStatus.LOST)) {
      drawGameOver(graphics);
    } else if (game.getGameStatus().equals(GameStatus.WON)) {
      drawGameWon(graphics);
    } else {
      game.getGrid().draw(graphics);
      drawGameObjects(graphics);
    }
  }

  private void drawGameObjects(Graphics g) {
    if (game.getBugBug() != null) {
      game.getBugBug().draw(g);
    }
    for (Food food : game.getFoods()) {
      food.draw(g);
    }
    for (Bug food : game.getLadyBugs()) {
      food.draw(g);
    }
  }

  private void drawGameWon(Graphics g) {
    g.drawImage(
        Util.getImage(gameWonIcon),
        0,
        0,
        getWidth(),
        getHeight(),
        null
    );
  }

  private void drawGameOver(Graphics g) {
    g.drawImage(
        Util.getImage(gameOverIcon),
        0,
        0,
        getWidth(),
        getHeight(),
        null
    );
  }
}
