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

    private Node head; // first node in the linked list
    private int size; // size variable for tracking size

    /**
     * helper node class
     * variables for key type
     * value type
     * and Node pointing to next
     */
    private class Node {
        KeyType key;
        ValueType val;
        Node next;

        /**
         * Node class used in lunkedlist symbol table implentation
         * @param key
         * @param val
         * @param next
         */
        public Node(KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    /**
     * put method takes in key and value parameters
     * then a for loop creates a tracker node called current, and runs till current equals null, then current points to current.next
     * if the key of currennt, equals the key passed in, the calue of current is changed to the value passsed in, other wise a new node is added to
     * the head variable with the parameters passed in.
     * @param key
     * @param value
     */
    @Override
    public void put(KeyType key, ValueType value) {
        for (Node current = head; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.val = value;
                return;
            }
        }
            head = new Node(key, value, head);

    }

    /**
     * this method takes a given key parameter and returns the value for that key. similiar to the put method, we run a forI loop that creates a current node, runs until current equals null then sets current to current.next
     * then it checks to see if the key in current is the same as the paramter of key passed in, and if it is, it returns the current value, otherwise the key does not exist in the table and null is received
     * @param key
     * @return
     */
    @Override
    public ValueType get(KeyType key) {
        for (Node current = head; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.val;
            }
        }
        return null;
    }

    /**
     * this size method runs a foreach loop on each key in the symbol table
     * and increments the size counter for each entry
     * @return
     */
    public int size() {
        for (KeyType key : this.keys()) {
            size++;
        }
        return size;
    }

    /**
     * this is an interator class that uses helper class SSIterator to return all keys
     * for use in foreach loops
     * @return SSIterator
     */
    public Iterable<KeyType> keys() {
        return new Iterable<KeyType>() {
            @Override
            public Iterator<KeyType> iterator() {
                return new SSIterator(head);
            }
        };
    }


    /**
     * helper iterator class used in for each loops
     */
    public class SSIterator implements Iterator<KeyType> {

        private Node current;

        public SSIterator(Node head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public KeyType next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            KeyType key = current.key;
            current = current.next;
            return key;
        }
    }


}


