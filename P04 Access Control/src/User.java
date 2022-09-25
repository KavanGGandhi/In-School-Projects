/**
 * This class simulates a user log-in, with a username and password.
 *
 * @author Kavan Gandhi
 */
public class User {
  private final String USERNAME;
  private String password;
  private boolean isAdmin;
 
  /**
   * This constructor creates a new user with the given username, password, and admin status
   *
   * @param username The given username
   * @param password The given password
   * @param isAdmin Whether the user is admin or not
   */
  public User(String username, String password, boolean isAdmin) {
    this.USERNAME = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }
  
  /**
   * This method returns whether the password is correct or not
   *
   * @param password The password to check if it is correct or not
   * @return true if the password is correct, false if it is not
   */
  public boolean isValidLogin(String password) {
    if (this.password.equals(password)) {
      return true;
    }
    return false;
  }

  /**
   * This method returns the username of the user
   * 
   * @return the username
   */
  public String getUsername() {
    return USERNAME;
  }

  /**
   * This method returns whether the user is admin or not
   * 
   * @return true if user is admin, false if not
   */
  public boolean getIsAdmin() {
    return isAdmin;
  }

  /**
   * This method sets the password to the passed password
   * 
   * @param password the new password to be set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * This method sets user the user to either be admin or not be admin
   * 
   * @param isAdmin the boolean to set the admin status to
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
