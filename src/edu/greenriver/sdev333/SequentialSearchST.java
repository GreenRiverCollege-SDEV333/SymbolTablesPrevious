package edu.greenriver.sdev333;

import com.sun.jdi.Value;
import org.w3c.dom.Node;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private Node first;
    int size;

    private class Node {
        KeyType key;
        ValueType val;
        Node next;

        //constructor to implement
        public Node(KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    /**
     * This method puts the kay/value pair into our tree
     * @param key
     * @param val
     */
    @Override
    public void put(KeyType key, ValueType val) {
        //need to iterate through the linked list to look for our value
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.val = val;
                return;
            }
        }

        //if the value is not found in the list, create a new node and assign it the value
        first = new Node(key, val, first);
        //increment size
        size++;
    }

    /**
     * This method gets a given key/value
     * @param key
     * @return
     */
    @Override
    public ValueType get(KeyType key) {
        // iterate through our linked list looking for matching key
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.val;
            }
        }
        // if there is no matching key found then return null
        return null;
    }

    /**
     * method returns the size of the tree accounting for how many nodes there are
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * this method will return a ordered list of keys
     * @return
     */
    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();

        Node current = first;
        while (current != null) {
            queue.enqueue(current.key);
            current = current.next;
        }

        return queue;
    }
}
