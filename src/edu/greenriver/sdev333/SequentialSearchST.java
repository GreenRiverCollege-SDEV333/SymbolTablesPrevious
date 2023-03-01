package edu.greenriver.sdev333;

import java.util.Iterator;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */

public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private Node first;
    private int size = 0;

    private class Node {
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
    public void put(KeyType key, ValueType value) {
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public ValueType get(KeyType key) {
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> keys = new Queue<>();

        Node current = first;
        while(current != null) {
            keys.enqueue(current.key);
            current = current.next;
        }
        return keys;
    }
}
