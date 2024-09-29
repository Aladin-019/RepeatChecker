//Aladin Alihodzic

import java.util.*;

/**
 * Helpful for identifying repeat words/phrases or duplicate references
 * in written documents.
 *
 */

public class RepeatChecker{

    Word[] repeats;   //list of repeated Word objects

    //common pronouns and words that are permitted repetition
    private static final Set<String> commonWords = new HashSet<>(Arrays.asList("yes", "no", "a", "any", "anything", "as", "both", "each",
            "everything", "few", "he", "her", "hers", "herself", "him", "himself", "his", "i", "it", "its", "it`s",
            "itself", "me", "mine", "most", "my", "myself", "neither", "nor", "not", "none", "nothing", "on", "one",
            "another", "other", "others", "our", "ours", "ourself", "ourselves", "she", "some", "somebody", "someone",
            "something", "somewhat", "such", "that", "their", "theirs", "them", "there", "these", "they", "they`re",
            "this", "those", "us", "we", "what", "where", "whether", "which", "who", "whom", "whose", "you", "your",
            "you`re", "yours", "is", "am", "be", "of", "the", "are", "for", "and", "or", "either", "everyone"));

    AllWords all;  // 2D array of all words in file

    public RepeatChecker(String filename){
        this.all = new AllWords(filename);

    }

    /**
     * Sort repeats list by most-to-least repeated
     */
    public void sortRepeats(){
        // stub
        // extract then use quicksort
    }

    /**
     * finds repeat words and their occurrence/repeat count
     * uses hashmap to do so
     * @param all - 2D array of all words in input document
     */
    public Map<String, Integer> countRepeats(AllWords all){
        Map<String, Integer> wordMap = new HashMap<>();  // hashmap of key (word) and value (count) of that word
        // for each element/word of 2D array put word inside map
        // and let value be the previous value + 1
        for (int row=0; row<all.All.length; row++){
            for (int word = 0; word < all.All[row].length; word++) {
                if (all.All[row][word] != null && !Objects.equals(all.All[row][word], "")){
                    // Line below initializes new word counts and counts existing words simultaneously.
                    // getOrDefault(x, y) get value of x (count of word), and if doesn't exsist in map
                    // yet it sets it to y (0). Put updates the new word count of the current word.
                    wordMap.put(all.All[row][word], wordMap.getOrDefault(all.All[row][word], 0) + 1);
                }
            }
        }
        return wordMap;
    }

    /**
     * Removes commonly repeated (unavoidable) words from wordMap
     * @param wordMap - HashMap of words in document
     * @return - cleaned up wordMap
     */
    public Map<String, Integer> removeCommonWords(Map<String, Integer> wordMap){
        // for each key iterate over entry set
        Iterator<Map.Entry<String, Integer>> iterator = wordMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            // for each entry set if entry key is contained in commonWords Set
            if (commonWords.contains(entry.getKey())) {
                iterator.remove();   // remove entry
            }
        }
        return wordMap;
    }

    /**
     * displays the repeat words along with their repeat count
     * @param wordMap - HashMap (each unique word mapped to its occurrence number)
     */
    public void displayRepeats(Map<String, Integer> wordMap){
        // for all key-value entries display key and value
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void main(String[] args){
        RepeatChecker checker = new RepeatChecker("testfile.txt");
        Map<String, Integer> repeatCounts, cleanedRepeats;
        repeatCounts = checker.countRepeats(checker.all);
        cleanedRepeats = checker.removeCommonWords(repeatCounts);
        checker.displayRepeats(cleanedRepeats);

    }
}

