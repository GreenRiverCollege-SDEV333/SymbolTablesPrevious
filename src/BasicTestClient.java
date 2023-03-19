import edu.greenriver.sdev333.*;

import java.util.Scanner;

/**
 * Basic symbol table test client
 * p. 370 of Sedgewick and Wayne
 * Authors have a running example throughout the text, with pictures of
 *  "S I M P L E E X A M P L E" and we rewrote the client to be in our style
 *  of writing Java code in our program.
 *
 *  Must extend to test BST class methods, min, max, floor, ceiling, select, and rank.
 *
 */
public class BasicTestClient {
    public static void main(String[] args) {
        String inputString = "S E A R C H E X A M P L E";

        Scanner input = new Scanner(inputString);

        // You can replace the implementation with any class that implements
        // SymbolTable interface
        //SymbolTable<String, Integer> st = new TreeMapWrapper<>();
        //SymbolTable<String, Integer> st = new SeparateChainingHashST<>(100);
        //SymbolTable<String, Integer> st = new SequentialSearchST<>();
        SymbolTable<String, Integer> st = new RedBlackBST<>();

        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            i++;
        }

        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }

        System.out.println("min() " + st.min());
        System.out.println("max() " + st.max());
        System.out.println("floor('A') " + st.floor("A"));
        System.out.println("floor('X') " + st.floor("X"));
        System.out.println("floor('G') " + st.floor("G") + " (note: 'G' is not in list)");
        System.out.println("ceiling('A') " + st.ceiling("A"));
        System.out.println("ceiling('X') " + st.ceiling("X"));
        System.out.println("ceiling('G') " + st.ceiling("G") + " (note: 'G' is not in list)");
        System.out.println("rank('A') " + st.rank("A"));
        System.out.println("rank('X') " + st.rank("X"));
        System.out.println("rank('G') " + st.rank("G") + " (note: 'G' is not in list)");
        System.out.println("select(0) " + st.select(0));
        System.out.println("select(9) " + st.select(9));
    }
}