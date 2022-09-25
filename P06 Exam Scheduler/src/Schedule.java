/**
 * This is the class for the Schedule object
 *
 * @author Kavan Gandhi
 */
public class Schedule {
  private Room[] rooms;
  private Course[] courses;
  private int[] assignments;

  /**
   * This constructor creates a new schedule with the given rooms and courses arrays. Creates an
   * assignments array of the correct length where all values are -1, indicating that the
   * corresponding course has not yet been assigned a room.
   *
   * @param rooms   The given rooms array
   * @param courses The given courses array
   */
  public Schedule(Room[] rooms, Course[] courses) {
    this.rooms = rooms;
    this.courses = courses;
    assignments = new int[courses.length];
    for (int i = 0; i < assignments.length; i++) {
      assignments[i] = -1;
    }
  }

  /**
   * This constructor creates a new schedule with the given rooms, courses and assignments arrays.
   *
   * @param rooms       The given rooms array
   * @param courses     The given courses array
   * @param assignments The given assignments array
   */
  private Schedule(Room[] rooms, Course[] courses, int[] assignments) {
    this.rooms = rooms;
    this.courses = courses;
    this.assignments = assignments;
  }

  /**
   * Returns the number of rooms available in this schedule
   * 
   * @return returns the number of rooms
   */
  public int getNumRooms() {
    return rooms.length;
  }

  /**
   * Returns the Room object at the given index in the rooms array
   * 
   * @param index the index to return the Room object
   * @return returns the Room object
   * @throws IndexOutOfBoundsException with descriptive message if invalid index
   */
  public Room getRoom(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= rooms.length) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    return rooms[index];
  }

  /**
   * Returns the number of courses available in this schedule
   * 
   * @return returns the number of courses
   */
  public int getNumCourses() {
    return courses.length;
  }

  /**
   * Returns the Course object at the given index in the courses array
   * 
   * @param index the index to return the Course object
   * @return returns the Course object
   * @throws IndexOutOfBoundsException with descriptive message if invalid index
   */
  public Course getCourse(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= courses.length) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    return courses[index];
  }

  /**
   * Returns whether the index is assigned or not
   * 
   * @param index the index to return the Course object
   * @return returns true if and only if the course at the given index has been assigned a room;
   *         false otherwise
   */
  public boolean isAssigned(int index) {
    if (assignments[index] == -1) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Returns the Room object assigned to the course at the given index
   * 
   * @param index the index to return the Course object
   * @return returns the Room object assigned to course at the given index
   * @throws IllegalArgumentException  with a descriptive message if the course has not been
   *                                   assigned a room
   * @throws IndexOutOfBoundsException with a descriptive error message if the given course index is
   *                                   invalid
   */
  public Room getAssignment(int index) throws IllegalArgumentException, IndexOutOfBoundsException {
    if (index < 0 || index >= rooms.length) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    if (!isAssigned(index)) {
      throw new IllegalArgumentException("The course has not been assigned a room yet");
    }
    return rooms[assignments[index]];
  }

  /**
   * Returns whether the array is full (complete) or not
   * 
   * @return returns true if and only if all courses have been assigned to rooms; false otherwise
   */
  public boolean isComplete() {
    for (int i = 0; i < assignments.length; i++) {
      if (assignments[i] == -1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns a new schedule object with the course index assigning to the room index
   * 
   * @param indexCourse the index of the course array
   * @param indexRoom   the index of the room array
   * @return returns new schedule object with course assigned to the room
   * @throws IllegalArgumentException  with a descriptive error message if the given course has
   *                                   already been assigned a room, or if the room does not have
   *                                   sufficient capacity
   * @throws IndexOutOfBoundsException with a descriptive error message if either index is invalid
   */
  public Schedule assignCourse(int indexCourse, int indexRoom)
      throws IllegalArgumentException, IndexOutOfBoundsException {
    if (indexCourse < 0 || indexCourse >= courses.length || indexRoom < 0
        || indexRoom >= rooms.length) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    if (isAssigned(indexCourse)) {
      throw new IllegalArgumentException("The course has already been assigned a room");
    }
    if (rooms[indexRoom].getCapacity() < courses[indexCourse].getNumStudents()) {
      throw new IllegalArgumentException("The room lacks sufficient capacity");
    }

    Schedule returnSchedule = new Schedule(rooms.clone(), courses.clone(), assignments.clone());
    returnSchedule.assignments[indexCourse] = indexRoom;
    returnSchedule.rooms[indexRoom] =
        rooms[indexRoom].reduceCapacity(courses[indexCourse].getNumStudents());
    return returnSchedule;
  }

  /**
   * Overriden toString method to convert schedule object into string
   * 
   * @return Schedule object converted to string
   */
  public String toString() {
    String returnString = "{";
    for (int i = 0; i < courses.length; i++) {
      returnString += courses[i].getName() + ": "
          + (isAssigned(i) ? rooms[assignments[i]].getLocation() : "Unassigned");
      if (i != courses.length - 1) {
        returnString += ", ";
      }
    }
    returnString += "}";
    return returnString;
  }
}
