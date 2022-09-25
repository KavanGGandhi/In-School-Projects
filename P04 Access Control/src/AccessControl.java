import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class simulates a control terminal where users can log on and off
 *
 * @author Kavan Gandhi
 */
public class AccessControl {
  private static ArrayList<User> users;
  private User currentUser;
  private static final String DEFAULT_PASSWORD = "changeme";

  /**
   * A no-argument constructor for the AccessControl object
   */
  public AccessControl() {
    if (users == null) {
      users = new ArrayList<>();
      users.add(new User("admin", "root", true));
    }
    currentUser = null;
  }

  /**
   * This method checks whether a username and password pair result in a valid login
   * 
   * @param username The username to be checked
   * @param password the password to be checked
   * @return true if the pair is a valid login, false if it is not
   */
  public static boolean isValidLogin(String username, String password) {
    if (username == null || password == null) {
      return false;
    }
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i) != null) {
        if (users.get(i).getUsername().equals(username) && users.get(i).isValidLogin(password)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method changes the password of the current user to the passed password
   * 
   * @param newPassword the password to be changed to
   */
  public void changePassword(String newPassword) {
    if (currentUser != null) {
      currentUser.setPassword(newPassword);
    }
  }

  /**
   * This method logs out the current user by setting it to null
   */
  public void logout() {
    currentUser = null;
  }

  /**
   * This method is a mutator that can be used to write tests without simulating user input. It sets
   * the current user to the user from the users list whose username matches the string provided as
   * input to the method (exact match case sensitive).
   * 
   * @param username the username to set the currentUser to
   */
  public void setCurrentUser(String username) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        currentUser = users.get(i);
      }
    }
  }

  /**
   * This method creates a new user with the default password and isAdmin==false and add it to the
   * users ArrayList.
   * 
   * @param username the username of the user to add
   * @return true if the current user has Admin power and the new user was successfully added.
   *         Return false if the the current user is null or does not have Admin power
   * @throws IllegalArgumentException with a descriptive error message if username is null or if its
   *                                  length is less than 5 ( < 5), or if a user with the same
   *                                  username is already in the list of users (usernames must be
   *                                  unique)
   */
  public boolean addUser(String username) throws IllegalArgumentException {
    if (username == null) {
      throw new IllegalArgumentException("The username can not be null");
    }
    if (username.length() < 5) {
      throw new IllegalArgumentException("The length can not be less than 5");
    }
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        throw new IllegalArgumentException("Someone already has that username");
      }
    }
    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    if (currentUser.getIsAdmin()) {
      users.add(new User(username, DEFAULT_PASSWORD, false));
    }
    return true;
  }

  /**
   * This method creates a new user with the default password and specific admin status and add it
   * to the users ArrayList.
   * 
   * @param username the username of the user to add
   * @param isAdmin  the admin status the user should have
   * @return true if the current user has Admin power and the new user was successfully added.
   *         Return false if the the current user is null or does not have Admin power
   * @throws IllegalArgumentException with a descriptive error message if username is null or if its
   *                                  length is less than 5 ( < 5), or if a user with the same
   *                                  username is already in the list of users (usernames must be
   *                                  unique)
   */
  public boolean addUser(String username, boolean isAdmin) throws IllegalArgumentException {
    if (username == null) {
      throw new IllegalArgumentException("The username can not be null");
    }
    if (username.length() < 5) {
      throw new IllegalArgumentException("The length can not be less than 5");
    }
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getUsername().equals(username)) {
        throw new IllegalArgumentException("Someone already has that username");
      }
    }
    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    if (currentUser.getIsAdmin()) {
      users.add(new User(username, DEFAULT_PASSWORD, isAdmin));
    }
    return true;
  }

  /**
   * This method removes a user given their unique username
   * 
   * @param username the username of the user to remove
   * @return true if the current user has Admin power and the new user was successfully removed.
   *         Return false if the the current user is null or does not have Admin power
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean removeUser(String username) throws NoSuchElementException {
    boolean isRemoved = false;

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }

    if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.remove(i);
          isRemoved = true;
        }
      }
      if (isRemoved == false) {
        throw new NoSuchElementException("The username was not found");
      }
    }
    return true;
  }

  /**
   * This method gives a user admin power
   * 
   * @param username the username of the user to give admin to
   * @return true if if this operation terminates successfully
   *         Return false if the the current user is null or does not have Admin power
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean giveAdmin(String username) throws NoSuchElementException {
    boolean hasGivenAdmin = false;

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setIsAdmin(true);
          hasGivenAdmin = true;
        }
      }
      if (hasGivenAdmin == false) {
        throw new NoSuchElementException("The username was not found");
      }
    }
    return true;
  }

  /**
   * This method removes the admin power of a user given their username
   * 
   * @param username the username of the user to remove admin from
   * @return true if if this operation terminates successfully
   *         Return false if the the current user is null or does not have Admin power
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean takeAdmin(String username) throws NoSuchElementException {
    boolean hasTakenAdmin = false;

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setIsAdmin(false);
          hasTakenAdmin = true;
        }
      }
      if (hasTakenAdmin == false) {
        throw new NoSuchElementException("The username was not found");
      }
    }
    return true;
  }

  /**
   * This method resets the password of a user given their username
   * 
   * @param username the username of the user to remove admin from
   * @return true if if this operation terminates successfully
   *         Return false if the the current user is null or does not have Admin power
   * @throws NoSuchElementException with a descriptive error message if no match with username is
   *                                found in the list of users
   */
  public boolean resetPassword(String username) throws NoSuchElementException {
    boolean hasResetPassword = false;

    if (currentUser == null || currentUser.getIsAdmin() == false) {
      return false;
    }
    if (currentUser.getIsAdmin()) {
      for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getUsername().equals(username)) {
          users.get(i).setPassword(DEFAULT_PASSWORD);
          hasResetPassword = true;
        }
      }
      if (hasResetPassword == false) {
        throw new NoSuchElementException("The username was not found");
      }
    }
    return true;
  }
  
  /**
   * Main method.
   *
   * @param args unused.
   */
  public static void main(String[] args) {
  }

}
