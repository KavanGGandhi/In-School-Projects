/**
 * This class implements an interactive object that can be dropped
 * 
 * @author Kavan Gandhi
 */
public class DroppableObject extends DraggableObject {
  private InteractiveObject target; // target of this droppable object

  /**
   * Creates a new Droppable object with specific name, x and y positions, message, target, and sets
   * its next clue.
   * 
   * @param name     name to be assigned to this droppable object
   * @param x        x-position of this droppable object
   * @param y        y-position this droppable object
   * @param message  message to be assigned to this droppable object
   * @param target   target where this droppable object should be dropped
   * @param nextClue reference to an interactive object which will be activated when this droppable
   *                 object is dropped on its target.
   */
  public DroppableObject(String name, int x, int y, String message, InteractiveObject target,
      InteractiveObject nextClue) {
    super(name, x, y, message);
    this.target = target;
    this.setNextClue(nextClue);
  }

  /**
   * Checks whether this object is over another interactive object.
   * 
   * @param other reference to another interactive object.
   * @return true if this droppable object and other overlap and false otherwise.
   */
  public boolean isOver(InteractiveObject other) {
    // assigns the bottom left corner of the image to variables
    int bottomCornerX = this.getX();
    int bottomCornerXTarget = other.getX();
    int bottomCornerY = this.getY();
    int bottomCornerYTarget = other.getY();
    // assigns the top right corner of the image to variables
    int topCornerX = this.getX() + image.width;
    int topCornerXTarget = other.getX() + other.image.width;
    int topCornerY = this.getY() + image.height;
    int topCornerYTarget = other.getY() + other.image.height;

    // if any of the following conditions are false, then the image is not overlapping
    return (bottomCornerX < topCornerXTarget && bottomCornerXTarget < topCornerX
        && bottomCornerY < topCornerYTarget && bottomCornerYTarget < topCornerY);
  }

  /**
   * Stops dragging this droppable object. Also, if this droppable object is over its target and the
   * target is activated, this method (1) deactivates both this object and its target, (2) activates
   * the next clue, and (3) prints the message of this draggable object to the console.
   */
  @Override
  public void mouseReleased() {
    this.stopDragging();
    if (isOver(target) && target.isActive()) {
      this.deactivate();
      target.deactivate();
      this.activateNextClue();
      System.out.println(this.message());
    }
  }
}
