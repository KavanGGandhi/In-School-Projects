/**
 * This is the class for the Room object
 *
 * @author Kavan Gandhi
 */
public class Room {
  private String location;
  private int capacity;

  /**
   * This constructor creates a new room with the given location and capacity
   *
   * @param location The given location
   * @param capacity The given capacity
   * @throws IllegalArgumentException with a descriptive error message if capacity is negative
   */
  public Room(String location, int capacity) throws IllegalArgumentException {
    if (capacity < 0) {
      throw new IllegalArgumentException("The capactiy can not be negative");
    }
    this.location = location;
    this.capacity = capacity;
  }

  /**
   * Returns the location of this room
   * 
   * @return returns the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Returns the capacity of this room
   * 
   * @return returns the name
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Returns a new room object with the same location as this one, but with a capacity less than
   * this one's by the argument's amount.
   * 
   * @return a new room object
   */
  public Room reduceCapacity(int capacityToReduce) throws IllegalArgumentException {
    if (capacityToReduce > capacity) {
      throw new IllegalArgumentException("Capacity to reduce can not be greater than capacity");
    }
    Room reducedRoom = new Room(location, capacity - capacityToReduce);
    return reducedRoom;
  }
}
