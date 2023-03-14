import edu.greenriver.sdev333.BST;
import edu.greenriver.sdev333.BinarySearchST;
import edu.greenriver.sdev333.SequentialSearchST;
import edu.greenriver.sdev333.SymbolTable;

import javax.security.auth.kerberos.KeyTab;
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
        SymbolTable<String, Integer> st = new BST();

        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            i++;
        }

        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }


        // Binary Search Tree assignment
        System.out.println("Binary Search Tree assignment");
        System.out.println("Binary min: " + ((BST<String, Integer>) st).min() + " Expected: A");
        System.out.println("Binary max: " + ((BST<String, Integer>) st).max() + " Expected: X");
        System.out.println("Binary floor: " + ((BST<String, Integer>) st).floor("B") + " Expected: A");
        System.out.println("Binary ceiling: " + ((BST<String, Integer>) st).ceiling("B") +" Expected: C");
        System.out.println("Binary select: " + ((BST<String, Integer>) st).select(0) +" Expected: A");
        System.out.println("Binary rank: " + ((BST<String, Integer>) st).rank("A") +" Expected: 0");
        System.out.println("------------------------------------------------------------------------------------");




    }
}
