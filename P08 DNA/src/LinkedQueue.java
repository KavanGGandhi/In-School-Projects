import java.util.NoSuchElementException;

/**
 * This is the class for the LinkedQueue object
 *
 * @author Kavan Gandhi
 */
public class LinkedQueue<T> implements QueueADT<T> {
  private int n; // The number of elements in the queue
  protected Node<T> front; // The node at the front of the queue, added LEAST recently
  protected Node<T> back; // The node at the back of the queue, added MOST recently

  // Default constructor is used, no explicitly defined constructor

  /**
   * Adds the given data to this queue; every addition to a queue is made at the back
   *
   * @param data the data to add
   */
  @Override
  public void enqueue(T data) {
    if (!isEmpty()) {
      back.setNext(new Node<T>(data, null));
      back = back.getNext();
    } else {
      back = new Node<T>(data, null);
      front = back;
    }
    n++;
  }

  /**
   * Removes and returns the item from this queue that was least recently added
   * 
   * @return the item from this queue that was least recently added
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public T dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }

    T tempData = front.getData();
    front = front.getNext();
    if (front == null) {
      back = null;
    }
    n--;
    return tempData;
  }

  /**
   * Returns the item least recently added to this queue without removing it
   * 
   * @return the item from this queue that was least recently added
   * @throws NoSuchElementException if this queue is empty
   */
  @Override
  public T peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return front.getData();
  }

  /**
   * Checks whether the queue contains any elements
   * 
   * @return true if this queue is empty; false otherwise
   */
  @Override
  public boolean isEmpty() {
    return ((front == null) && (back == null));
  }

  /**
   * Returns the number of items in this queue
   * 
   * @return the number of items in this queue
   */
  @Override
  public int size() {
    return n;
  }

  /**
   * Returns a string representation of this queue, beginning at the front (least recently added) of
   * the queue and ending at the back (most recently added). An empty queue is represented as an
   * empty string; otherwise items in the queue are represented using their data separated by spaces
   * 
   * @return the sequence of items in FIFO order, separated by spaces
   */
  @Override
  public String toString() {
    String returnString = "";
    Node<T> tempNode = front;
    for (int i = 0; i < n; i++) {
      returnString += tempNode.getData() + " ";
      tempNode = tempNode.getNext();
    }
    return returnString.trim();
  }
}
