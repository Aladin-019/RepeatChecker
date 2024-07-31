// Aladin Alihodzic

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * A two-dimensional representation of all words in the document.
 * Each line is a row of all words. AllWords being a list of all rows.
 */
public class AllWords{

    /**
     * list of each row in document (containing only words)
     */
    String[][] All = new String[100][100];

    /**
     * position of current word being analyzed in document
     * (so we don't compare current word to itself)
     * index 0 = row, index 1 = column/word in that row
     */
    int[] currentPosition = new int[2];

    /**
     * AllWords simple constructor
     * current position at first file entry
     */
    public AllWords(String filename){
        initAll(filename);  // initialize All
        this.currentPosition = new int[]{0, 0};  // current position at beginning of file
    }

    /**
     * AllWords constructor
     * choose current position
     */
    public AllWords(String filename, int[] currentPosition){
        initAll(filename);  // initialize All
        this.currentPosition = currentPosition;  // set current position
    }

    /**
     * Removes common punctuation characters found at the beginning of a word
     * @param word - old word
     * @return new word
     */
    public String cleanHead(String word){
        char firstChar;
        int len = word.length();
        // if empty string, return empty string immediately
        // prevents StringIndexOutOfBoundsException later in the program
        if (len < 1){
            return word;
        }

        int count = 0;
        firstChar = word.charAt(count);  //current character being checked (starting from front)
        // while characters left in word and have a match, go to next char
        while ((firstChar=='.' || firstChar==',' || firstChar==':' || firstChar==';' || firstChar=='{' || firstChar=='['
                || firstChar=='?' || firstChar=='!'|| firstChar=='(' || firstChar=='<' || firstChar=='-'
                || firstChar=='\'' || firstChar=='\"') && count < len){

            //if match and it`s the only character left, return empty string
            if (count == len - 1){
                return "";
            }
            count++;
            firstChar = word.charAt(count);
        }

        return word.substring(count, len);   // return new string with undesired beginning left out
    }

    /**
     * removes common punctuation characters found at the end of a word
     * @param word - old word
     * @return new word
     */
    public String cleanTail(String word){
        char lastChar;
        int len = word.length();
        // if empty string, return empty string immediately
        // prevents StringIndexOutOfBoundsException later in the program
        if (len < 1){
            return word;
        }

        lastChar = word.charAt(len-1); //current character being checked (starting from back)
        // while characters left in word and have a match, go to next char
        while ((lastChar=='.' || lastChar==',' || lastChar==':' || lastChar==';' || lastChar=='?' || lastChar=='!'
                || lastChar=='}'|| lastChar==']' || lastChar==')' || lastChar=='>' || lastChar=='-'
                || lastChar=='\'' || lastChar=='\"') && len > 0) {

            //if match and it`s the only character left, return empty string
            if (len == 1){
                return "";
            }
            len--;
            lastChar = word.charAt(len - 1);
        }

        return word.substring(0, len);  // return new string with undesired end left out
    }

    /**
     * Read and parse document and fill All (2D array) with only the words of the file
     * @param filename file to be read
     */
    private void initAll(String filename) {
        BufferedReader fileReader;
        int wordNum;  // word number of current row (column)
        try {
            fileReader = new BufferedReader(new FileReader(filename));
            String line = fileReader.readLine();   // line of document
            int lineNum = 0;   // current line number (row)
            String[] lineArr;  // String Array of line

            // while not at end of file
            while (line != null) {
                wordNum = 0;
                lineArr = line.toLowerCase().split("\\s+");   // parse and clean up
                // for each line array clean up leading and trailing punctuation and store into
                // All 2D array.
                for (int i = 0; i < lineArr.length; i++) {
                    lineArr[i] = this.cleanHead(lineArr[i]);
                    lineArr[i] = this.cleanTail(lineArr[i]);
                    // only store non-null strings
                    if (lineArr[i] != null && !Objects.equals(lineArr[i], "")){
                        All[lineNum][wordNum] = lineArr[i];
                        wordNum++;
                    }
                }
                lineNum++;
                line = fileReader.readLine();  //next line
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

        /**
         * change current position to new position
         * @param pos new position
         */
        public void setCurrentPosition (int[] pos){
            this.currentPosition = pos;
        }

        /**
         * create string of All for testing
         * @return
         */
    public String toString(){
        String result = "";
        for (int i=0; i<100; i++){
            for (int j=0; j<100; j++) {
                if (All[i][j] != null) {
                    result = result + " " + All[i][j];
                }
            }
            result = result + "\n";
        }
        return result;
    }

        // main
        public static void main (String[] args){

            // test AllWords constructor on text file, display result
            // (also tests initAll, cleanHead, and cleanTail in the process)
            // New test cases added to single testfile.txt document as testing progresses.
            // tests for cleanHead and cleanTail are described in testfile.txt
            AllWords words = new AllWords("testfile.txt");
            String displayAll;
            displayAll = words.toString();
            System.out.println(displayAll);
        }
}
