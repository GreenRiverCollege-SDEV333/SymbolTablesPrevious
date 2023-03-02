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

    // helper class
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N; // number of nodes in the subtree here

        public Node(KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /*
        method that starts the recursion of our put helper method
     */
    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root, key, value);
    }


    /**
     * This is a helper method that puts in a value and a key
     * @param current our subtree root
     * @param key the key we want to insert
     * @param value the value we are give our node
     * @return
     */
    //Node current is essentially our subtree root
    private Node put(Node current, KeyType key, ValueType value){
        //cases to consider, if we need to go left, go right, or are where we need to be
        //we know where were supposed to be when we reach the bottom of the tree which means our next is null

        if (current == null) {
            //make a new node when we find our place that has a key, a value, and the count is 1 because we are at the bottom
           return new Node(key, value, 1);
       }

        // compare the value we want to insert to whats currently at the root
        int cmp = key.compareTo(current.key);
        //cmp will be -1 (negative) if the key < current.key
        //cmp will be 0 (zero) if the key == current.key
        //cmp will be +1 (positive) if the key > current.key

        //go left in the subtree and put the value in there
        if (cmp < 0) {
               current.left = put(current.left, key, value);
           }
        //go right
        else if (cmp > 0) {
               current.right = put(current.right, key, value);
           }
        //don't need to move, the key already exists so we replace the data
        else {
               current.val = value;

           }

        //Next we update N
        // N is the size of the subtree I am in
        //size of the left size of the right and plus one to count yourself
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    //get method using a loop
    /*  @Override
    public ValueType get(KeyType key) {
        // if someone gives me a key, I want to find the value that goes with that key
        Node current = root; //we start at the root, in a linkedList we would start at the head, in a map we refer to the beginning as the root
        while (current != null) { // we reach the end of the tree if the left is null
            int cmp = key.compareTo(current.key);
            //compareTo method returns neg, zero, positive based on the key were looking for

            //go left
            if (cmp < 0) {
                current = current.left;
            }
            //go right
            else if (cmp >0) {
                current = current.right;
            }
            //then if its equal, this is the value we want
            else {
                return current.val;
            }
        } // end of while loop

        // when the key is not found return null
        return null;
    }*/

    /**
     * if someone gives me a key, I want to find the value that goes with that key
     * @param key
     * @return
     */
    // get method using recursion
    //method that main calls
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    //recursive helper method
    private ValueType get(Node current, KeyType key){
        //author uses x instead of current in the book
        if (current == null) {
            return null;
        }
        int cmp = key.compareTo(current.key);

        if (cmp < 0) {
            return get(current.left, key);
        } else if (cmp > 0) {
            return get(current.right, key);
        } else {
            return current.val;
        }
    }

    @Override
    public int size() {
       // this way works as well without using the helper method
        /* if (root == null) { //if the root is null
            return 0; //size will be zero
        }
        else {
            return root.N;
        }*/
        return size(root);
    }

    // private helper method, we are getting the size at a particular root
    // looks at subtree of where current is.
    // for example, if current is blank then its size is what ever proceeds after
    private int size(Node current) {
        if (current == null) {
            return 0;
        } else {
            return current.N;
        }
    }

    @Override
    public KeyType min() {
        if (isEmpty()) throw new NoSuchElementException();
        //Node current = min(root);
        //return current.key;
        return null;
    }

    @Override
    public KeyType max() {
        return null;
    }

    @Override
    public KeyType floor(KeyType key) {
        /*Node current = floor(root, key);
        if (current == null){
            throw new NoSuchElementException();
        }
        return current.key;*/
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

    /**
     * Method returns a collection of keys in order
     * @return
     */
    @Override
    public Iterable<KeyType> keys() {
        //create a new empty queue to hold my results
        Queue<KeyType> queue = new Queue<>();

        //start the recursion, collection the results in the queue
        inorder(root,queue);

        //when done, return queue
        return queue;
    }

    /**
     * Helper method for our keys method
     */
    public void inorder(Node current, Queue<KeyType> q){
        if (current == null) {
            //if current = null do nothing
            return;
        }
        //prints the left subtree
        inorder(current.left, q);

        //add myself if to the queue
        q.enqueue(current.key);

        //prints the right subtree
        inorder(current.right, q);
    }
}
