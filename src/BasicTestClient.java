import edu.greenriver.sdev333.*;

import javax.security.auth.kerberos.KeyTab;
import java.security.Key;
import java.util.Iterator;
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
        SymbolTable<String, Integer> scHashST = new SeparateChainingHashST<>(100);
        SymbolTable<String, Integer> st = new BST<>();
        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            scHashST.put(key, i);
            i++;
        }

        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }


        // Binary Search Tree assignment
        System.out.println("Binary Search Tree assignment");
        System.out.println("Binary min: " + ((BST<String, Integer>) st).min() + " Expected: A");
        System.out.println("Binary max: " + ((BST<String, Integer>) st).max() + " Expected: X");
        System.out.println("Binary floor of 'B': " + ((BST<String, Integer>) st).floor("B") + " Expected: A");
        System.out.println("Binary ceiling of 'B': " + ((BST<String, Integer>) st).ceiling("B") +" Expected: C");
        System.out.println("Binary select 0: " + ((BST<String, Integer>) st).select(0) +" Expected: A");
        System.out.println("Binary rank of 'A': " + ((BST<String, Integer>) st).rank("A") +" Expected: 0");
        System.out.println("------------------------------------------------------------------------------------");

        //SeparateChainingHashST - get, put, keys, size, contains
        System.out.println("SeparateChainingHashST assignment");
        System.out.println("Get A is " + scHashST.get("A"));
        System.out.println("Put already done below the create new hash object!");
        System.out.println("Size of scHashST is " + scHashST.size());
        System.out.println("A contained in scHashST: " + scHashST.contains("A"));

        Iterator itr = scHashST.keys().iterator();

        System.out.print("Keys() iterator method test: ");
        while(itr.hasNext()) {
            String element = (String) itr.next();
            System.out.print(element + " ");
        }
    }
}
