package edu.greenriver.sdev333;


import java.security.Key;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private Node first; // first node in the linked list

    private class Node {
        // linked list node
        KeyType key;
        ValueType val;
        Node next;

        public Node (KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    @Override
    public void put(KeyType key, ValueType val) {
        // search for key, update value if found; grow table if new
        for (Node current = first; current != null; current = current.next) {
            if (keys().equals(current.key)) {
                current.val = val; // update val, search hit
                return;
            }
        }
        first = new Node(key,val,first); // add new node , search miss
    }

    @Override
    public ValueType get(KeyType key) {
        // search for key, return associated value
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        if(first == null) {
            return 0;

        }
        return size();
    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
