/**
 * This is the class for the Course object
 *
 * @author Kavan Gandhi
 */
public class Course {
  private String name;
  private int numStudents;

  /**
   * This constructor creates a new course with the given name and number of students
   *
   * @param name        The given name
   * @param numStudents The given number of students
   * @throws IllegalArgumentException with a descriptive error message if numStudents is negative
   */
  public Course(String name, int numStudents) throws IllegalArgumentException {
    if (numStudents < 0) {
      throw new IllegalArgumentException("The number of students can not be negative");
    }
    this.name = name;
    this.numStudents = numStudents;
  }

  /**
   * Returns the name of this course
   * 
   * @return returns the name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the number of students enrolled in this course
   * 
   * @return returns the number of students
   */
  public int getNumStudents() {
    return numStudents;
  }
}
