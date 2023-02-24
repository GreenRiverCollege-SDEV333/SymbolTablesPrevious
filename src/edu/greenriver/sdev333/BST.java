package edu.greenriver.sdev333;

import org.w3c.dom.Node;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    //fields
    private Node root;
    //helper class
    private class Node{
        private KeyType key;
        private ValueType value;
        private Node left;
        private Node right;
        //number of nodes in the subtree rooted here
        private int N;

        public Node(KeyType key, ValueType value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {

    }

    @Override
//    public ValueType get(KeyType key) {
//        // someone gives me a key, I want to find the value that goes with
//        // that key
//        Node current = root;
//        while ( current != null ) {
//            int cmp = key.compareTo(current.key);
//            // compareTo returns neg, zero, pos
//
//            if (cmp < 0) {
//                current = current.left;
//            }
//            else if (cmp > 0) {
//                current = current.right;
//            }
//            else {
//                return current.value;
//            }
//        } // end of while loop
//
//        return null;
//    }

    public ValueType get(KeyType key) {
        return get(root, key);
    }

    private ValueType get(Node current, KeyType key){
        // author uses x instead of current
        if ( current == null ) {
            return  null;
        }
        int cmp = key.compareTo(current.key);

        if (cmp < 0) {
            return get(current.left, key);
        }
        else if (cmp > 0) {
            return get(current.right, key);
        }
        else {
            return current.value;
        }
    }

    @Override
    public int size() {
        return size(root);
//        if ( root == null ) {
//            return 0;
//        }
//        else {
//            return root.N;
//        }
    }

    private int size(Node current) {
        if (current == null) {
            return 0;
        }
        else {
            return current.N;
        }
    }

    @Override
    public KeyType min() {
        return null;
    }

    @Override
    public KeyType max() {
        return null;
    }

    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        return null;
    }

    @Override
    public int rank(KeyType key) {
        return 0;
    }

    @Override
    public KeyType select(int k) {
        return null;
    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
