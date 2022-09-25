import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of Song, LinkedNode, SongPlayer
 * ForwardSongIterator and BackwardSongIterator classes in P07 Iterable Song Player assignment.
 * 
 * @author Kavan Gandhi
 * @author Chinmay Koimuttum
 */
public class SongPlayerTester {
  /**
   * This method test and make use of the Song constructor, an accessor (getter) method, overridden
   * method toString() and equal() method defined in the Song class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSong() {
    // test constructor and methods with valid parameters
    {
      try {
        Song testSong = new Song("Out of Time", "The Weeknd", "3:34");
        Song testSong2 = new Song("Out of Time", "The Weeknd", "3:34");
        if (!testSong.getSongName().equals("Out of Time")) {
          System.out.println("error in Song() constructor or getter method");
          return false;
        }
        if (!testSong.getArtist().equals("The Weeknd")) {
          System.out.println("error in Song() constructor or getter method");
          return false;
        }
        if (!testSong.getDuration().equals("3:34")) {
          System.out.println("error in Song() constructor or getter method");
          return false;
        }
        if (!testSong.toString().equals("Out of Time---The Weeknd---3:34")) {
          System.out.println("error in overriding toString() method");
          return false;
        }
        if (!testSong.equals(testSong2)) {
          System.out.println("error in overriding equals() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected exception
        return false;
      }
    }

    // tests if expected errors are thrown
    {
      try {
        Song testSong = new Song("", null, null);
        return false;
      } catch (IllegalArgumentException e) {
        // expected error is caught, do nothing
      } catch (Exception e) {
        // unexpected error is caught
        return false;
      }

      try {
        Song testSong2 = new Song("Out of Time", "The Weeknd", "-1:340");
        return false;
      } catch (IllegalArgumentException e) {
        // expected error is caught, do nothing
      } catch (Exception e) {
        // unexpected error is caught
        return false;
      }
    }
    return true;
  }

  /**
   * This method test and make use of the LinkedNode constructor, an accessor (getter) method, and a
   * mutator (setter) method defined in the LinkedCart class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedNode() {
    // tests the linkedNode() constructor and an accessor and getter method
    {
      LinkedNode<Song> prev = null;
      LinkedNode<Song> next = null;
      Song testSong = new Song("Out of Time", "The Weeknd", "3:34");
      Song testSong2 = new Song("Out of Time", "The Weeknd", "3:34");
      try {
        LinkedNode<Song> testLinkedNode = new LinkedNode<Song>(prev, testSong, next);
        LinkedNode<Song> testLinkedNode2 = new LinkedNode<Song>(null, testSong2, null);
        if (testLinkedNode.getPrev() != null) {
          System.out.println("error in linkedNode() constructor or getter method");
          return false;
        }
        if (testLinkedNode.getNext() != null) {
          System.out.println("error in linkedNode() constructor or getter method");
          return false;
        }
        testLinkedNode.setNext(testLinkedNode2);
        testLinkedNode2.setPrev(testLinkedNode);
        if (!(testLinkedNode.getNext().getData().toString()
            .equals("Out of Time---The Weeknd---3:34"))) {
          System.out.println("error in setNext() method");
          return false;
        }
        if (!(testLinkedNode2.getPrev().getData().toString()
            .equals("Out of Time---The Weeknd---3:34"))) {
          System.out.println("error in setPrev() method");
          return false;
        }
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
    }

    // tests the exceptions the constructor can throw
    {
      LinkedNode<Song> prev = null;
      LinkedNode<Song> next = null;
      try {
        LinkedNode<Song> testLinkedNode = new LinkedNode<Song>(prev, null, next);
        return false;
      } catch (IllegalArgumentException e) {
        // expected error caught, do nothing
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of addFirst(), addLast() and add(int index) method in
   * SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerAdd() {
    // tests addFirst(), addLast() and add(int index) methods
    {
      SongPlayer songList = new SongPlayer();
      songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
      songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
      if (!songList.getFirst().getArtist().equals("Jay Chou")) {
        System.out.println("error in addFirst() method");
        return false;
      }
      if (!songList.getLast().getArtist().equals("The Weeknd")) {
        System.out.println("error in addLast() method");
        return false;
      }
      songList.add(1, new Song("Simple Love", "Jay Chou", "4:30"));
      if (!songList.get(1).getSongName().equals("Simple Love")) {
        System.out.println("error in add() method");
        return false;
      }
    }

    // tests if expected errors are thrown
    {
      Song testSong = null;
      SongPlayer songList = new SongPlayer();
      try {
        songList.addFirst(testSong);
        return false;
      } catch (NullPointerException e) {
        // expected error caught
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      try {
        songList.addLast(testSong);
        return false;
      } catch (NullPointerException e) {
        // expected error caught
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      try {
        songList.add(0, testSong);
        return false;
      } catch (NullPointerException e) {
        // expected error caught
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      testSong = new Song("Simple Love", "Jay Chou", "4:30");
      try {
        songList.add(-100, testSong);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected error caught
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of getFirst(), getLast() and get(int index) method in
   * SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerGet() {
    // tests getFirst(), getLast() and get(int index) methods
    {
      SongPlayer songList = new SongPlayer();
      songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
      songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
      songList.add(1, new Song("Simple Love", "Jay Chou", "4:30"));
      if (!songList.getFirst().getArtist().equals("Jay Chou")) {
        System.out.println("error in getFirst() method");
        return false;
      }
      if (!songList.getLast().getArtist().equals("The Weeknd")) {
        System.out.println("error in getLast() method");
        return false;
      }
      if (!songList.get(1).getSongName().equals("Simple Love")) {
        System.out.println("error in get() method");
        return false;
      }
    }

    // tests if expected errors thrown
    {
      SongPlayer songList = new SongPlayer();
      try {
        songList.getFirst();
        return false;
      } catch (NoSuchElementException e) {
        // expected error is thrown, do nothing
      } catch (Exception e) {
        // unexpected error is thrown
        return false;
      }
      try {
        songList.getLast();
        return false;
      } catch (NoSuchElementException e) {
        // expected error is thrown, do nothing
      } catch (Exception e) {
        // unexpected error is thrown
        return false;
      }
      try {
        songList.get(-100);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected error is thrown, do nothing
      } catch (Exception e) {
        // unexpected error is thrown
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of removeFirst(), removeLast() and remove(int index)
   * method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerRemove() {
    // tests the removeFirst(), removeLast() and remove(int index) methods
    {
      SongPlayer songList = new SongPlayer();
      songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
      songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
      songList.add(1, new Song("Simple Love", "Jay Chou", "4:30"));
      songList.removeFirst();
      if (!songList.getFirst().getSongName().equals("Simple Love")) {
        System.out.println("error in removeFirst() method");
        return false;
      }
      songList.removeLast();
      if (!songList.getLast().getSongName().equals("Simple Love")) {
        System.out.println("error in removeFirst() method");
        return false;
      }
      songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
      songList.remove(0);
      if (!songList.getLast().getSongName().equals("Save Your Tears")) {
        System.out.println("error in remove() method");
        return false;
      }
    }

    // tests if expected errors are thrown
    {
      SongPlayer songList = new SongPlayer();
      try {
        songList.removeFirst();
        return false;
      } catch (NoSuchElementException e) {
        // expected error caught, do nothing
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      try {
        songList.removeLast();
        return false;
      } catch (NoSuchElementException e) {
        // expected error caught, do nothing
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      Song testSong = new Song("Simple Love", "Jay Chou", "4:30");
      songList.addFirst(testSong);
      try {
        songList.remove(-100);
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected error caught
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of iterator(), switchPlayingDirection() and String
   * play() method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerIterator() {
    // tests the switchPlayingDirection() and play() methods
    {
      SongPlayer songList = new SongPlayer();
      songList.switchPlayingDirection();
      songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
      songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
      String expectedOutput =
          "Save Your Tears---The Weeknd---3:35" + "\n" + "Mojito---Jay Chou---3:05";
      if (!songList.play().equals(expectedOutput)) {
        System.out.println("error in switchPlayingDirection() or play() method");
        return false;
      }
    }

    // tests the iterator() method
    {
      SongPlayer songList = new SongPlayer();
      songList.switchPlayingDirection();
      if (!(songList.iterator() instanceof BackwardSongIterator)) {
        System.out.println("error with iterator() method");
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of contains(Object song), clear(), isEmpty()and size()
   * method in SongPlayer class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testSongPlayerCommonMethod() {
    // tests the contains(Object song), clear(), isEmpty() and size() methods
    {
      SongPlayer songList = new SongPlayer();
      songList.addFirst(new Song("Mojito", "Jay Chou", "3:05"));
      songList.addLast(new Song("Save Your Tears", "The Weeknd", "3:35"));
      if (songList.size() != 2) {
        System.out.println("error with the size() getter method");
        return false;
      }
      if (!songList.contains(new Song("Mojito", "Jay Chou", "3:05"))) {
        System.out.println("error with the contains() method");
        return false;
      }
      if (songList.isEmpty()) {
        System.out.println("error with the isEmpty() method");
        return false;
      }
      songList.clear();
      if (!songList.isEmpty()) {
        System.out.println("error with the clear() method");
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of ForwardSongIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testForwardSongIterator() {
    // tests ForwardSongIterator class
    {
      LinkedNode<Song> firstNode;
      LinkedNode<Song> secondNode = null;
      LinkedNode<Song> thirdNode = null;
      firstNode = new LinkedNode<Song>(null, new Song("Mojito", "Jay Chou", "3:05"), secondNode);
      secondNode = new LinkedNode<Song>(firstNode,
          new Song("Save Your Tears", "The Weeknd", "3:35"), thirdNode);
      thirdNode =
          new LinkedNode<Song>(secondNode, new Song("Simple Love", "Jay Chou", "4:30"), null);
      ForwardSongIterator forwardIterator = new ForwardSongIterator(firstNode);
      if (!forwardIterator.hasNext()) {
        System.out.println("error with hasNext() method");
        return false;
      }
      if (!forwardIterator.next().getSongName().equals("Mojito")) {
        System.out.println("error with next() method");
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks for the correctness of BackwardSongIterator class
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testBackwardSongIterator() {
    // tests BackwardSongIterator class
    {
      LinkedNode<Song> firstNode;
      LinkedNode<Song> secondNode = null;
      LinkedNode<Song> thirdNode = null;
      firstNode = new LinkedNode<Song>(null, new Song("Mojito", "Jay Chou", "3:05"), secondNode);
      secondNode = new LinkedNode<Song>(firstNode,
          new Song("Save Your Tears", "The Weeknd", "3:35"), thirdNode);
      thirdNode =
          new LinkedNode<Song>(secondNode, new Song("Simple Love", "Jay Chou", "4:30"), null);
      BackwardSongIterator backwardIterator = new BackwardSongIterator(thirdNode);
      if (!backwardIterator.hasNext()) {
        System.out.println("error with hasNext() method");
        return false;
      }
      if (!backwardIterator.next().getSongName().equals("Simple Love")) {
        System.out.println("error with next() method");
        return false;
      }
    }
    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your SongPlayerTester class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    return (testSong() && testLinkedNode() && testSongPlayerAdd() && testSongPlayerGet()
        && testSongPlayerRemove() && testSongPlayerIterator() && testSongPlayerCommonMethod()
        && testForwardSongIterator() && testBackwardSongIterator());
  }

  /**
   * Driver method defined in this SongPlayerTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println("testSong(): " + testSong());
    System.out.println("testLinkedNode(): " + testLinkedNode());
    System.out.println("testSongPlayerAdd(): " + testSongPlayerAdd());
    System.out.println("testSongPlayerGet(): " + testSongPlayerGet());
    System.out.println("testSongPlayerRemove(): " + testSongPlayerRemove());
    System.out.println("testSongPlayerIterator(): " + testSongPlayerIterator());
    System.out.println("testSongPlayerCommonMethod(): " + testSongPlayerCommonMethod());
    System.out.println("testForwardSongIterator(): " + testForwardSongIterator());
    System.out.println("testBackwardSongIterator(): " + testBackwardSongIterator());
    System.out.println("runAllTests(): " + runAllTests());
  }
}
