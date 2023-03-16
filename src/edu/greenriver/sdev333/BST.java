package edu.greenriver.sdev333;

import com.sun.jdi.Value;

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
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        //number of nodes in the subtree rooted here
        private int N;

        public Node(KeyType key, ValueType value, int N) {
            this.key = key;
            this.val = value;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        // starts the recursion
        root = put(root, key, value);
    }

    // helper method for put for recursion
    private Node put(Node current, KeyType key, ValueType val){
        // current is the root of the subtree we are looking at

        // we are at where we are supposed to be - current is null
        if(current == null){
            // create a new node and return it
            return new Node(key, val, 1);
        }

        // compare value of root (current) to the value of what we want to insert
        int cmp = key.compareTo(current.key);

            // cmp will be -1 (negative) if key < current.key
            // cmp will be 0 (zero) if key == current.key
            // cmp will be +1 (positive) if key > current.key
        // go left
        if (cmp < 0){
           current.left = put(current.left, key, val);
        }

        // go right
        else if (cmp > 0){
            current.right = put(current.right, key, val);
        }
        // key already exists, replace the data (val)
        else {
            current.val = val;

        }
        // update the nodes N - number of nodes in the subtree
        //            size of left + size of right + myself
        current.N = size(current.left) + size(current.right) + 1;

        return current;

    }

    /**
     * non-recursive version of get
     * @param key
     * @return
     */
    @Override
//    public ValueType get(KeyType key) {
//        // someone gives me a key, I want to find the value that goes with that key
//        // start at root
//        Node current = root;
//        while (current != null) {
//            int cmp = key.compareTo(current.key);
//            // compareTo returns neg, zero, pos number
//            // if key is less than current key; return negative number
//            // if key is equal to current key; returns 0
//            // if key is more than current key; returns positive number
//
//            if (cmp < 0) {
//                current = current.left; // go to left of root
//
//            }
//            else if (cmp > 0) {
//                current = current.right; // go to right of root
//
//            }
//            else {
//                return current.value; // stay right there
//
//            }
//        } // end of while loop
//        return null;
//
//
//    }

    /**
     * recursive method of code above
     */
    public ValueType get(KeyType key) {
        // root will be the first current, then work from there
        return get(root, key);



    }

    // helper method
    private ValueType get (Node current, KeyType key) {
        // author uses x instead of current
        if (current == null) {
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
//        if (root == null ) {
//            return 0;
//        }
//        else {
//            return root.N;
//        }
        // call helper method and return at the root
        return size(root);
    }
    // helper method
    private int size(Node current) {
        if (current == null ) {
            return 0;
        }
        else {
            return current.N;
        }

    }


    @Override
    public KeyType min() {
        return min(root).key;
    }
    // helper method
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public KeyType max() {
        return max(root).key;
    }
    // helper method
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node x = floor (root, key);
        if (x == null) return null;
        return x.key;
    }
    // helper method
    private Node floor(Node x, KeyType key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling (root, key);
        if (x == null) return null;
        return x.key;
    }
    // helper method
    private Node ceiling(Node x, KeyType key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }
    // helper method
    private int rank(KeyType key, Node x)
    {  // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }


    @Override
    public KeyType select(int k) {
        return select(root, k).key;
    }
    // helper method
    private Node select(Node x, int k)
    {   // Return Node containing key of rank k.
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left,  k);
        else if (t < k) return select(x.right, k-t-1);
        else            return x;
    }

    // return key in order
    @Override
    public Iterable<KeyType> keys() {
        // create a new empty queue to hold my results
        Queue<KeyType> queue = new Queue<>();

        // start the recursion, collecting results in the queue
        inorder(root, queue);

        // when done, return the queue
        return queue;
    }

    // helper method for keys
    private void inorder(Node current, Queue<KeyType> q) {
        if (current == null) {
            // do nothing, end the method immediately
            return;

        }
        inorder(current.left, q); // prints left subtree
        q.enqueue(current.key); // prints myself
        inorder(current.right, q); // prints right subtree

    }
}
