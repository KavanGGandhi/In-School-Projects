/**
 * This is the class for the LinkedNode object
 *
 * @author Kavan Gandhi
 * @author Chinmay Koimuttum
 */
public class LinkedNode<T> {
  private T data; // data field of this linked node
  private LinkedNode<T> prev; // reference to the previous linked node in a list of nodes
  private LinkedNode<T> next; // reference to the next linked node in a list of nodes

  /**
   * Initializes a new node with the provided information.
   * 
   * @param prev - node, which comes before this node in a doubly linked list
   * @param data - to be stored within this node
   * @param next - node, which comes after this node in a doubly linked list
   *
   * @throws IllegalArgumentException - with a descriptive error message if data is null
   */
  public LinkedNode(LinkedNode<T> prev, T data, LinkedNode<T> next)
      throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("data can not be null");
    }
    this.prev = prev;
    this.data = data;
    this.next = next;
  }

  /**
   * Accessor method for this node's data.
   *
   * @return the data stored within this node.
   */
  public T getData() {
    return data;
  }

  /**
   * Accessor method for this node's previous node reference.
   *
   * @return the reference to the node that comes before this one in the list or null when this is
   *         the first node in that list
   */
  public LinkedNode<T> getPrev() {
    return prev;
  }

  /**
   * Mutator method for this node's previous node reference.
   * 
   * @param prev - node, which comes before this node in a doubly linked list
   */
  public void setPrev(LinkedNode<T> prev) {
    this.prev = prev;
  }

  /**
   * Accessor method for this node's next node reference.
   *
   * @return the next reference to the node that comes after this one in the list, or null when this
   *         is the last node in that list
   */
  public LinkedNode<T> getNext() {
    return next;
  }

  /**
   * Mutator method for this node's next node reference.
   * 
   * @param next - node, which comes after this node in a doubly linked list
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}
