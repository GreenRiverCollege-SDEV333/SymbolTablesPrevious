import edu.greenriver.sdev333.BST;
import edu.greenriver.sdev333.BinarySearchST;
import edu.greenriver.sdev333.SymbolTable;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Frequency Table is a symbol-table client
 * This is a rewriting of what is on p. 372 in Sedgewick and Wayne, Algorithms, 4th edition
 * to use some of the Java practices that you may be more familiar with and perhaps
 * to expand some of the code where they chose to be more verbose/efficient in terms
 * of lines of code.
 */
public class FrequencyCounter {
    public static final int MINLEN = 1;
    public static final String FILENAME = "tale.txt";
    public static void main(String[] args) {
        //System.out.println("Hello world!");

        SymbolTable<String, Integer> st = new BST<>();

        try {
            Scanner input = new Scanner(new File(FILENAME));
            while (input.hasNext()) {
                String word = input.next();

                // ignore short keys
                if (word.length() < MINLEN) {
                    continue;
                }

                if (!st.contains(word)) {
                    // if the word is not in the symbol table
                    // put it in with a value of 1
                    //System.out.println("New Word: "+word);
                    st.put(word, 1);
                } else {
                    //System.out.println("Existing Word: "+word);
                    int count = st.get(word);   // get existing word count
                    count++;                    // increment/update the count
                    st.put(word, count);        // put updated word count
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Find a key with the highest frequency count
        String maxWord = "";
        int maxCount = 0;

        //System.out.println("doing this now...");
        for (String currentWord : st.keys()) {
            //System.out.println(currentWord);
            int currentCount = st.get(currentWord);
            if (currentCount > maxCount) {
                maxWord = currentWord;
                maxCount = currentCount;
            }
        }

        System.out.println("Most frequent word: "+maxWord + "  Count: " + maxCount);
    }
}