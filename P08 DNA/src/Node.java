/**
 * This is the class for the Node object
 *
 * @author Kavan Gandhi
 */
public class Node<T> {
  private T data; // The data contained in the Node
  private Node<T> next; // The Node following this one

  /**
   * Basic constructor; creates a node that contains the provided data and no linkages.
   *
   * @param data the information to put inside the node
   */
  public Node(T data) {
    this.data = data;
  }

  /**
   * A constructor that allows specification of the next node in the chain
   *
   * @param data the information to put inside the node
   * @param next the next node, must contain the same type of data as this node
   */
  public Node(T data, Node<T> next) {
    this.data = data;
    this.next = next;
  }

  /**
   * Accessor method for this node's data
   * 
   * @return the data contained in this node
   */
  public T getData() {
    return data;
  }

  /**
   * Accessor method for the node following this one
   * 
   * @return the next node
   */
  public Node<T> getNext() {
    return next;
  }

  /**
   * Mutator method for the node following this one
   * 
   * @param next the node to follow this one
   */
  public void setNext(Node<T> next) {
    this.next = next;
  }
}
