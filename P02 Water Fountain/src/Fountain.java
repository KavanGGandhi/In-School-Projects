import java.util.Random;
import java.io.File;
import processing.core.PImage;

/**
 * This class simulates a water fountain that spews out randomly generated droplets.
 *
 * @author Kavan Gandhi
 */
public class Fountain {
  private static Random randGen;
  private static PImage fountainImage;
  private static int positionX;
  private static int positionY;
  private static Droplet[] droplets;
  private static int startColor;
  private static int endColor;

  /**
   * Main method.
   *
   * @param args unused.
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }
  
  /**
   * This method updates the velocity, color/transparency, and position 
   * of a droplet at a specific index.
   *
   * @param index The index of the droplet which must be.
   */
  private static void updateDroplet(int index) {
    Utility.fill(droplets[index].getColor(), droplets[index].getTransparency());
    Utility.circle(droplets[index].getPositionX(), droplets[index].getPositionY(),
        droplets[index].getSize());
    droplets[index].setVelocityY(droplets[index].getVelocityY() + 0.3f);
    droplets[index].setPositionX(droplets[index].getPositionX() + droplets[index].getVelocityX());
    droplets[index].setPositionY(droplets[index].getPositionY() + droplets[index].getVelocityY());
  }
  
  /**
   * This method creates a new droplet with random position close to or at the center of the
   * background, random size, random color between startColor and endColor, random velocity,
   * random age and randome transparency.
   *
   * @param numDropletsToCreate The number of addition droplets to create.
   */
  private static void createNewDroplets(int numDropletsToCreate) {
    for (int i = 0; i < droplets.length; i++) {
      if (numDropletsToCreate == 0) {
        break;
      }
      if (droplets[i] == null) {
        droplets[i] = new Droplet();
        droplets[i].setPositionX(positionX + (-3 + (randGen.nextFloat() * (3 - -3))));
        droplets[i].setPositionY(positionY + (-3 + (randGen.nextFloat() * (3 - -3))));
        droplets[i].setSize(4 + (randGen.nextFloat() * (11 - 4)));
        droplets[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextInt()));
        droplets[i].setVelocityX(-1 + (randGen.nextFloat() * (1 - -1)));
        droplets[i].setVelocityY(-10 + (randGen.nextFloat() * (-5 - -10)));
        droplets[i].setAge((randGen.nextInt(41)));
        droplets[i].setTransparency((randGen.nextInt(127 - 32) + 32 + 1));
        numDropletsToCreate -= 1;
      }
    }
  }
  
  /**
   * Sets droplets that are greater that the maxAge to null.
   *
   * @param maxAge The maximum age the droplet can be before getting removed.
   */
  private static void removeOldDroplets(int maxAge) {
    for (int i = 0; i < droplets.length; i++) {
      if (droplets[i] != null && droplets[i].getAge() > maxAge) {
        droplets[i] = null;
      }
    }
  }
  
  /**
   * This method sets the origin around where the droplets are created to where the mouse is 
   * pressed.
   */
  public static void mousePressed() {
    positionX = Utility.mouseX();
    positionY = Utility.mouseY();
  }
  
  /**
   * Whenever s or S is pressed on the keyboard, a screenshot of the application is taken 
   * and saved.
   *
   * @param pressedKey The key that is pressed by the user.
   */
  public static void keyPressed(char pressedKey) {
    if (pressedKey == 's' || pressedKey == 'S') {
      Utility.save("screenshot.png");
    }
  }
  
  /**
   * This method instantiates variables used throughout the code. It is called once when the 
   * application is initially run.
   */
  public static void setup() {
    System.out.println(testUpdateDroplet());
    System.out.println(testRemoveOldDroplets());
    randGen = new Random();
    positionX = Utility.width() / 2;
    positionY = Utility.height() / 2;
    fountainImage = Utility.loadImage("images" + File.separator + "fountain.png");
    droplets = new Droplet[800];
    startColor = Utility.color(23, 141, 235);
    endColor = Utility.color(23, 200, 255);
  }
  /**
   * This method calls other methods to create and update droplets. It is called repetedly. 
   */
  public static void draw() {
    Utility.background(Utility.color(253, 245, 230));
    Utility.image(fountainImage, positionX, positionY);
    createNewDroplets(10);
    for (int i = 0; i < droplets.length; i++) {
      if (droplets[i] != null) {
        updateDroplet(i);
        droplets[i].setAge(droplets[i].getAge() + 1);
      }
    }
    removeOldDroplets(80);
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets. Creates a single
   * droplet at position (3,3) with velocity (-1,-2). Then checks whether calling updateDroplet() 
   * on this droplet’s index correctly results in changing its position to (2.0, 1.3).
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testUpdateDroplet() {
    droplets = new Droplet[3];
    droplets[0] = new Droplet();
    droplets[1] = new Droplet();
    droplets[2] = new Droplet();
    droplets[0].setPositionX(3f);
    droplets[0].setPositionY(3f);
    droplets[0].setVelocityX(-1.0f);
    droplets[0].setVelocityY(-2.0f);
    updateDroplet(0);
    float expectedXPosition = 2.0f;
    float expectedYPosition = 1.3f;
    if (Math.abs(droplets[0].getPositionX() - expectedXPosition) > 0.001) {
      System.out.println("Your updateDroplet() method failed to get the correct X position");
      return false;
    } else if (Math.abs(droplets[0].getPositionY() - expectedYPosition) > 0.001) {
      System.out.println("Your updateDroplet() method failed to get the correct Y position");
      return false;
    }
    return true;
  }

  /**
   * This tester initializes the droplets array to hold at least three droplets. Calls
   * removeOldDroplets(6) on an array with three droplets (two of which have ages over six and
   * another that does not). Then checks whether the old droplets were removed and 
   * the young droplet was left alone.
   *
   * @return true when no defect is found, and false otherwise
   */
  private static boolean testRemoveOldDroplets() {
    droplets = new Droplet[3];
    droplets[0] = new Droplet();
    droplets[1] = new Droplet();
    droplets[2] = new Droplet();
    droplets[0].setAge(8);
    droplets[1].setAge(20);
    droplets[2].setAge(4);
    removeOldDroplets(6);
    if (droplets[0] != null || droplets[1] != null || droplets[2] == null) {
      System.out.println("Your removeOldDroplets() method failed to work as intended");
      return false;
    }
    return true;
  }
}
