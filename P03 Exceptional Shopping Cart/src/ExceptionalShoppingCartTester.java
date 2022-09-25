import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;

/**
 * This class tests methods from the ExceptionalShoppingCart class.
 *
 * @author Kavan Gandhi
 */
public class ExceptionalShoppingCartTester {
  
  /**
   * Checks whether lookupProductByName() and lookupProductById() methods work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupMethods() {
    // Test to see if method returns expected output if passed an item in market
    {
      String expectedOutput = "4390 Apple $1.59";
      try {
        if (!ExceptionalShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
          System.out.println("Problem detected: Your lookupProductByName() method "
              + "failed to return the expected output when passed Apple as input");
          return false;
        }
        if (!ExceptionalShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
          System.out.println("Problem detected: Your lookupProductById() method "
              + "failed to return the expected output when passed the id " + "of Apple as input");
          return false;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected output when passed the id " + "of Apple as input");
        return false;
      } catch (NoSuchElementException e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected output when passed the id " + "of Apple as input");
        return false;
      } catch (Exception e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected output when passed the id " + "of Apple as input");
        return false;
      }
    }

    // Test to see if expected error is returned for lookupProductByName()
    {
      try {
        ExceptionalShoppingCart.lookupProductByName("Not Found");
      } catch (IllegalArgumentException e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected error");
        return false;
      } catch (NoSuchElementException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected error");
        return false;
      }
    }

    // Test to see if expected error is returned for lookupProductById() if incorrect Id is input
    {
      try {
        ExceptionalShoppingCart.lookupProductById(1234);
      } catch (IllegalArgumentException e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected error");
        return false;
      } catch (NoSuchElementException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected error");
        return false;
      }
    }

    // Test to see if expected error is returned for lookupProductById() if incorrect size of
    // Id is input
    {
      try {
        ExceptionalShoppingCart.lookupProductById(12345);
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (NoSuchElementException e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected error");
        return false;
      } catch (Exception e) {
        System.out.println("Problem detected: Your lookupProductById() method "
            + "failed to return the expected error");
        return false;
      }
    }
    return true;
  }
  
  /**
   * Checks whether addItemToMarketCatalog() method work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToMarketCatalog() {
    // Test to see if IllegalArgumentException is thrown when id is non 4-digit number
    {
      try {
        ExceptionalShoppingCart.addItemToMarketCatalog("12345", "test", "$100");
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your testAddItemToMarketCatalog() method "
            + "failed to return the expected error");
      }
    }

    // Test to see if IllegalArgumentException is thrown when id is non parsable
    {
      try {
        ExceptionalShoppingCart.addItemToMarketCatalog("hello", "test", "$100");
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your testAddItemToMarketCatalog() method "
            + "failed to return the expected error");
      }
    }

    // Test to see if IllegalArgumentException is thrown when name is empty string
    {
      try {
        ExceptionalShoppingCart.addItemToMarketCatalog("1234", "", "$100");
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your testAddItemToMarketCatalog() method "
            + "failed to return the expected error");
      }
    }

    // Test to see if IllegalArgumentException is thrown when name is null
    {
      try {
        ExceptionalShoppingCart.addItemToMarketCatalog("1234", null, "$100");
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your testAddItemToMarketCatalog() method "
            + "failed to return the expected error");
      }
    }

    // Test to see if IllegalArgumentException is thrown when price input does not start with $
    {
      try {
        ExceptionalShoppingCart.addItemToMarketCatalog("1234", "test", "100");
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your testAddItemToMarketCatalog() method "
            + "failed to return the expected error");
      }
    }

    // Test to see if IllegalArgumentException is thrown when price input does not start with $
    {
      try {
        ExceptionalShoppingCart.addItemToMarketCatalog("1234", "test", "hello");
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      } catch (Exception e) {
        System.out.println("Problem detected: Your testAddItemToMarketCatalog() method "
            + "failed to return the expected error");
      }
    }
    return true;
  }
  
  /**
   * Checks whether saveCartSummary() method work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSaveCartSummary() {
    // Tests a normal case of creating a file, compares files using scanner
    {
      String[] testCart = new String[] {"Milk", "Apple", "Cheese"};
      int testSize = 3;
      File testFile = new File("TestFile.txt");
      File expectedFile = new File("Expected.txt");
      try {
        FileWriter fileWriter = new FileWriter(expectedFile);
        fileWriter.write("( 1 ) Milk" + "\n" + "( 1 ) Apple" + "\n" + "( 1 ) Cheese");
        fileWriter.close();
        ExceptionalShoppingCart.saveCartSummary(testCart, testSize, testFile);
        Scanner scnr = new Scanner(expectedFile);
        Scanner scnr2 = new Scanner(testFile);
        while (scnr.hasNextLine()) {
          if (!scnr.nextLine().equals(scnr2.nextLine())) {
            System.out.println("saveCartSummary() failed to return expect output file");
            return false;
          }
        }
      } catch (IOException e) {
        return false;
      }
    }
    
    // Tests with a negative size, expected to throw IllegalArgumentException
    {
      String[] testCart = new String[] {"Milk", "Apple", "Cheese"};
      int testSize = -1;
      File testFile = new File("TestFile.txt");
      File expectedFile = new File("Expected.txt");
      try {
        FileWriter fileWriter = new FileWriter(expectedFile);
        fileWriter.write("( 1 ) Milk" + "\n" + "( 1 ) Apple" + "\n" + "( 1 ) Cheese");
        fileWriter.close();
        ExceptionalShoppingCart.saveCartSummary(testCart, testSize, testFile);
        Scanner scnr = new Scanner(expectedFile);
        Scanner scnr2 = new Scanner(testFile);
        while (scnr.hasNextLine()) {
          if (!scnr.nextLine().equals(scnr2.nextLine())) {
            System.out.println("saveCartSummary() failed to return expect output file");
            return false;
          }
        }
      } catch (IOException e) {
        return false;
      } catch (IllegalArgumentException e) {
        // Expected error thrown
      }
    }
    return true;
  }
  
  /**
   * Checks whether loadCartSummary() method work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLoadCartSummary() {

    // Tests if items are added in a normal circumstance
    {
      String[] testCart = new String[] {"Milk", "Apple", "Cheese", null, null};
      int testSize = 3;
      int expectedSize = 5;
      int sizeAfterMethod = 0;
      File testFile = new File("TestFile.txt");
      try {
        FileWriter fileWriter = new FileWriter(testFile);
        fileWriter.write("( 1 ) Beef" + "\n" + "( 1 ) Butter");
        fileWriter.close();
        sizeAfterMethod = ExceptionalShoppingCart.loadCartSummary(testFile, testCart, testSize);
        if (!testCart[3].equals("Beef") || !testCart[4].equals("Butter")) {
          System.out.println("The cart after loadCartSummary() was not updated as expected");
          return false;
        } else if (sizeAfterMethod != expectedSize) {
          System.out.println("The size after loadCartSummary() was not updated as expected");
          return false;
        }
      } catch (IOException e) {
        return false;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
        System.out.println("Unexpected excpetion in testLoadCartSummary()");
        return false;
      }
    }

    // Tests if wrong input is skipped
    {
      String[] testCart = new String[] {"Milk", "Apple", "Cheese", null, null};
      int testSize = 3;
      int expectedSize = 4;
      int sizeAfterMethod = 0;
      File testFile = new File("TestFile.txt");
      try {
        FileWriter fileWriter = new FileWriter(testFile);
        fileWriter.write("( one ) Beef" + "\n" + "( 1 ) Butter");
        fileWriter.close();
        sizeAfterMethod = ExceptionalShoppingCart.loadCartSummary(testFile, testCart, testSize);
        if (!testCart[3].equals("Butter")) {
          System.out.println("The cart after loadCartSummary() was not updated as expected");
          return false;
        } else if (sizeAfterMethod != expectedSize) {
          System.out.println("The size after loadCartSummary() was not updated as expected");
          return false;
        }
      } catch (IOException e) {
        return false;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
        System.out.println("Unexpected excpetion in testLoadCartSummary()");
        return false;
      }
    }

    // Tests if multiple of the same items are added to the cart
    {
      String[] testCart = new String[] {"Milk", "Apple", "Cheese", null, null, null};
      int testSize = 3;
      int expectedSize = 6;
      int sizeAfterMethod = 0;
      File testFile = new File("TestFile.txt");
      try {
        FileWriter fileWriter = new FileWriter(testFile);
        fileWriter.write("( 2 ) Beef" + "\n" + "( 1 ) Butter");
        fileWriter.close();
        sizeAfterMethod = ExceptionalShoppingCart.loadCartSummary(testFile, testCart, testSize);
        if (!testCart[3].equals("Beef") || !testCart[4].equals("Beef")
            || !testCart[5].equals("Butter")) {
          System.out.println("The cart after loadCartSummary() was not updated as expected");
          return false;
        } else if (sizeAfterMethod != expectedSize) {
          System.out.println("The size after loadCartSummary() was not updated as expected");
          return false;
        }
      } catch (IOException e) {
        return false;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
        System.out.println("Unexpected excpetion in testLoadCartSummary()");
        return false;
      }
    }
    return true;
  }
  
  /**
   * Runs all testing methods in this class together.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean runAllTests() {
    if (!testLookupMethods() || !testAddItemToMarketCatalog() || !testSaveCartSummary()
        || !testLoadCartSummary()) {
      return false;
    }
    return true;
  }
  
  /**
   * Main method
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

}
