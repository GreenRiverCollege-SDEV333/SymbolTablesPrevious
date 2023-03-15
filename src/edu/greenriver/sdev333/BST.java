package edu.greenriver.sdev333;

import java.util.NoSuchElementException;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    // field
    private Node root;

    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N; // number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        // starts the recursion
        root = put(root, key, value);
    }

    public Node put(Node current, KeyType key, ValueType val) {
        // current is the root of the subtree we are looking at

        // we are at where we are supposed to be
        if (current == null) {
            return new Node(key, val,1);
        }

        int cmp = key.compareTo(current.key);
        // cmp will be -1 (negative) if key < current.key
        // cmp will be 0 (zero) if key == current.key
        // cmp will be +1 (positive) if key > current.key

        if (cmp < 0) {
            // go left
            current.left = put(current.left,key,val);
        }
        else if (cmp > 0) {
            // go right
            current.right = put(current.right,key,val);
        }
        else {
            // key already exists, replace the data(val)
            current.val = val;
        }

        // update the node's N - number of nodes in the subtree
        current.N = size(current.left) + size(current.right) + 1;

        return current;
    }


//    @Override
//    public ValueType get(KeyType key) {
//        // someone gives me a key, I want to find the value that goes with that key
//        Node current = root;
//        while (current != null) {
//            int cmp = key.compareTo(current.key);
//                // compareTo returns neg, zero, pos
//
//            if (cmp < 0) {
//               current = current.left;
//            }
//            else if (cmp > 0) {
//                current = current.right;
//            }
//            else {
//                return  current.val;
//            }
//        } // end of while loop
//
//        return null;
//    }

    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    // recursive version
    // helper method
    private ValueType get(Node current, KeyType key) {
        // author uses x instead of current
        if(current ==  null) {
            return null;
        }

        int cmp = key.compareTo(current.key);

        if (cmp < 0) {
            return get(current.left, key);
        }
        else if (cmp > 0) {
            return get(current.right, key);
        }
        else {
            return current.val;
        }
    }

    @Override
    public int size() {
//        if (root == null) {
//            return 0;
//        }
//        else {
//            return root.N;
//        }
        return size(root);
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = min(root);
        return current.key;
    }
    private Node min(Node current) {
        if (current.left == null) {
            return current;
        }
        return min(current.left);
    }

    @Override
    public KeyType max() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = max(root);
        return current.key;
    }
    public Node max(Node current) {
        if (current.right == null) {
            return current;
        }
        return max(current.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node current = floor(root, key);
        if (current == null) {
            throw new NoSuchElementException();
        }
        return current.key;
    }
    private Node floor(Node current, KeyType key) {
        if (current == null)  {
            return null;
        }

        int cmp = key.compareTo(current.key);

        if (cmp == 0) {
            return current;
        }
        if (cmp < 0) {
            return floor(current.left, key);
        }

        Node t = floor(current.right, key);

        if (t != null) {
            return t;
        }
        else {
            return current;
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node current = ceiling(root, key);
        if (current == null) {
            throw new NoSuchElementException();
        }
        return current.key;
    }
    private Node ceiling(Node current, KeyType key) {
        if (current == null)  {
            return null;
        }

        int cmp = key.compareTo(current.key);

        if (cmp == 0) {
            return current;
        }
        if (cmp < 0) {
            return floor(current.right, key);
        }

        Node t = floor(current.left, key);

        if (t != null) {
            return t;
        }
        else {
            return current;
        }
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }
    private int rank(KeyType key, Node currrent) {
        if (currrent == null) {
            return 0;
        }

        int cmp = key.compareTo(currrent.key);
        if (cmp < 0) {
            return rank(key, currrent.left);
        }
        else if (cmp > 0) {
            return 1 + size(currrent.left) + rank(key, currrent.right);
        }
        else {
            return size(currrent.left);
        }
    }

    @Override
    public KeyType select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }

        Node current = select(root, k);
        return current.key;
    }
    private Node select(Node current, int k) {
        if (current == null) {
            return null;
        }

        int t = size(current.left);
        if (t > k) {
            return select(current.left, k);
        }
        else if (t < k) {
            return select(current.right, k-t-1);
        }
        else {
            return current;
        }

    }

    @Override
    public Iterable<KeyType> keys() {
        // create a new empty queue to hold my results
        Queue<KeyType> queue = new Queue<>();

        // start the recursion, collecting results in the queue
        inorder(root,queue);

        // when done, return the queue
        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q) {
        if (current == null) {
            // do nothing - end the method immediately
            return;

        }

        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right,q);
    }
}
