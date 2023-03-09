import edu.greenriver.sdev333.*;

import java.util.Scanner;

/**
 * Basic symbol table test client
 * p. 370 of Sedgewick and Wayne
 * Authors have a running example throughout the text, with pictures of
 *  "S I M P L E E X A M P L E" and we rewrote the client to be in our style
 *  of writing Java code in our program.
 */
public class BasicTestClient {
    public static void main(String[] args) {
        String inputString = "S E A R C H E X A M P L E";

        Scanner input = new Scanner(inputString);

        // You can replace the implementation with any class that implements
        // SymbolTable interface
        BST<String, Integer> st = new BST<>();

        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            System.out.println("the rank is :" + st.rank(key));
            System.out.println("the floor is :" + st.floor(key));
            System.out.println("the ceiling is :" + st.ceiling(key));
            i++;



        }
        System.out.println("the max is :" + st.max());
        System.out.println("the min is :" + st.min());
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
}
