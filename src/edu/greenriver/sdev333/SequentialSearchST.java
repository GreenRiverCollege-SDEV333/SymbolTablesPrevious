package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    // field
    private Node first;

    private class Node {
        // linked list node
        private KeyType key;
        private ValueType val;
        private Node next;

        public Node(KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(KeyType key, ValueType val) {
        // search for key
        // update value if found; grow table if new
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return; // search hit: update val
            }
        }
        first = new Node(key, val, first); // search miss: add new Node
    }

    @Override
    public ValueType get(KeyType key) {
        // search for key, return associated value
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val; // search hit
            }
        }
        return null; // search miss
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
