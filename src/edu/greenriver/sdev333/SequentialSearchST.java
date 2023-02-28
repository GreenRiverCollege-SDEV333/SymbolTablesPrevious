package edu.greenriver.sdev333;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */

//The class SequentialSearchST is defined with two generic type parameters, KeyType and ValueType,
// which represent the types of keys and values that can be stored in the symbol table.
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private Node first;
    //The Node class is a private nested class that represents each element of the linked list.
    // Each Node object contains a key-value pair and a reference to the next node in the list.
    private class Node{
        KeyType key;
        ValueType value;
        Node next;

        public Node(KeyType key, ValueType value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    //The put method takes a key-value pair and inserts it into the linked list.
    // It does this by iterating through the list, starting at the first node, and comparing each node's key to the key provided as an argument.
    // If the keys match, the method updates the node's value and returns. Otherwise,
    // the method inserts a new node containing the key-value pair at the beginning of the list.
    @Override
    public void put(KeyType key, ValueType value) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
            {
                x.value = value;
                return;
            }
        first = new Node(key, value, first);
    }

    //The get method takes a key and searches for the corresponding value in the linked list.
    // It does this by iterating through the list, starting at the first node, and comparing each node's key to the key provided as an argument.
    // If the keys match, the method returns the corresponding value. If no node with a matching key is found, the method returns null.
    @Override
    public ValueType get(KeyType key) {
        for (Node x = first; x != null; x = x.next)
            if( key.equals(x.key))
                return  x.value;
        return null;
    }
    //The size method returns the number of nodes in the linked list.
    // Since the implementation uses an unordered linked list, there is no efficient way
    // to count the number of elements without iterating through the entire list, so this method always returns 0.

    @Override
    public int size() {
        return 0;
    }

    //The keys method returns an iterable object containing all the keys in the symbol table.
    // Since the implementation uses an unordered linked list, there is no efficient way to iterate through the
    // keys without iterating through the entire list, so this method returns null.
    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
