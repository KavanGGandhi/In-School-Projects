import java.util.ArrayList;

/**
 * This class holds different recursive methods that create schedules
 *
 * @author Kavan Gandhi
 */
public class ExamScheduler {

  /**
   * Returns a valid Schedule for the given set of rooms and courses
   *
   * @param rooms   The given rooms array
   * @param courses The given courses array
   * @return schedule with assigned courses
   * @throws IllegalStateException with descriptive message if no such schedule exists
   */
  public static Schedule findSchedule(Room[] rooms, Course[] courses) throws IllegalStateException {
    Schedule schedule = new Schedule(rooms, courses);
    return findScheduleHelper(schedule, 0);
  }

  /**
   * Recursive method that assigns all unassigned courses in a Schedule beginning from the index
   * provided as an argument
   *
   * @param schedule The given schedule object
   * @param index    The index to start with
   * @return schedule with assigned courses
   * @throws IllegalStateException with descriptive message if no such schedule exists
   */
  private static Schedule findScheduleHelper(Schedule schedule, int index)
      throws IllegalStateException {
    // returns schedule if it is complete, base case
    if (index == schedule.getNumCourses()) {
      if (schedule.isComplete()) {
        return schedule;
      } else {
        throw new IllegalStateException("The schedule is invalid");
      }
    }
    // recursion occurs if current index course is already assigned
    if (schedule.isAssigned(index)) {
      return findScheduleHelper(schedule, index + 1);
      // if not assigned, find the lowest difference, save the index, assign course to index
    } else {
      int difference = -1;
      int closestRoom = 0;
      for (int i = 0; i < schedule.getNumCourses(); i++) {
        try {
          Schedule tempSchedule = schedule.assignCourse(index, i);
          int newDifference =
              schedule.getRoom(i).getCapacity() - schedule.getCourse(i).getNumStudents();
          if (difference > newDifference || difference == -1) {
            closestRoom = i;
            difference = newDifference;
          }
        } catch (IllegalArgumentException e) {
          // catches error and skips to next loop
        }
      }
      if (difference == -1) {
        throw new IllegalStateException("The schedule is invalid");
      }
      // second recursive case
      return findScheduleHelper(schedule.assignCourse(index, closestRoom), index + 1);
    }
  }

  /**
   * Returns all valid Schedules for the given set of rooms and courses in an ArrayList
   *
   * @param rooms   The given rooms array
   * @param courses The given courses array
   * @return ArrayList containing all valid schedules
   */
  public static ArrayList<Schedule> findAllSchedules(Room[] rooms, Course[] courses) {
    Schedule schedule = new Schedule(rooms, courses);
    return (findAllSchedulesHelper(schedule, 0));
  }

  /**
   * Recursive method that assigns all unassigned courses in a Schedule in all possible ways,
   * beginning from the index provided as an argument
   *
   * @param schedule The given schedule object
   * @param index    The index to start with
   * @return ArrayList containing all valid schedules
   */
  private static ArrayList<Schedule> findAllSchedulesHelper(Schedule schedule, int index) {
    ArrayList<Schedule> possibleSchedules = new ArrayList<Schedule>();
    // base case
    if (index == schedule.getNumCourses()) {
      possibleSchedules.add(schedule);
      return possibleSchedules;
    }
    // recursion occurs if current index course is already assigned
    if (schedule.isAssigned(index)) {
      possibleSchedules = findAllSchedulesHelper(schedule, index + 1);
      return possibleSchedules;
    // second recursive case if current index is not assigned
    } else {
      possibleSchedules = findAllSchedulesHelper(schedule, index + 1);
      ArrayList<Schedule> returnSchedules = new ArrayList<Schedule>();
      for (int j = 0; j < possibleSchedules.size(); j++) {
        for (int i = 0; i < schedule.getNumRooms(); i++) {
          try {
            returnSchedules.add(possibleSchedules.get(j).assignCourse(index, i));
          } catch (IllegalArgumentException e) {

          }
        }
      }
      return returnSchedules;
    }
  }
}

