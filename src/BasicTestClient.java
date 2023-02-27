import edu.greenriver.sdev333.BST;
import edu.greenriver.sdev333.SeparateChainingHashST;
import edu.greenriver.sdev333.SequentialSearchST;
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
        SymbolTable<String, Integer> st = new BST<String, Integer>();
        // You can replace the implementation with any class that implements
        // SymbolTable interface


        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            i++;
            System.out.println("Current Key: " + key);
        }

        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }

        System.out.println("Size: " + st.size());
    }
}
