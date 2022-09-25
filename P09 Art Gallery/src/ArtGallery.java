import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree. The search criteria
 * include the year of creation of the artwork, the name of the artwork and its cost.
 * 
 * @author Kavan Gandhi
 * @author Chinmay Koimuttum
 */
public class ArtGallery {
  private BSTNode<Artwork> root; // root node of the artwork catalog BST
  private int size; // size of the artwork catalog tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this ArtworkGallery is empty, false otherwise
   */
  public boolean isEmpty() {
    return (size == 0);
  }

  /**
   * Returns the number of artwork pieces stored in this BST.
   * 
   * @return the size of this ArtworkGallery
   */
  public int size() {
    return size;
  }

  /**
   * Checks whether this ArtworkGallery contains a Artwork given its name, year, and cost.
   * 
   * @param name name of the Artwork to search
   * @param year year of creation of the Artwork to search
   * @param cost cost of the Artwork to search
   * @return true if there is a match with this Artwork in this BST, and false otherwise
   */
  public boolean lookup(String name, int year, double cost) {
    if (this.isEmpty()) {
      return false;
    }
    Artwork artwork = new Artwork(name, year, cost);
    return lookupHelper(artwork, root);
  }

  /**
   * Recursive helper method to search whether there is a match with a given Artwork in the subtree
   * rooted at current
   * 
   * @param target  a reference to a Artwork we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {
    // if current is null, no match has been found
    if (current == null) {
      return false;
    }
    // base case
    if (current.getData().compareTo(target) == 0) {
      return true;
    }
    // if current is less than target, recurse on right subtree
    if ((current.getData().compareTo(target) < 0 && current.getRight() != null)) {
      return (lookupHelper(target, current.getRight()));
    }
    // if current greater than target, recurse on left subtree
    if ((current.getData().compareTo(target) > 0 && current.getLeft() != null)) {
      return (lookupHelper(target, current.getLeft()));
    }
    return false;
  }

  /**
   * Adds a new artwork piece to this ArtworkGallery
   * 
   * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
   * @return true if the newArtwork was successfully added to this gallery, and returns false if
   *         there is a match with this Artwork already stored in gallery.
   * @throws NullPointerException if newArtwork is null
   */
  public boolean addArtwork(Artwork newArtwork) throws NullPointerException {
    if (newArtwork == null) {
      throw new NullPointerException("Passed artwork is null");
    }
    // if the artwork does not exist, return false
    if (lookup(newArtwork.getName(), newArtwork.getYear(), newArtwork.getCost())) {
      return false;
    }
    // if empty tree, root becomes newArtwork
    if (root == null) {
      root = new BSTNode<Artwork>(newArtwork);
      size++;
      return true;
    }
    // placeholder variable to hold data from method call
    boolean bool = addArtworkHelper(newArtwork, root);
    if (bool) {
      size++;
    }
    return bool;
  }

  /**
   * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at current.
   * 
   * @param current    The "root" of the subtree we are inserting new Artwork into.
   * @param newArtwork The Artwork to be added to a BST rooted at current.
   * @return true if the newArtwork was successfully added to this ArtworkGallery, false if a match
   *         with newArtwork is already present in the subtree rooted at current.
   */
  protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {
    // base case
    if (current == null) {
      current = new BSTNode<Artwork>(newArtwork);
      return true;
    }
    // if current is less than newArtwork, recurse on right subtree
    if (current.getData().compareTo(newArtwork) < 0) {
      if (current.getRight() == null) {
        current.setRight(new BSTNode<Artwork>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getRight());
      }
      // if current is greater than newArtwork, recurse on left subtree
    } else if (current.getData().compareTo(newArtwork) > 0) {
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<Artwork>(newArtwork));
        return true;
      } else {
        return addArtworkHelper(newArtwork, current.getLeft());
      }
    }
    return false;
  }

  /**
   * Gets the recent best Artwork in this BST (meaning the largest artwork in this gallery)
   * 
   * @return the best (largest) Artwork (the most recent, highest cost artwork) in this
   *         ArtworkGallery, and null if this tree is empty.
   */
  public Artwork getBestArtwork() {
    if (this.isEmpty()) {
      return null;
    }
    // start search at root
    BSTNode<Artwork> tempArtwork = root;
    // since binary search tree, right most node will have greatest value
    while (tempArtwork.getRight() != null) {
      tempArtwork = tempArtwork.getRight();
    }
    return tempArtwork.getData();
  }

  /**
   * Returns a String representation of all the artwork stored within this BST in the increasing
   * order of year, separated by a newline "\n". For instance
   * 
   * "[(Name: Stars, Artist1) (Year: 1988) (Cost: 300)]" + "\n" + "[(Name: Sky, Artist1) (Year:
   * 2003) (Cost: 550)]" + "\n"
   * 
   * @return a String representation of all the artwork stored within this BST sorted in an
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root).trim();
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a ArtworkGallery is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current Artwork within this BST (root of a subtree)
   * @return a String representation of all the artworks stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Artwork.compareTo() method (year, cost,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Artwork> current) {
    String returnString = "";
    if (current != null) {
      // in order traversal, from left most node to right most node. Base case when current is null
      returnString += toStringHelper(current.getLeft());
      returnString += current.getData().toString() + "\n";
      returnString += toStringHelper(current.getRight());
    }
    return returnString;
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a ArtworkGallery (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Artwork> current) {
    // base case
    if (current == null) {
      return 0;
    }
    // increment return value every time you move up a level of recursion and current is not null
    int leftTreeMax = heightHelper(current.getLeft());
    int rightTreeMax = heightHelper(current.getRight());
    if (leftTreeMax > rightTreeMax) {
      return leftTreeMax + 1;
    } else {
      return rightTreeMax + 1;
    }
  }

  /**
   * Search for all artwork objects created on a given year and have a maximum cost value.
   * 
   * @param year creation year of artwork
   * @param cost the maximum cost we would like to search for a artwork
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost. If no artwork satisfies the lookup query, this method returns an empty arraylist
   */
  public ArrayList<Artwork> lookupAll(int year, double cost) {
    return lookupAllHelper(year, cost, root);
  }

  /**
   * Recursive helper method to lookup the list of artworks given their year of creation and a
   * maximum value of cost
   * 
   * @param year    the year we would like to search for a artwork
   * @param cost    the maximum cost we would like to search for a artwork
   * @param current "root" of the subtree we are looking for a match to find within it.
   * @return a list of all the artwork objects whose year equals our lookup year key and maximum
   *         cost stored in the subtree rooted at current. If no artwork satisfies the lookup query,
   *         this method returns an empty arraylist
   */
  protected static ArrayList<Artwork> lookupAllHelper(int year, double cost,
      BSTNode<Artwork> current) {
    ArrayList<Artwork> returnArtwork = new ArrayList<Artwork>();
    // base case when current is null
    if (current != null) {
      // in order traversal of the tree that compares value of current with given parameters with
      // every node
      returnArtwork.addAll(lookupAllHelper(year, cost, current.getLeft()));
      if (current.getData().getYear() == year && current.getData().getCost() <= cost) {
        returnArtwork.add(current.getData());
      }
      returnArtwork.addAll(lookupAllHelper(year, cost, current.getRight()));
    }
    return returnArtwork;
  }

  /**
   * Buy an artwork with the specified name, year and cost. In terms of BST operation, this is
   * equivalent to finding the specific node and deleting it from the tree
   * 
   * @param name name of the artwork, artist
   * @param year creation year of artwork
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria
   */

  public void buyArtwork(String name, int year, double cost) throws NoSuchElementException {
    Artwork artwork = new Artwork(name, year, cost);
    root = buyArtworkHelper(artwork, root);
    size--;
  }

  /**
   * Recursive helper method to buy artwork given the name, year and cost. In terms of BST
   * operation, this is equivalent to finding the specific node and deleting it from the tree
   * 
   * @param target  a reference to a Artwork we are searching to remove in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws a NoSuchElementException with a descriptive error message if there is no Artwork found
   *           with the buying criteria in the BST rooted at current
   */
  protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current)
      throws NoSuchElementException {
    // if no match found throw an exception
    if (current == null) {
      throw new NoSuchElementException("No artwork with given criteria found");
    }
    // if current is greater than target, recurse on left subtree
    if (current.getData().compareTo(target) > 0) {
      current.setLeft(buyArtworkHelper(target, current.getLeft()));
      // if current is less than target, recurse on right subtree
    } else if (current.getData().compareTo(target) < 0) {
      current.setRight(buyArtworkHelper(target, current.getRight()));
    } else if (current.getData().equals(target)) {
      // if current is equal to target, and current is leaf, current becomes null (removed)
      if (current.getLeft() == null && current.getRight() == null) {
        current = null;
        // if current has only right child, set current to that child
      } else if (current.getLeft() == null) {
        current = current.getRight();
        // or if current has only left child, set current to that child
      } else if (current.getRight() == null) {
        current = current.getLeft();
        // if current has both children, set current to successor and delete successor
      } else {
        BSTNode<Artwork> successorNode =
            new BSTNode<Artwork>(getSuccessor(current), current.getLeft(), current.getRight());
        current = successorNode;
        current.setRight(buyArtworkHelper(current.getRight().getData(), current.getRight()));
      }
    }
    return current;
  }

  /**
   * Helper method to find the successor of a node while performing a delete operation (buyArtwork)
   * The successor is defined as the smallest key in the right subtree. We assume by default that
   * node is not null
   * 
   * @param node node whose successor is to be found in the tree
   * @return return the key of the successor node
   */

  protected static Artwork getSuccessor(BSTNode<Artwork> node) {
    BSTNode<Artwork> tempNode = node;
    if (tempNode.getRight() == null) {
      return tempNode.getData();
    }
    // successor must be in right subtree
    tempNode = tempNode.getRight();
    // successor must be left most node
    while (tempNode.getLeft() != null) {
      tempNode = tempNode.getLeft();
    }
    return tempNode.getData();
  }
}
