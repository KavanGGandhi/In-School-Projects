/**
 * This is the class for the DNA object
 *
 * @author Kavan Gandhi
 */
public class DNA {
  // A two-dimensional array containing the mRNA codons in index 0 and the corresponding amino acid
  // (or STOP) in index 1
  protected static String[][] mRNAtoProteinMap =
      {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
          {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
          {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
          {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
          {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
          {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
          {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
          {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
          {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
          {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
          {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};

  protected LinkedQueue<Character> DNA; // The queue containing the original DNA sequence

  /**
   * Creates the DNA queue from the provided String. Each Node contains a single Character from the
   * sequence.
   *
   * @param sequence a String containing the original DNA sequence
   */
  public DNA(String sequence) {
    DNA = new LinkedQueue<Character>();
    String sequenceWithoutSpaces = sequence.replaceAll(" ", "");
    for (int i = 0; i < sequenceWithoutSpaces.trim().length(); i++) {
      DNA.enqueue(sequenceWithoutSpaces.charAt(i));
    }
  }

  /**
   * Creates and returns a new queue of mRNA characters from the stored DNA. The transcription is
   * done one character at a time, as (A->U, T->A, C->G, G->C).
   * 
   * @return the queue containing the mRNA sequence corresponding to the stored DNA sequence
   */
  public LinkedQueue<Character> transcribeDNA() {
    LinkedQueue<Character> tempLinkedQueue = new LinkedQueue<Character>();
    char tempChar;
    char tempChar2 = 0;
    int size = DNA.size();
    int i = 0;
    while (i < size) {
      tempChar = DNA.dequeue();
      if (tempChar == 'A') {
        tempChar2 = 'U';
      } else if (tempChar == 'T') {
        tempChar2 = 'A';
      } else if (tempChar == 'C') {
        tempChar2 = 'G';
      } else if (tempChar == 'G') {
        tempChar2 = 'C';
      }
      tempLinkedQueue.enqueue(tempChar2);
      DNA.enqueue(tempChar);
      i++;
    }
    return tempLinkedQueue;
  }

  /**
   * Creates and returns a new queue of amino acids from a provided queue of mRNA characters. The
   * translation is done three characters at a time, according to the static mRNAtoProteinMap
   * provided above. Translation ends either when you run out of mRNA characters OR when a STOP
   * codon is reached (i.e. the three-character sequence corresponds to the word STOP in the map,
   * rather than a single amino acid character).
   * 
   * @param mRNA a queue containing the mRNA sequence corresponding to the stored DNA sequence
   * @return the queue containing the amino acid sequence corresponding to the provided mRNA
   *         sequence
   */
  public LinkedQueue<String> mRNATranslate​(LinkedQueue<Character> mRNA) {
    char c1;
    char c2;
    char c3;
    String proteinMappedString = "";
    boolean stop = false;
    int size = mRNA.size();
    LinkedQueue<String> tempLinkedQueue = new LinkedQueue<String>();
    for (int i = 0; i < size && !stop; i++) {
      if (mRNA.size() >= 3) {
        c1 = mRNA.dequeue();
        c2 = mRNA.dequeue();
        c3 = mRNA.dequeue();
        proteinMappedString +=
            Character.toString(c1) + Character.toString(c2) + Character.toString(c3);
        for (int j = 0; j < mRNAtoProteinMap.length && !stop; j++) {
          if (proteinMappedString.equals(mRNAtoProteinMap[j][0])) {
            if (mRNAtoProteinMap[j][1].equals("STOP")) {
              stop = true;
              break;
            }
            tempLinkedQueue.enqueue(mRNAtoProteinMap[j][1]);
            proteinMappedString = "";
          }
        }
      } else {
        break;
      }
    }
    return tempLinkedQueue;
  }

  /**
   * A shortcut method that translates the stored DNA sequence to a queue of amino acids using the
   * other two methods in this class
   * 
   * @return the queue containing the amino acid sequence corresponding to the stored DNA sequence,
   *         via its mRNA transcription
   */
  public LinkedQueue<String> translateDNA() {
    return mRNATranslate​(transcribeDNA());
  }
}
