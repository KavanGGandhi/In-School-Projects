/**
 * This class models a shopping cart and a market.
 * 
 * @author Kavan Gandhi
 */
public class ShoppingCart {
  private static final double TAX_RATE = 0.05; // sales tax
  private static String[][] marketItems =
      new String[][] {{"4390", "Apple", "$1.59"}, {"4046", "Avocado", "$0.59"},
          {"4011", "Banana", "$0.49"}, {"4500", "Beef", "$3.79"}, {"4033", "Blueberry", "$6.89"},
          {"4129", "Broccoli", "$1.79"}, {"4131", "Butter", "$4.59"}, {"4017", "Carrot", "$1.19"},
          {"3240", "Cereal", "$3.69"}, {"3560", "Cheese", "$3.49"}, {"3294", "Chicken", "$5.09"},
          {"4071", "Chocolate", "$3.19"}, {"4363", "Cookie", "$9.5"}, {"4232", "Cucumber", "$0.79"},
          {"3033", "Eggs", "$3.09"}, {"4770", "Grape", "$2.29"}, {"3553", "Ice Cream", "$5.39"},
          {"3117", "Milk", "$2.09"}, {"3437", "Mushroom", "$1.79"}, {"4663", "Onion", "$0.79"},
          {"4030", "Pepper", "$1.99"}, {"3890", "Pizza", "$11.5"}, {"4139", "Potato", "$0.69"},
          {"3044", "Spinach", "$3.09"}, {"4688", "Tomato", "$1.79"}, null, null, null, null};

  /**
   * Returns details of a specific product in the market given its name
   *
   * @param name name of the product to search
   * @return A string representation of the product to search including the identifier of the
   *         product, its name, and its price in dollars if match found.
   */
  public static String lookupProductByName(String name) {
    String productInfo;
    for (int i = 0; i < marketItems.length; i++) {
      if (!(marketItems[i] == null) && marketItems[i][1].equals(name)) {
        productInfo = marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
        return productInfo;
      } else if (marketItems[i] == null) {
        return "No match found";
      } else if (!marketItems[i][1].equals(name) && i == marketItems.length - 1) {
        return "No match found";
      }
    }
    return "No match found";
  }

  /**
   * Returns a string representation of the item whose id is provided as input if a match was found.
   *
   * @param id id of the product to search
   * @return A string representation of the product to search including the identifier of the
   *         product, its name, and its price in dollars if match found.
   */
  public static String lookupProductById(int id) {
    String productInfo;
    for (int i = 0; i < marketItems.length; i++) {
      if (!(marketItems[i] == null) && Integer.parseInt(marketItems[i][0]) == (id)) {
        productInfo = marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
        return productInfo;
      } else if (marketItems[i] == null) {
        return "No match found";
      } else if (!(Integer.parseInt(marketItems[i][0]) == (id)) && i == marketItems.length - 1) {
        return "No match found";
      }
    }
    return "No match found";
  }

  /**
   * Returns the price in dollars (a double value) of a market item given its name.
   *
   * @param name name of the product to get the price of
   * @return A string representation of the price. Returns -1.0 if no match was found in market
   *         catalog.
   */
  public static double getProductPrice(String name) {
    double productPrice;
    for (int i = 0; i < marketItems.length; i++) {
      if (!(marketItems[i] == null) && marketItems[i][1].equals(name)) {
        productPrice = Double.parseDouble(marketItems[i][2].substring(1));
        return productPrice;
      } else if (marketItems[i] == null) {
        return -1.0;
      } else if (!marketItems[i][1].equals(name) && i == marketItems.length - 1) {
        return -1.0;
      }
    }
    return -1.0;
  }

  /**
   * Returns a deep copy of the marketItems perfect sized array.
   *
   * @return A 2d array which is a deep copy of the marketItems array
   */
  public static String[][] getCopyOfMarketItems() {
    String copyOfMarketItems[][] = new String[marketItems.length][];
    for (int i = 0; i < marketItems.length; i++) {
      copyOfMarketItems[i] = marketItems[i];
    }
    return copyOfMarketItems;
  }

  /**
   * Appends an item to a given cart (appends means adding to the end). If the cart is already full
   * (meaning its size equals its length), the item will not be added to the cart.
   *
   * @param item the name of the product to be added to the cart
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return The size of the oversize array cart after trying to add item to the cart. Returns the
   *         same size without making any changes to the contents if it is full.
   */
  public static int addItemToCart(String item, String[] cart, int size) {
    if (cart.length == size) {
      return size;
    }
    for (int i = 0; i < cart.length; i++) {
      if (cart[i] == null) {
        cart[i] = item;
        size += 1;
        return size;
      }
    }
    return size;
  }

  /**
   * Returns the number of occurrences of a given item within a cart.
   *
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return The number of occurrences of item (exact match) within the oversize array cart.
   */
  public static int nbOccurrences(String item, String[] cart, int size) {
    int nbOccurrences = 0;
    for (int i = 0; i < size; i++) {
      if (!(cart[i] == null)) {
        if (cart[i].equals(item)) {
          nbOccurrences += 1;
        }
      }
    }
    return nbOccurrences;
  }

  /**
   * Checks whether a cart contains at least one occurrence of a given item.
   *
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return True if there is a match (exact match) of item within the provided cart, and false
   *         otherwise.
   */
  public static boolean contains(String item, String[] cart, int size) {
    for (int i = 0; i < size; i++) {
      if (!(cart[i] == null)) {
        if (cart[i].equals(item)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method returns the total value in dollars of the cart. All products in the market are
   * taxable (subject to TAX_RATE).
   *
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return The total value in dollars of the cart accounting taxes.
   */
  public static double checkout(String[] cart, int size) {
    double totalValue = 0;
    for (int i = 0; i < size; i++) {
      if (!(cart[i] == null)) {
        totalValue += getProductPrice(cart[i]) * (1 + TAX_RATE);
      }
    }
    return totalValue;
  }

  /**
   * Removes one occurrence of item from a given cart. If no match with item was found in the cart,
   * the method returns the same value of input size without making any change to the contents of
   * the array.
   *
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return The size of the oversize array cart after trying to remove item from the cart.
   */
  public static int removeItem(String[] cart, String item, int size) {
    for (int i = 0; i < size; i++) {
      if (!(cart[i] == null)) {
        if (cart[i].equals(item)) {
          cart[i] = cart[size - 1];
          cart[size - 1] = null;
          size -= 1;
          break;
        }
      }
    }
    return size;
  }

  /**
   * Removes all items from a given cart. The array cart must be empty (contains only null
   * references) after this method returns.
   *
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return The size of the cart after removing all its items.
   */
  public static int emptyCart(String[] cart, int size) {
    for (int i = 0; i < size; i++) {
      cart[i] = null;
      size -= 1;
    }
    return size;
  }
  
  /**
   * Returns a string representation of the summary of the contents of a given cart.
   *
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return A string representation of the summary of the contents of the cart
   */
  public static String getCartSummary(String[] cart, int size) {
    String cartSummary = "";
    String[] cartCopy = new String[size];
    for (int i = 0; i < size; i++) {
      cartCopy[i] = cart[i];
    }
    for (int i = 0; i < size - 1; i++) {
      for (int j = i + 1; j < size - 1; j++) {
        if (!(cartCopy[i] == null) && cartCopy[i].equals(cartCopy[j])) {
          cartCopy[j] = null;
        }
      }
    }
    for (int i = 0; i < size - 1; i++) {
      if (!(cartCopy == null) && cartCopy[size - 1].equals(cartCopy[i])) {
        cartCopy[size - 1] = null;
      }
    }
    for (int i = 0; i < size; i++) {
      if (!(cartCopy[i] == null)) {
        cartSummary += "(" + nbOccurrences(cart[i], cart, size) + ")" + " " + cartCopy[i] + "\n";
      }
    }
    return cartSummary;
  }
}

