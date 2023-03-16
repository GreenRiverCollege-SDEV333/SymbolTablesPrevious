package edu.greenriver.sdev333;
import java.util.ArrayList;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private Node first; //first node in the linked list
    int size;

    private class Node {
        //linked list Node
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
    public void put(KeyType key, ValueType val) {
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key)) {
                //search hit: update val
                x.val = val; return;
            }
        first = new Node(key, val, first); //search miss: add new node
        size++; //increment size

    }

    @Override
    public ValueType get(KeyType key) {
        //Search for key, return associated value
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                return x.val; //search hit
            }
        return null; //search miss
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        ArrayList<KeyType> list = new ArrayList<>();
        for(Node x = first; x != null; x = x.next) {
            list.add(x.key);
        }
        return list;
    }
}
