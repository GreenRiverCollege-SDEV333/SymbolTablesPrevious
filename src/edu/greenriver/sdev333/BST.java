package edu.greenriver.sdev333;


import java.util.NoSuchElementException;

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
        private ValueType val;
        private Node left;
        private Node right;
        private int N; // number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root,key, value);
    }

    private Node put (Node current, KeyType key, ValueType val){
        // current is the root of the subtree wer are looking at

        //we are at where we are supposed to be
        if (current == null){
            //create new node
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(current.key);
        //go left
        if (cmp < 0){
            current.left = put(current.left, key, val);
        }
        //go right
        else if (cmp > 0){
            current.right = put(current.right, key, val);
        } else {
            // key is already exists, replace the data (val)
            current.val = val;
        }
        // update the node's N - number of nodes in the subtree
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    /*
    @Override
    public ValueType get(KeyType key) {
        // someone gives me a key, I want to find the value that goes with that key
        Node current = root;
        while(current != null){
            int cmp = key.compareTo(current.key);
                //compareTo returns neg, zero, pos
            if (cmp < 0){
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }//end of while loop
        return null;
    }
    */


    public boolean contains(KeyType key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                // We found an exact match for the key, so return true
                return true;
            } else if (cmp < 0) {
                // The key is less than the current node's key, so move left
                current = current.left;
            } else {
                // The key is greater than the current node's key, so move right
                current = current.right;
            }
        }

        // If we get here, we didn't find a matching key in the tree
        return false;
    }


    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    private ValueType get(Node current, KeyType key){
        if (current == null){
            return null;
        }

        int cmp = key.compareTo(current.key);

        if (cmp < 0){
            return get(current.left, key);
        } else if (cmp > 0) {
            return get(current.right, key);
        } else {
            return current.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node current){
        if (current == null){
            return 0;
        }
        else {
            return current.N;
        }
    }

    @Override
    public KeyType min() {
        if (isEmpty()) throw new NoSuchElementException();
        Node current = min(root);
        return  current.key;
    }

    //helper method
    private Node min(Node current){
        if (current.left == null){
            return current;
        }
        return min(current.left);
    }

    @Override
    public KeyType max() {
        if (isEmpty()) throw new NoSuchElementException();
        Node current = max(root);
        return  current.key;
    }

    //helper method
    private Node max(Node current){
        if (current.right == null){
            return current;
        }
        return max(current.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node current = floor(root, key);
        if (current == null) throw new NoSuchElementException();
        return current.key;
    }

    // helper method
    private Node floor(Node current, KeyType key){
        if(current == null){
            return null;
        }
        int cmp = key.compareTo(current.key);
        if (cmp == 0){
            return current;
        }
        if (cmp < 0){
            return floor(current.left, key);
        }
        Node t = floor(current.right, key);
        if (t != null){
            return t;
        }else{
            return current;
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node current = root;
        KeyType result = null;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                // We found an exact match for the key, so return it
                return current.key;
            } else if (cmp < 0) {
                // The key is less than the current node's key, so move left
                result = current.key;
                current = current.left;
            } else {
                // The key is greater than the current node's key, so move right
                current = current.right;
            }
        }

        return result;
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }

    // helper method
    private int rank (KeyType key, Node current){
        // Return number of keys less than key in the subtree rooted at current
        if (current == null){
            return 0;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0){
            return rank(key, current.left);
        } else if (cmp > 0) {
            return 1 + size(current.left) + rank(key, current.right);
        } else {
            return size(current.left);
        }
    }

    @Override
    public KeyType select(int k) {
        if ((k < 0) || k >= size()) throw new IllegalArgumentException();
        Node current = select(root, k);
        return current.key;
    }

    // helper method
    private Node select(Node current, int k){
        //return key containing rank k
        if (current == null){
            return null;
        }
        int t  = size(current.left);
        if (t > k){
            return select(current.left, k);
        } else if (t < k){
            return select(current.right, k-t-1);
        } else {
            return current;
        }
    }

    @Override
    public Iterable<KeyType> keys() {
        //create a new empty queue to hold my results
        Queue<KeyType> queue = new Queue<KeyType>();

        //start the recursion, collecting results in the queue
        inorder(root, queue);

        //when done, return the queue
        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q){
        if (current == null){
            //do nothing - intentional
            return;
        }

        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }
}
