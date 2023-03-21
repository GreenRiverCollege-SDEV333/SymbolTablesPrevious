package edu.greenriver.sdev333;

import java.util.NoSuchElementException;

/**
 * Red Black (Balanced) Binary Search Tree symbol table
 * Refer to p. 424-448 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class RedBlackBST <KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    // Instant field
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        KeyType key;
        ValueType val;
        Node left, right;
        int n;
        boolean color;

        Node(KeyType key, ValueType val, int n, boolean color){
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }
    private Node rotateLeft(Node current){
        Node newNode = current.right;
        current.right = newNode.left;
        newNode.left = current;
        newNode.color = current.color;
        current.color = RED;
        newNode.n = current.n;
        current.n = 1 + size(current.left) + size(current.right);
        return newNode;
    }

    private Node rotateRight(Node current){
        Node newNode = current.left;
        current.left = newNode.right;
        newNode.right = current;
        newNode.color = current.color;
        current.color = RED;
        newNode.n = current.n;
        current.n = 1 + size(current.left) + size(current.right);
        return newNode;
    }

    private boolean isRed(Node current){
        if (current == null){
            return false;
        }
        return current.color == RED;
    }

    public void flipColors(Node current){
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node current, KeyType key, ValueType val){
        if (current == null){
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0){
            current.left = put(current.left, key, val);
        } else if (cmp >0){
            current.right = put(current.right, key, val);
        } else{
            current.val = val;
        }

        if (isRed(current.right) && !isRed(current.left)){
            current = rotateLeft(current);
        }
        if (isRed(current.left) && isRed(current.left.left)){
            current = rotateRight(current);
        }
        if (isRed(current.left) && isRed(current.right)){
            flipColors(current);
        }

        current.n = size(current.left) + size(current.right) + 1;
        return  current;
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
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    @Override
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
