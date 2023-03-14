import edu.greenriver.sdev333.SymbolTable;
import edu.greenriver.sdev333.BST;
import edu.greenriver.sdev333.BinarySearchST;
import edu.greenriver.sdev333.SequentialSearchST;
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
    public static final String FILENAME = "333/333/SymbolTables/tale.txt";
    public static void main(String[] args) {
        System.out.println("Hello world!");

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
                    st.put(word, 1);
                }
                else {
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

        for (String currentWord : st.keys()) {
            int currentCount = st.get(currentWord);
            if (currentCount > maxCount) {
                maxWord = currentWord;
                maxCount = currentCount;
            }
        }

        System.out.println(maxWord + " " + maxCount);
    }
}