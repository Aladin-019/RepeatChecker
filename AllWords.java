// Aladin Alihodzic

import java.io.BufferedReader;

/**
 * A two-dimensional representation of all words in the document.
 * Each line is a row. AllWords being a list of all rows.
 */
public class AllWords {

    /**
     * list of each row in document (containing only words)
     */
    String[][] All;

    /**
     * position of current word being analyzed in document
     * (so we don't compare current word to itself)
     */
    int[] currentPosition;

    /**
     * read and parse document and fill All
     * @param inFile file reader to be analyzed
     * @return new AllWords object
     */
    public AllWords initAllWords(BufferedReader inFile){
        // stub
        return;
    }

    public static void main(String[] args){

    }

}
