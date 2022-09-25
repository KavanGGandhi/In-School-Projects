import java.util.NoSuchElementException;

/**
 * This class tests methods from the User class and the AccessControl class
 *
 * @author Kavan Gandhi
 */
public class AccessControlTester {

  /**
   * Main method.
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

  /**
   * This method runs all of the tests in this class
   *
   * @return true if all tests succeed, false if even one test fails
   */
  public static boolean runAllTests() {
    if (!testUserConstructorAndMethods() || !testAccessControlIsValidLoginNotValidUser()
        || !testAddRemoveUserWithAdminPowers() || !testAddUserWithNoAdminPowers()) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the methods from the User class
   *
   * @return true if all methods work as intended, false if not
   */
  public static boolean testUserConstructorAndMethods() {
    // These tests verify whether the constructor and the accessor methods work as intended
    {
      User testUser = new User("Bob", "password", false);
      if (!testUser.getUsername().equals("Bob")) {
        System.out.println("There is an issue with the constructor or getUsername()");
        return false;
      }
      if (testUser.getIsAdmin() == true) {
        System.out.println("There is an issue with the constructor or getIsAdmin()");
        return false;
      }
      if (testUser.isValidLogin("password") == false) {
        System.out.println("There is an issue with the constructor or isValidLogin()");
        return false;
      }
    }

    // These tests verify whether the mutator methods work as intended
    {
      User testUser = new User("Bob", "password", false);
      testUser.setPassword("hello");
      if (testUser.isValidLogin("hello") == false) {
        System.out.println("There is an issue with setPassword()");
        return false;
      }
      testUser.setIsAdmin(true);
      if (testUser.getIsAdmin() == false) {
        System.out.println("There is an issue with setIsAdmin()");
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks the correctness of AccessControl.isValidLogin() method when called with
   * incorrect username or not matching (username, password) pair
   *
   * @return true if the method works as intended, false if not
   */
  public static boolean testAccessControlIsValidLoginNotValidUser() {
    AccessControl testObject = new AccessControl();
    if (AccessControl.isValidLogin("admin", "wrong password") == true) {
      System.out.println("There is a problem with the isValidLogin() method");
      return false;
    }
    return true;
  }

  /**
   * This method checks the correctness of addUser and removeUser methods when the current user has
   * admin powers
   *
   * @return true if the methods works as intended, false if not
   */
  public static boolean testAddRemoveUserWithAdminPowers() {
    AccessControl testObject = new AccessControl();
    try {
      testObject.setCurrentUser("admin");
      if (testObject.addUser("Maximus", true) == false) {
        System.out
            .println("There is a problem with the addUser() method. An admin could not add a user");
        return false;
      }
      testObject.setCurrentUser("Maximus");
      if (testObject.addUser("William") == false) {
        System.out
            .println("There is a problem with the addUser() method. An admin could not add a user");
        return false;
      }
      if (testObject.removeUser("William") == false) {
        System.out.println(
            "There is a problem with the addUser() method. An admin could not remove a user");
        return false;
      }
    } catch (IllegalArgumentException e) {
      System.out.println("An unexpected IllegalArgumentException was caught");
      e.printStackTrace();
      return false;
    } catch (NoSuchElementException e) {
      System.out.println("An unexpected NoSuchElementException was caught");
      return false;
    }
    return true;
  }

  /**
   * This method creates a new AccessControl object and does not log in an admin. This test must
   * fail if addUser(String username) does not return false or if a user was added to the list of
   * user after the method returns.
   *
   * @return true if the method works as intended, false if not
   */
  public static boolean testAddUserWithNoAdminPowers() {
    AccessControl testObject = new AccessControl();
    try {
      testObject.setCurrentUser("admin");
      testObject.addUser("Johnathan", false);
      testObject.setCurrentUser("Johnathan");
      if (testObject.addUser("Bobert") == true) {
        System.out
            .println("There is a problem with the addUser() method. A non admin added a user");
        return false;
      }
      testObject.setCurrentUser("admin");
      if (testObject.removeUser("Bobert") == true) {
        System.out.println("There is a problem with the addUser() method.");
        return false;
      }
    } catch (IllegalArgumentException e) {
      System.out.println("An unexpected IllegalArgumentException was caught");
      return false;
    } catch (NoSuchElementException e) {
      // expected exception thrown from remove user
    }
    return true;
  }
}
