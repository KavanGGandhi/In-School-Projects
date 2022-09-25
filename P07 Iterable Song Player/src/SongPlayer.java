import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is the class for the SongPlayer object
 *
 * @author Kavan Gandhi
 * @author Chinmay Koimuttum
 */
public class SongPlayer implements Iterable<Song> {
  private int size; // size of the list
  private LinkedNode<Song> head; // head of this doubly linked list
  private LinkedNode<Song> tail; // tail of this doubly linked list
  private boolean playingBackward; // true if this song player is reading the list backward

  // default constructor will create the constructor required by the specification

  /**
   * Adds a Song as Last Song
   * 
   * @param oneSong - the song that is going to be added to the tail of this doubly linked list of
   *                songs
   */
  public void addLast(Song oneSong) {
    if (oneSong == null) {
      throw new NullPointerException("Passed parameter is null");
    }
    LinkedNode<Song> lastSongNode = new LinkedNode<Song>(null, oneSong, null);
    // if list is empty, both head and tail are song
    if (isEmpty()) {
      head = lastSongNode;
      tail = lastSongNode;
      // else, tail becomes passed song
    } else {
      tail.setNext(lastSongNode);
      lastSongNode.setPrev(tail);
      tail = lastSongNode;
    }
    size++;
  }

  /**
   * add Song as First Song
   * 
   * @param oneSong - the song that is going to be added to the head of this doubly linked list of
   *                songs
   *
   * @throws NullPointerException - with a descriptive error message if the passed oneSong is null
   */
  public void addFirst(Song oneSong) throws NullPointerException {
    if (oneSong == null) {
      throw new NullPointerException("Passed parameter is null");
    }
    LinkedNode<Song> firstSongNode = new LinkedNode<Song>(null, oneSong, null);
    // if list is empty, both head and tail are song
    if (isEmpty()) {
      head = firstSongNode;
      tail = firstSongNode;
      // else head becomes passed song
    } else {
      head.setPrev(firstSongNode);
      firstSongNode.setNext(head);
      head = firstSongNode;
    }
    size++;
  }

  /**
   * adds Song at a given position/order within this collection of songs
   * 
   * @param index   - the given index where the new song will be added
   * @param oneSong - the song that is going to be added
   *
   * @throws NullPointerException      - with a descriptive error message if the passed oneSong is
   *                                   null
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
   *                                   .. size() range
   */
  public void add(int index, Song oneSong) throws NullPointerException, IndexOutOfBoundsException {
    if (oneSong == null) {
      throw new NullPointerException("Passed parameter is null");
    }
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Given index is invalid");
    }

    LinkedNode<Song> songNode = new LinkedNode<Song>(null, oneSong, null);
    LinkedNode<Song> tempNode = head;
    if (index == 0) {
      addFirst(oneSong);
      return;
    } else if (index == size) {
      addLast(oneSong);
      return;
    } else {
      int i = 0;
      // temporary node goes right before given index
      while (i < index - 1) {
        tempNode = tempNode.getNext();
        i++;
      }
      // use temporary node's data to set given song node's data and add
      songNode.setPrev(tempNode);
      songNode.setNext(tempNode.getNext());
      tempNode.setNext(songNode);
      songNode.getNext().setPrev(songNode);
      songNode = tempNode.getNext();
    }
    size++;
  }

  /**
   * Returns the first Song in this list.
   *
   * @return the Song at the head of this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song getFirst() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    return head.getData();
  }

  /**
   * Returns the last Song in this list.
   *
   * @return the Song at the tail of this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song getLast() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    return tail.getData();
  }

  /**
   * Returns the song at the specified position in this list.
   * 
   * @param index - index of the song to return
   *
   * @return the song at the specified position in this list
   *
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
   *                                   .. size()-1 range
   */
  public Song get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("Given index is invalid");
    }
    int i = 0;
    LinkedNode<Song> tempNode = head;
    // temporary node goes to index
    while (i < index) {
      tempNode = tempNode.getNext();
      i++;
    }
    return tempNode.getData();
  }

  /**
   * Removes and returns the first song from this list.
   *
   * @return the first song from this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song removeFirst() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    Song tempSong = head.getData();
    // shift head one space to right, remove previous head
    if (head != tail) {
      head = head.getNext();
      head.setPrev(null);
      // else if only one element, set head and tail to null
    } else {
      head = null;
      tail = null;
    }
    size--;
    return tempSong;
  }

  /**
   * Removes and returns the last song from this list.
   *
   * @return the last song from this list
   *
   * @throws java.util.NoSuchElementException - with a descriptive error message if this list is
   *                                          empty
   */
  public Song removeLast() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("List is empty");
    }
    Song tempSong = tail.getData();
    // shift tail one space to left, remove previous tail
    if (head != tail) {
      tail = tail.getPrev();
      tail.setNext(null);
      // else if only one element, set head and tail to null
    } else {
      head = null;
      tail = null;
    }
    size--;
    return tempSong;
  }

  /**
   * Removes the song at the specified position in this list and returns the song that was removed
   * from the list. The order of precedence of the other songs in the list should not be modified.
   * 
   * @param index - the index of the song to be removed
   *
   * @return the song previously at the specified position
   *
   * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
   *                                   .. size()-1 range
   */
  public Song remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("Given index is invalid");
    }
    Song tempSong = get(index);
    LinkedNode<Song> tempNode = head;
    int i = 0;
    // temporary node goes to index
    while (i < index) {
      tempNode = tempNode.getNext();
      i++;
    }
    if (index == 0) {
      return removeFirst();
    } else if (index == size - 1) {
      return removeLast();
    } else {
      tempNode.getPrev().setNext(tempNode.getNext());
      tempNode.getNext().setPrev(tempNode.getPrev());
    }
    tempNode = null;
    size--;
    return tempSong;
  }

  /**
   * Returns true if this list contains a match with the specified song. More formally, returns true
   * if and only if this list contains at least one song e such that Objects.equals(o, e).
   * 
   * @param o - song whose presence in this list is to be tested
   *
   * @return true if this list contains the specified song
   */
  public boolean contains(Song o) {
    int i = 0;
    LinkedNode<Song> tempNode = head;
    // for entire size of linked list, compare each node
    do {
      if (tempNode.getData().equals(o)) {
        return true;
      }
      tempNode = tempNode.getNext();
      i++;
    } while (i < size);
    return false;
  }

  /**
   * Removes all of the songs from this list. The list will be empty after this call returns.
   */
  public void clear() {
    LinkedNode<Song> tempNode;
    while (head != null) {
      tempNode = head;
      head = head.getNext();
      tempNode = null;
    }
    tail = null;
    size = 0;
  }

  /**
   * Returns true if this list is empty.
   *
   * @return true if this list is empty
   */
  public boolean isEmpty() {
    return ((head == null) && (tail == null) && (size == 0));
  }

  /**
   * Returns the number of songs in this list.
   *
   * @return the number of songs in this list
   */
  public int size() {
    return size;
  }

  /**
   * Returns an iterator to iterate through the songs in this list with respect to current playing
   * direction of this song player (either in the forward or in the backward direction)
   *
   * @return an Iterator to traverse the list of songs in this SongPlayer with respect to the
   *         current playing direction specified by the playingBackward data field.
   */
  @Override
  public Iterator<Song> iterator() {
    if (playingBackward) {
      return new BackwardSongIterator(tail);
    }
    return new ForwardSongIterator(head);
  }

  /**
   * Mutator of the playingDirection of this song player. It switches the playing direction by
   * setting playingBackward to its opposite value.
   */
  public void switchPlayingDirection() {
    if (playingBackward) {
      playingBackward = false;
    } else {
      playingBackward = true;
    }
  }

  /**
   * Plays the songs in this song player in the current playing direction. This method MUST be
   * implemented using an enhanced for-each loop.
   *
   * @return a String representation of the songs in this song player. String representations of
   *         each song are separated by a newline. If this song player is empty, this method returns
   *         an empty string.
   */
  public String play() {
    String returnString = "";
    for (Song song : this) {
      returnString += song.toString() + "\n";
    }
    return returnString.trim();
  }
}
