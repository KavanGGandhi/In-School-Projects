//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tests the shopping cart method
// Course: CS 300 Spring 2022
//
// Author: Kavan Gandhi
// Email: kgandhi4@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class tests the methods in ShoppingCart.java.
 * 
 * @author Kavan Gandhi
 */
public class ShoppingCartTester {

  /**
   * Checks whether ShoppingCart.lookupProductByName() and ShoppingCart.lookupProductById() methods
   * work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupMethods() {
    String expectedOutput = "4390 Apple $1.59";
    if (!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Apple as input");
      return false;
    }
    if (!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Apple as input");
      return false;
    }
    String expectedOutput2 = "4688 Tomato $1.79";
    if (!ShoppingCart.lookupProductByName("Tomato").equals(expectedOutput2)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Tomato as input");
      return false;
    }
    if (!ShoppingCart.lookupProductById(4688).equals(expectedOutput2)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Tomato as input");
      return false;
    }
    String expectedOutput3 = "3560 Cheese $3.49";
    if (!ShoppingCart.lookupProductByName("Cheese").equals(expectedOutput3)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed Tomato as input");
      return false;
    }
    if (!ShoppingCart.lookupProductById(3560).equals(expectedOutput3)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the id " + "of Cheese as input");
      return false;
    }
    expectedOutput = "No match found";
    if (!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductByName() method "
          + "failed to return the expected output when passed the name of "
          + "a product not found in the market.");
      return false;
    }
    if (!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {
      System.out.println("Problem detected: Your lookupProductById() method "
          + "failed to return the expected output when passed the identifier "
          + "of a product not found in the market.");
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of ShoppingCart.getProductPrice() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetProductPrice() {
    double expectedPrice = 1.59;
    if (Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {
      System.out.println("Problem detected: Your getProductPrice() method "
          + "failed to return the expected output when passed Apple as input");
      return false;
    }
    double expectedPrice2 = 1.79;
    if (Math.abs(ShoppingCart.getProductPrice("Tomato") - expectedPrice2) > 0.001) {
      System.out.println("Problem detected: Your getProductPrice() method "
          + "failed to return the expected output when passed Tomato as input");
      return false;
    }
    double expectedPrice3 = -1.0;
    if (Math.abs(ShoppingCart.getProductPrice("NO PRODUCT") - expectedPrice3) > 0.001) {
      System.out.println("Problem detected: Your getProductPrice() method "
          + "failed to return the expected output when passed the name of "
          + "a product not found in the market.");
      return false;
    }
    return true;
  }

  /**
   * Checks whether ShoppingCart.addItemToCart() and ShoppingCart.contains() and nbOccurrences()
   * methods work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToCartContainsNbOccurrences() {
    String[] testCartEmpty = new String[10];
    int inputSizeEmpty = 0;
    int expectedSizeEmpty = 1;
    int expectedNbOccurrencesEmpty = 1;
    if (!(ShoppingCart.addItemToCart("Banana", testCartEmpty,
        inputSizeEmpty) == expectedSizeEmpty)) {
      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(testCartEmpty[0].equals("Banana"))) {
      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Banana", testCartEmpty,
        expectedSizeEmpty) == expectedNbOccurrencesEmpty)) {
      System.out.println("Problem detected: Your nbOccurrences() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!ShoppingCart.contains("Banana", testCartEmpty, expectedSizeEmpty)) {
      System.out.println("Problem detected: Your contains() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    }
    String[] testCartFull = new String[] {"Milk", "Apple", "Banana", "Pizza"};
    int inputSizeFull = 4;
    int expectedSizeFull = 4;
    int expectedNbOccurrencesFull = 1;
    if (!(ShoppingCart.addItemToCart("Banana", testCartFull, inputSizeFull) == expectedSizeFull)) {
      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Banana", testCartFull,
        expectedSizeFull) == expectedNbOccurrencesFull)) {
      System.out.println("Problem detected: Your nbOccurrences() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!ShoppingCart.contains("Banana", testCartFull, expectedSizeFull)) {
      System.out.println("Problem detected: Your contains() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    }
    String[] testCart = new String[] {"Milk", "Apple", "Banana", "Pizza", null, null};
    int inputSize = 4;
    int expectedSize = 5;
    int expectedNbOccurrences = 2;
    if (!(ShoppingCart.addItemToCart("Banana", testCart, inputSize) == expectedSize)) {
      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(testCart[4].equals("Banana"))) {
      System.out.println("Problem detected: Your addItemToCart() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Banana", testCart,
        expectedSize) == expectedNbOccurrences)) {
      System.out.println("Problem detected: Your nbOccurrences() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!ShoppingCart.contains("Banana", testCart, expectedSize)) {
      System.out.println("Problem detected: Your contains() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    }
    return true;

  }

  /**
   * Checks whether the ShoppingCart.removeItem() method work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveItem() {
    String[] testCart = new String[] {"Apple", "Milk", "Banana", "Cheese", "Pizza", null, null};
    int inputSize = 5;
    int expectedSize = 4;
    int expectedNbOccurrences = 0;
    if (!(ShoppingCart.removeItem(testCart, "Apple", inputSize) == expectedSize)) {
      System.out.println("Problem detected: Your removeItem method "
          + "failed to return the expected output when passed Apple as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Apple", testCart,
        expectedSize) == expectedNbOccurrences)) {
      System.out.println("Problem detected: Your removeItem method "
          + "failed to return the expected output when passed Apple as input");
      return false;
    }
    String[] testCart2 = new String[] {"Milk", "Apple", "Cheese", "Apple", "Banana", null, null};
    int inputSize2 = 5;
    int expectedSize2 = 4;
    int expectedNbOccurrences2 = 0;
    if (!(ShoppingCart.removeItem(testCart2, "Banana", inputSize2) == expectedSize2)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Banana", testCart2,
        expectedSize2) == expectedNbOccurrences2)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    }
    String[] testCart3 = new String[] {"Milk", "Apple", "Banana", "Apple", "Cheese", null, null};
    int inputSize3 = 5;
    int expectedSize3 = 4;
    int expectedNbOccurrences3 = 0;
    if (!(ShoppingCart.removeItem(testCart3, "Banana", inputSize3) == expectedSize3)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Banana", testCart3,
        expectedSize3) == expectedNbOccurrences3)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    }
    String[] testCart4 = new String[] {null, null, null, null, null};
    int inputSize4 = 0;
    int expectedSize4 = 0;
    int expectedNbOccurrences4 = 0;
    if (!(ShoppingCart.removeItem(testCart4, "Banana", inputSize4) == expectedSize4)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Banana", testCart4,
        expectedSize4) == expectedNbOccurrences4)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Banana as input");
      return false;
    }
    String[] testCart5 = new String[] {"Milk", "Apple", "Banana", "Apple", "Cheese", null, null};
    int inputSize5 = 5;
    int expectedSize5 = 5;
    int expectedNbOccurrences5 = 0;
    if (!(ShoppingCart.removeItem(testCart5, "Potato", inputSize5) == expectedSize5)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Potato as input");
      return false;
    } else if (!(ShoppingCart.nbOccurrences("Potato", testCart5,
        expectedSize5) == expectedNbOccurrences5)) {
      System.out.println("Problem detected: Your removeItem() method "
          + "failed to return the expected output when passed Potato as input");
      return false;
    }
    return true;
  }

  /**
   * Checks whether ShoppingCart.checkout() and ShoppingCart.getCartSummary() methods work as
   * expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCheckoutGetCartSummary() {
    String expectedOutput = "";
    int inputSize = 0;
    double expectedCheckout = 0;
    String[] testCart = new String[] {null, null, null, null, null};
    if (!(ShoppingCart.getCartSummary(testCart, inputSize).equals(expectedOutput))) {
      System.out.println("Problem detected: Your getCartSummary() method "
          + "failed to return the expected output when called on empty cart");
      return false;
    } else if (Math.abs((ShoppingCart.checkout(testCart, inputSize)) - expectedCheckout) > 0.01) {
      System.out.println("Problem detected: Your checkout() method "
          + "failed to return the expected output when called on empty cart");
      return false;
    }
    String expectedOutput2 = "(1) Milk" + "\n" + "(1) Apple" + "\n" + "(1) Cheese" + "\n";
    int inputSize2 = 3;
    double expectedCheckout2 = 7.5285;
    String[] testCart2 = new String[] {"Milk", "Apple", "Cheese"};
    if (!(ShoppingCart.getCartSummary(testCart2, inputSize2).equals(expectedOutput2))) {
      System.out.println("Problem detected: Your getCartSummary() method "
          + "failed to return the expected output when called on a cart containing unique items");
      return false;
    } else if (Math
        .abs((ShoppingCart.checkout(testCart2, inputSize2)) - expectedCheckout2) > 0.01) {
      System.out.println("Problem detected: Your checkout() method "
          + "failed to return the expected output when called on a cart containing unique items");
      return false;
    }
    String expectedOutput3 = "(1) Milk" + "\n" + "(2) Apple" + "\n";
    int inputSize3 = 3;
    double expectedCheckout3 = 5.5335;
    String[] testCart3 = new String[] {"Milk", "Apple", "Apple"};
    if (!(ShoppingCart.getCartSummary(testCart3, inputSize3).equals(expectedOutput3))) {
      System.out.println("Problem detected: Your getCartSummary() method "
          + "failed to return the expected output when called on a cart "
          + "containing duplicate items");
      return false;
    } else if (Math
        .abs((ShoppingCart.checkout(testCart3, inputSize3)) - expectedCheckout3) > 0.01) {
      System.out.println("Problem detected: Your checkout() method "
          + "failed to return the expected output when called on a cart "
          + "containing duplicate items");
      return false;
    }
    return true;

  }

  /**
   * Checks whether all tester methods work as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean runAllTests() {
    if (testLookupMethods() & testGetProductPrice() & testAddItemToCartContainsNbOccurrences()
        & testRemoveItem() & testCheckoutGetCartSummary()) {
      return true;
    }
    return false;
  }

  /**
   * Main method
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("testLookupMethods(): " + testLookupMethods());
    System.out.println("testGetProductPrice(): " + testGetProductPrice());
    System.out.println(
        "testAddItemToCartContainsNbOccurrences(): " + testAddItemToCartContainsNbOccurrences());
    System.out.println("testRemoveItem(): " + testRemoveItem());
    System.out.println("testCheckoutGetCartSummary(): " + testCheckoutGetCartSummary());
    System.out.println("runAllTests():" + runAllTests());
  }

}
