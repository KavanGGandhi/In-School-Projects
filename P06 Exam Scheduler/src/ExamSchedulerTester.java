/**
 * This class tests the methods created in the other class
 *
 * @author Kavan Gandhi
 */
public class ExamSchedulerTester {

  /**
   * Main method. Calls all other tests methods and prints whether they pass or fail to console
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    System.out.println(testCourse());
    System.out.println(testRoom());
    System.out.println(testScheduleAccessors());
    System.out.println(testAssignCourse());
    System.out.println(testFindAllSchedules());
    System.out.println(testFindSchedule());
  }

  /**
   * This method tests the methods from the Course class
   *
   * @return true if all methods work as intended, false if not
   */
  public static boolean testCourse() {
    // This test method tests the Course() constructor, as well as the two methods
    {
      Course testCourse = new Course("CS300", 800);
      if (!(testCourse.getName().equals("CS300"))) {
        System.out.println("There is an issue with the Course constructor or getName() method");
        return false;
      }
      if (!(testCourse.getNumStudents() == 800)) {
        System.out
            .println("There is an issue with the Course constructor or getNumStudents() method");
        return false;
      }
    }

    // This test method tests whether the correct error is thrown from the Course() constructor
    {
      try {
        Course testCourse = new Course("CS300", -800);
      } catch (IllegalArgumentException e) {
        // The correct error is thrown
      } catch (Exception e) {
        System.out.println("An unexpected error was thrown");
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the methods from the Room class
   *
   * @return true if all methods work as intended, false if not
   */
  public static boolean testRoom() {
    // This test method tests the Room() constructor, as well as the three methods
    {
      Room testRoom = new Room("Noland 168", 800);
      if (!(testRoom.getLocation().equals("Noland 168"))) {
        System.out.println("There is an issue with the Room constructor or getLocation() method");
        return false;
      }
      if (!(testRoom.getCapacity() == 800)) {
        System.out.println("There is an issue with the Room constructor or getCapacity() method");
        return false;
      }
      if (!(testRoom.reduceCapacity(50).getCapacity() == 750)) {
        System.out.println("There is an issue with the reduceCapacity() method");
        return false;
      }
    }

    // This test method tests whether the correct error is thrown from the Course() constructor
    {
      try {
        Room testRoom = new Room("Noland 168", -800);
      } catch (IllegalArgumentException e) {
        // The correct error is thrown
      } catch (Exception e) {
        System.out.println("An unexpected error was thrown");
        return false;
      }
    }

    {
      try {
        Room testRoom = new Room("Noland 168", 800);
        testRoom.reduceCapacity(850);
      } catch (IllegalArgumentException e) {
        // The correct error is thrown
      } catch (Exception e) {
        System.out.println("An unexpected error was thrown");
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the accessor methods from the schedule class
   *
   * @return true if all methods work as intended, false if not
   */
  public static boolean testScheduleAccessors() {
    // General test for different accessor methods and constructor
    {
      Course testCourse = new Course("CS300", 800);
      Course[] testCourseArray = {testCourse};
      Room testRoom = new Room("Noland 168", 800);
      Room[] testRoomArray = {testRoom};
      Schedule testSchedule = new Schedule(testRoomArray, testCourseArray);

      if (testSchedule.getNumRooms() != 1) {
        System.out.println("There is an issue with the getNumRooms() method");
        return false;
      }
      if (testSchedule.getNumCourses() != 1) {
        System.out.println("There is an issue with the getNumCourses() method");
        return false;
      }
      if (testSchedule.getRoom(0).getCapacity() != 800) {
        System.out.println("There is an issue with the getRoom() method");
        return false;
      }
      if (testSchedule.getCourse(0).getNumStudents() != 800) {
        System.out.println("There is an issue with the getCourse() method");
        return false;
      }
      if (testSchedule.isAssigned(0)) {
        System.out.println("There is an issue with the isAssigned() method");
        return false;
      }
      if (testSchedule.isComplete()) {
        System.out.println("There is an issue with the isComplete() method");
        return false;
      }
    }

    // Tests for checking whether the correct errors are thrown
    {
      Course testCourse = new Course("CS300", 800);
      Course[] testCourseArray = {testCourse};
      Room testRoom = new Room("Noland 168", 800);
      Room[] testRoomArray = {testRoom};
      Schedule testSchedule = new Schedule(testRoomArray, testCourseArray);

      try {
        testSchedule.getRoom(-1);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected error was thrown
      } catch (Exception e) {
        // Unexpected error was thrown
        return false;
      }
      try {
        testSchedule.getCourse(-1);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected error was thrown
      } catch (Exception e) {
        // Unexpected error was thrown
        return false;
      }
      try {
        testSchedule.getAssignment(-1);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected error was thrown
      } catch (Exception e) {
        // Unexpected error was thrown
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the assignCourse() method from the schedule class
   *
   * @return true if the method works as intended, false if not
   */
  public static boolean testAssignCourse() {
    // General test for assignCourse()
    {
      Course testCourse = new Course("CS300", 800);
      Course testCourse2 = new Course("CS400", 600);
      Course[] testCourseArray = {testCourse, testCourse2};
      Room testRoom = new Room("Noland 168", 800);
      Room testRoom2 = new Room("Noland 169", 800);
      Room[] testRoomArray = {testRoom, testRoom2};
      Schedule testSchedule = new Schedule(testRoomArray, testCourseArray);

      testSchedule = testSchedule.assignCourse(0, 1);
      if (!testSchedule.isAssigned(0)) {
        System.out.println("There is an issue with the assignCourse() method");
        return false;
      }
      try {
        testSchedule.assignCourse(0, 1);
      } catch (IllegalArgumentException e) {
        // expected error is thrown
      } catch (Exception e) {
        // Unexpected error is throw
        return false;
      }
      try {
        testSchedule.assignCourse(-1, -1);
      } catch (IndexOutOfBoundsException e) {
        // expected error is thrown
      } catch (Exception e) {
        // Unexpected error is throw
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the findAllSchedules() method
   *
   * @return true if the method works as intended, false if not
   */
  public static boolean testFindAllSchedules() {
    // General test for findAllSchedules()
    {
      Course testCourse = new Course("CS200", 50);
      Course testCourse2 = new Course("CS300", 110);
      Course testCourse3 = new Course("CS400", 75);
      Course[] testCourseArray = {testCourse, testCourse2, testCourse3};
      Room testRoom = new Room("Room 1", 100);
      Room testRoom2 = new Room("Room 2", 150);
      Room testRoom3 = new Room("Room 3", 75);
      Room[] testRoomArray = {testRoom, testRoom2, testRoom3};

      if (ExamScheduler.findAllSchedules(testRoomArray, testCourseArray).size() != 2) {
        System.out.println("There is an issue with the findAllSchedules() method");
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the findSchedule() method
   *
   * @return true if the method works as intended, false if not
   */
  public static boolean testFindSchedule() {
    {
      Course testCourse = new Course("CS200", 50);
      Course testCourse2 = new Course("CS300", 110);
      Course testCourse3 = new Course("CS400", 75);
      Course[] testCourseArray = {testCourse, testCourse2, testCourse3};
      Room testRoom = new Room("Room 1", 100);
      Room testRoom2 = new Room("Room 2", 150);
      Room testRoom3 = new Room("Room 3", 75);
      Room[] testRoomArray = {testRoom, testRoom2, testRoom3};

      if (ExamScheduler.findSchedule(testRoomArray, testCourseArray).getCourse(0)
          .getNumStudents() != 50) {
        System.out.println("There is an issue with the findSchedule() method");
        return false;
      }
    }
    return true;
  }
}
