package edu.greenriver.sdev333;

import com.sun.jdi.Value;

import java.security.Key;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    // private field
    private Node first;

    // private helper class
    private class Node {
        // linked list node
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        Node current = first;

        while ( current != null ) {
            if ( key.equals(current.key) ) {
                // update value if the key is found
                current.val.equals(value);
            }
            if ( !key.equals(current.key) ) {
                // increment iterator if not the key
                current = current.next;
            }
            // if no key is found, create a new Node pointing at "first"
            else {
                Node newNode = new Node((Key) key, (Value) value, first);
            }
        }
    }

    /**
     * Gets the value of the key passed as a parameter
     * @param key
     * @return the associated value of the given key
     */
    @Override
    public ValueType get(KeyType key) {

        Node current = first;

        while ( current != null ) {
            if ( key.equals(current.key) ) {
                return (ValueType) current.val;
            }
            else {
                current = current.next;
            }
        }
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
