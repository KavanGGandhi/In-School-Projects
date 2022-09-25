import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 * 
 * @author Kavan Gandhi
 * @author Chinmay Koimuttum
 */
public class ArtGalleryTester {

  /**
   * Checks the correctness of the implementation of both compareTo() and equals() methods defined
   * in the Artwork class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testArtworkCompareToEquals() {
    // tests the compareTo() method
    {
      Artwork testArtwork1 = new Artwork("Guernica, Picasso", 1937, 3000);
      Artwork testArtwork2 = new Artwork("Starry Night, Van Gogh", 1889, 2000);
      try {
        if (testArtwork1.compareTo(testArtwork2) < 0) {
          System.out.println("Error in compareTo() method");
          return false;
        }
        testArtwork2 = new Artwork("Starry Night, Van Gogh", 1937, 2000);
        if (testArtwork1.compareTo(testArtwork2) < 0) {
          System.out.println("Error in compareTo() method");
          return false;
        }
        testArtwork2 = new Artwork("Starry Night, Van Gogh", 1937, 3000);
        if (testArtwork1.compareTo(testArtwork2) > 0) {
          System.out.println("Error in compareTo() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // tests the equals() method
    {
      Artwork testArtwork1 = new Artwork("Guernica, Picasso", 1937, 3000);
      Artwork testArtwork2 = new Artwork("Guernica, Picasso", 1937, 2000);
      try {
        if (!testArtwork1.equals(testArtwork2)) {
          System.out.println("Error in equals() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }
    return true;
  }

  /**
   * Checks the correctness of the implementation of both addArtwork() and toString() methods
   * implemented in the ArtworkGallery class. This unit test considers at least the following
   * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one artwork and then
   * check that the addArtwork() method call returns true, the tree is not empty, its size is 1, and
   * the .toString() called on the tree returns the expected output. (3) Try adding another artwork
   * which is smaller that the artwork at the root, (4) Try adding a third artwork which is greater
   * than the one at the root, (5) Try adding at least two further artwork such that one must be
   * added at the left subtree, and the other at the right subtree. For all the above scenarios, and
   * more, double check each time that size() method returns the expected value, the add method call
   * returns true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * artwork with respect to year, cost, and then name. (6) Try adding a artwork already stored in
   * the tree. Make sure that the addArtwork() method call returned false, and that the size of the
   * tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddArtworkToStringSize() {
    // Scenario 1
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        if (testGallery.size() != 0) {
          System.out.println("Error in size() method");
          return false;
        }
        if (!testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString().equals("")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 2
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        if (!testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000))) {
          System.out.println("Error in addArtwork() method");
          return false;
        }
        if (testGallery.size() != 1) {
          System.out.println("Error in size() method");
          return false;
        }
        if (testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString()
            .equals("[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }


    // Scenario 3
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
        if (!testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000))) {
          System.out.println("Error in addArtwork() method");
          return false;
        }
        if (testGallery.size() != 2) {
          System.out.println("Error in size() method");
          return false;
        }
        if (testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString()
            .equals("[(Name: Starry Night, Van Gogh) (Year: 1889) (Cost: $2000.0)]" + "\n"
                + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 4
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
        testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
        if (!testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000))) {
          System.out.println("Error in addArtwork() method");
          return false;
        }
        if (testGallery.size() != 3) {
          System.out.println("Error in size() method");
          return false;
        }
        if (testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString()
            .equals("[(Name: Starry Night, Van Gogh) (Year: 1889) (Cost: $2000.0)]" + "\n"
                + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]" + "\n"
                + "[(Name: NightHawks, Hopper) (Year: 1942) (Cost: $4000.0)]")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 5, first addition (Adding artwork to left subtree)
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
        testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
        testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
        if (!testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000))) {
          System.out.println("Error in addArtwork() method");
          return false;
        }
        if (testGallery.size() != 4) {
          System.out.println("Error in size() method");
          return false;
        }
        if (testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString()
            .equals("[(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]" + "\n"
                + "[(Name: Starry Night, Van Gogh) (Year: 1889) (Cost: $2000.0)]" + "\n"
                + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]" + "\n"
                + "[(Name: NightHawks, Hopper) (Year: 1942) (Cost: $4000.0)]")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 5, second addition (Adding artwork to right subtree
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
        testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
        testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
        testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
        if (!testGallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280))) {
          System.out.println("Error in addArtwork() method");
          return false;
        }
        if (testGallery.size() != 5) {
          System.out.println("Error in size() method");
          return false;
        }
        if (testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString()
            .equals("[(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]" + "\n"
                + "[(Name: Starry Night, Van Gogh) (Year: 1889) (Cost: $2000.0)]" + "\n"
                + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]" + "\n"
                + "[(Name: NightHawks, Hopper) (Year: 1942) (Cost: $4000.0)]" + "\n"
                + "[(Name: For gourmets, Tuzhilkina) (Year: 2021) (Cost: $1280.0)]")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 6
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
        if (testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000))) {
          System.out.println("Error in addArtwork() method");
          return false;
        }
        if (testGallery.size() != 1) {
          System.out.println("Error in size() method");
          return false;
        }
        if (testGallery.isEmpty()) {
          System.out.println("Error in isEmpty() method");
          return false;
        }
        if (!testGallery.toString()
            .equals("[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]")) {
          System.out.println("Error in toString() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
   * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
   * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
   * to search for the artwork having a match at the root of the tree. (3) Then, search for a
   * artwork at the right and left subtrees at different levels considering successful and
   * unsuccessful search operations. Make sure that the lookup() method returns the expected output
   * for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    // Scenario 1
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        if (testGallery.lookup("Mona Lisa, DaVinci", 1503, 1000)) {
          System.out.println("Error in lookup() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 2
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      testGallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
      try {
        if (!testGallery.lookup("Guernica, Picasso", 1937, 3000)) {
          System.out.println("Error in lookup() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 3
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      testGallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
      try {
        if (!testGallery.lookup("Starry Night, Van Gogh", 1889, 2000)) {
          System.out.println("Error in lookup() method");
          return false;
        }
        if (!testGallery.lookup("For gourmets, Tuzhilkina", 2021, 1280)) {
          System.out.println("Error in lookup() method");
          return false;
        }
        if (testGallery.lookup("Whistler, Abbott", 1871, 5000)) {
          System.out.println("Error in lookup() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
   * that the height of a tree which consists of only one node is 1. (3) ensures that the height of
   * a ArtworkGallery with the following structure for instance, is 4. (*) / \ (*) (*) \ / \ (*) (*)
   * (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // Scenario 1
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        if (testGallery.height() != 0) {
          System.out.println("Error in height() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 2
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      try {
        if (testGallery.height() != 1) {
          System.out.println("Error in height() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 3
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      testGallery.addArtwork(new Artwork("Whistler, Abbott", 1871, 5000));
      testGallery.addArtwork(new Artwork("Amazone, Tsalapatanis", 2021, 6080));
      try {
        if (testGallery.height() != 4) {
          System.out.println("Error in height() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }

    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestArtwork() {
    // Scenario with non empty tree
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      testGallery.addArtwork(new Artwork("Whistler, Abbott", 1871, 5000));
      testGallery.addArtwork(new Artwork("Amazone, Tsalapatanis", 2021, 6080));
      Artwork expectedArtwork = new Artwork("Amazone, Tsalapatanis", 2021, 6080);
      try {
        if (!testGallery.getBestArtwork().equals(expectedArtwork)) {
          System.out.println("Error in getBestArtwork() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario with empty tree
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        if (testGallery.getBestArtwork() != null) {
          System.out.println("Error in getBestArtwork() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
   * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an empty
   * arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method
   * returns an array list which contains all the artwork satisfying the search criteria of year and
   * cost, when called on a non empty artwork tree with one match, and two matches and more. Vary
   * your search criteria such that the lookupAll() method must check in left and right subtrees.
   * (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist when called
   * on a non-empty artwork tree with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookupAll() {
    // Scenario 1
    {
      ArtGallery testGallery = new ArtGallery();
      try {
        if (testGallery.lookupAll(2021, 5000).size() != 0) {
          System.out.println("Error in lookupAll() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 2
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      testGallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
      testGallery.addArtwork(new Artwork("Cantabrico, Torices", 2021, 3870));
      try {
        if (testGallery.lookupAll(2021, 5000).size() != 2) {
          System.out.println("Error in lookupAll() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 3
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      testGallery.addArtwork(new Artwork("For gourmets, Tuzhilkina", 2021, 1280));
      testGallery.addArtwork(new Artwork("Cantabrico, Torices", 2021, 3870));
      try {
        if (testGallery.lookupAll(2022, 5).size() != 0) {
          System.out.println("Error in lookupAll() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }
    return true;
  }

  /**
   * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
   * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at non-leaf
   * node (3) ensures that the ArtworkGallery.buyArtwork() method throws a NoSuchElementException
   * when called on an artwork that is not present in the BST
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBuyArtwork() {
    // Scenario 1
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      try {
        testGallery.buyArtwork("Starry Night, Van Gogh", 1889, 2000);
        if (!testGallery.toString()
            .equals("[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]" + "\n"
                + "[(Name: NightHawks, Hopper) (Year: 1942) (Cost: $4000.0)]")) {
          System.out.println("Error in buyArtwork() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 2
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      testGallery.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000));
      try {
        testGallery.buyArtwork("Starry Night, Van Gogh", 1889, 2000);
        if (!testGallery.toString()
            .equals("[(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]" + "\n"
                + "[(Name: Guernica, Picasso) (Year: 1937) (Cost: $3000.0)]" + "\n"
                + "[(Name: NightHawks, Hopper) (Year: 1942) (Cost: $4000.0)]")) {
          System.out.println("Error in buyArtwork() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }

    // Scenario 3
    {
      ArtGallery testGallery = new ArtGallery();
      testGallery.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000));
      testGallery.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000));
      testGallery.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000));
      try {
        testGallery.buyArtwork("Persistence of Memory, Dali", 1931, 7000);
        return false;
      } catch (NoSuchElementException e) {
        // expected exception caught, do nothing
      } catch (Exception e) {
        // unexpected exception caught
        return false;
      }
    }
    return true;
  }

  /**
   * Returns false if any of the tester methods defined in this tester class fails.
   * 
   * @return false if any of the tester methods defined in this tester class fails, and true if all
   *         tests pass
   */
  public static boolean runAllTests() {
    return (testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup()
        && testHeight() && testGetBestArtwork() && testLookupAll() && testBuyArtwork());
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testArworkCompareToEquals(): " + testArtworkCompareToEquals());
    System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
    System.out.println("testLookup(): " + testLookup());
    System.out.println("testHeight(): " + testHeight());
    System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
    System.out.println("testLookupAll(): " + testLookupAll());
    System.out.println("testBuyArtwork(): " + testBuyArtwork());
    System.out.println("runAllTests(): " + runAllTests());
  }
}
