/**
 * This class implements a graphic clickable restart button
 * 
 * @author Kavan Gandhi
 */
public class RestartGameButton extends Button {
  /**
   * Creates a new RestartGameButton object labeled "Restart Game" at a specific position on the
   * screen
   *
   * @param x x-position to be assigned to this restart game button
   * @param y y-position to be assigned to this restart game buttons
   */
  public RestartGameButton(int x, int y) {
    super("Restart Game", x, y);
  }

  /**
   * Defines the behavior of this button when the mouse is pressed. This button initializes the game
   * if it is clicked (meaning if the mouse is over it)
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      ((TreasureHunt) processing).initGame();
    }
  }
}
