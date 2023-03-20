import edu.greenriver.sdev333.BST;
import edu.greenriver.sdev333.OrderedSymbolTable;
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

        // OrderedSymbolTable interface
        OrderedSymbolTable<String, Integer> st = new BST<>();

        int i = 0;
        while (input.hasNext()) {
            String key = input.next();
            st.put(key, i);
            i++;
        }

        // Test min
        System.out.println("Min key: " + st.min());

        // Test max
        System.out.println("Max key: " + st.max());

        // Test floor
        String floorKey = "F";
        System.out.println("Floor of " + floorKey + ": " + st.floor(floorKey));

        // Test ceiling
        String ceilingKey = "C";
        System.out.println("Ceiling of " + ceilingKey + ": " + st.ceiling(ceilingKey));

        // Test select
        int k = 3;
        System.out.println("Key with rank " + k + ": " + st.select(k));

        // Test rank
        String rankKey = "E";
        System.out.println("Rank of " + rankKey + ": " + st.rank(rankKey));
    }
}
