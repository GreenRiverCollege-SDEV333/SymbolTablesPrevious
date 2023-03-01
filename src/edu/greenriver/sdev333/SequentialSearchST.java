package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private Node first; // first node in the linked list

    private class Node {
        KeyType key;
        ValueType val;
        Node next;

        public Node(KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    @Override
    public void put(KeyType key, ValueType value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = value;
                return;
            }
            first = new Node(key, value, first);
        }
    }

    @Override
    public ValueType get(KeyType key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
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


