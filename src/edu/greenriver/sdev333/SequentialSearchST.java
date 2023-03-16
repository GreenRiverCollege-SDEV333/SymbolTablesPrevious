package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    // fields
    private Node first;
    private int size = 0;

    // helper class
    private class Node{
        KeyType key;
        ValueType val;
        Node next;

        // constructor:
        public Node (KeyType key, ValueType val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }


    /**
     * method that puts key value pair into Symbol table
     *
     * @param key - key; where to insert node in symbol table
     * @param value - value of node
     */
    @Override
    public void put(KeyType key, ValueType value) {
        // Search for key. Update Value if found; grow table.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.val = value;
                return;
            }
        first = new Node(key, value, first);
        size++;

    }

    /**
     * method that returns value of specified key - search for the key
     * and return associated value
     *
     * @param key
     * @return value of key
     */
    @Override
    public ValueType get(KeyType key) {
        // Search for key, return associated value
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
    }

    /**
     * method that returns the size of symbol table - if first == null, reutrn
     * size = 0; otherwise return size of symbol table
     *
     * @return size of symbol table
     */
    @Override
    public int size() {
        if (first == null ) {
            return 0;
        }
        else {
            return size();
        }
    }

    /**
     * Iterable method that returns keys in order
     *
     * @return symbol table keys in order
     */
    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();
        Node current = first;
        for(Node x = first; x != null; x = x.next){
            queue.enqueue(current.key);
            current = current.next;
        }
        return queue;
    }
}
