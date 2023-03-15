import edu.greenriver.sdev333.*;
import edu.greenriver.sdev333.SymbolTable;
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
        SymbolTable<String, Integer> st = new TreeMapWrapper<>();
        SequentialSearchST<String, Integer> aat = new SequentialSearchST<>();
        BST<String, Integer> at =new BST<>();
        SeparateChainingHashST<String, Integer> t =new SeparateChainingHashST<>();
        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            aat.put(key, i);
            at.put(key, i);
            t.put(key, i);
            i++;
        }
        System.out.println("TreeMapWrapper");
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
        System.out.println("SequentialSearchST");
        for (String s : aat.keys()) {
            System.out.println(s + " " + aat.get(s));
        }
        System.out.println("BST");
        for (String s : at.keys()) {
            System.out.println(s + " " + at.get(s));
        }System.out.println("SeparateChainingHashST");
        for (String s : t.keys()) {
            System.out.println(s + " " + t.get(s));
        }
    }
}
