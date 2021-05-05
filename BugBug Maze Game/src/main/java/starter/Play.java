package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import starter.game.Game;
import starter.game.GamePanel;
import starter.model.Cell;

public class Play extends JFrame {

  public static final int ROWS = 20;
  public static final int COLS = 20;
  private static final int INTERVAL = 200;
  private final Game game;
  private final GamePanel gamePanel;

  /**
   * Prepare the game.
   */
  public Play() {
    game = new Game(ROWS, COLS);
    gamePanel = new GamePanel(game);
    setTitle("Maze!");
    setSize(
        ROWS * Cell.CELL_PIXELS + 2 * Cell.CELL_OFFSET,
        COLS * Cell.CELL_PIXELS + 4 * Cell.CELL_OFFSET);
    setLocationByPlatform(true);
    getContentPane().add(gamePanel);
    addTimer();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addKeyListener(new KeyHandler());
    setVisible(true);
  }

  // initializes a timer that updates game each INTERVAL milliseconds.
  private void addTimer() {
    Timer timer = new Timer(INTERVAL, null);
    timer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        if (game.isOver()) {
          timer.stop();
        }
        game.update();
        Play.this.repaint();
      }
    });
    timer.start();
  }

  /**
   * Execution starts here.
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    new Play();
  }

  // Represents a key handler that responds to keyboard events
  private class KeyHandler extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        game.getBugBug().moveLeft();
      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        game.getBugBug().moveRight();
      } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        game.getBugBug().moveUp();
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        game.getBugBug().moveDown();
      } else if (e.getKeyCode() == KeyEvent.VK_H) {
        game.markPathToClosestFood();
      }
    }
  }
}
