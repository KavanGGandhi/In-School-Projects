import java.util.NoSuchElementException;

/**
 * Test methods to verify the implementation of the methods for P08.
 * 
 * @author Kavan Gandhi
 */
public class DNATester {

  /**
   * Tests the enqueue() and dequeue() methods
   * 
   * @return true if and only if the methods works correctly
   */
  public static boolean testEnqueueDequeue() {
    // tests the enqueue() method
    {
      LinkedQueue<Character> testQueue = new LinkedQueue<Character>();
      try {
        testQueue.enqueue('G');
        testQueue.enqueue('G');
        testQueue.enqueue('A');
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      if (!testQueue.toString().trim().equals("G G A")) {
        System.out.println("Error with enqueue method");
        return false;
      }
    }

    // tests the dequeue() method
    {
      LinkedQueue<Character> testQueue = new LinkedQueue<Character>();
      try {
        testQueue.enqueue('G');
        testQueue.enqueue('G');
        testQueue.enqueue('A');
        testQueue.enqueue('G');
        testQueue.enqueue('G');
        testQueue.enqueue('A');
        testQueue.dequeue();
        testQueue.dequeue();
        testQueue.dequeue();
      } catch (NoSuchElementException e) {
        // caught expected error, do nothing
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
      if (!testQueue.toString().trim().equals("G G A")) {
        System.out.println("Error with dequeue method");
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the isEmpty() and size() methods
   * 
   * @return true if and only if the methods works correctly
   */
  public static boolean testQueueSize() {
    // tests the isEmpty() method
    {
      LinkedQueue<Character> testQueue = new LinkedQueue<Character>();
      try {
        if (!testQueue.isEmpty()) {
          System.out.println("Error with isEmpty method");
          return false;
        }
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
    }

    // tests the size() method
    {
      LinkedQueue<Character> testQueue = new LinkedQueue<Character>();
      try {
        testQueue.enqueue('G');
        testQueue.enqueue('G');
        testQueue.enqueue('A');
        if (testQueue.size() != 3) {
          System.out.println("Error with size method");
          return false;
        }
      } catch (Exception e) {
        // unexpected error caught
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the transcribeDNA() method
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testTranscribeDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
    System.out.println(testDNA.transcribeDNA().toString());
    if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
      return true;
    }
    return false;
  }

  /**
   * Tests the translateDNA() method
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    System.out.println(testDNA.translateDNA().toString());
    if (testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIRWPCMTEPLEARSFRD")) {
      return true;
    }
    return false;
  }

  /**
   * Tests the mRNATranslate() method
   * 
   * @return true if and only if the method works correctly
   */
  public static boolean testMRNATranslate() {
    DNA testDNA = new DNA("CCGGCCCTCCGGTGGATCCAA");
    String expectedString = "G R E A T";
    try {
      if (!testDNA.mRNATranslate​(testDNA.transcribeDNA()).toString().trim()
          .equals(expectedString)) {
        System.out.println("Error with mRNATranslate method");
        return false;
      }
    } catch (Exception e) {
      // unexpected error caught
      return false;
    }
    return true;
  }

  /**
   * Main method - use this to run your test methods and output the results (ungraded)
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("testEnqueueDequeue:" + testEnqueueDequeue());
    System.out.println("testQueueSize:" + testQueueSize());
    System.out.println("transcribeDNA: " + testTranscribeDNA());
    System.out.println("translateDNA: " + testTranslateDNA());
    System.out.println("testMRNATranslate: " + testMRNATranslate());
  }

}
