package edu.greenriver.sdev333;

import java.util.NoSuchElementException;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    private Node root;

    private class Node{
        private KeyType key;
        private ValueType val;
        private Node left, right;
        private int n;

        public Node (KeyType key, ValueType val, int n){
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }
    @Override
    public void put(KeyType key, ValueType val) {
        root = put(root, key, val);
    }

    // helper method for put for recursion
    private Node put(Node x, KeyType key, ValueType val){
        // current is the root of the subtree we are looking at


        if(x == null){
           return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        // go left
        if(cmp < 0) x.left = put(x.left, key, val);

        //go right
        else if (cmp >  0) x.right = put(x.right, key, val);

        //we have arrived
        else x.val = val;
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    private ValueType get(Node x, KeyType key){
        if(x == null){return null;}
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if(x == null){ return 0;}
        else{return x.n;}
    }
    @Override
    public KeyType min() {
        if(size() == 0) throw new NoSuchElementException();
        //if(size() == 0) return null;
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public KeyType max() {
        if(size() == 0) throw new NoSuchElementException();
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x){
        if (x.right == null) return x;
        return min(x.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }

    private Node floor(Node x, KeyType key){
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if(t != null) {
            return t;
        }else{
            return x;
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }

    private Node ceiling(Node x, KeyType key){
        if(x==null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if(t != null) {
            return t;
        }else{
            return x;
        }
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }

    private int rank(KeyType key, Node x){
        // Return number of keys less than key in the subtree rooted at x.
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            return rank(key, x.left);
        }else if (cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }else{
            return size(x.left);
        }
    }

    @Override
    public KeyType select(int k) {
        if(k<0 || k>=size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k){
        // Return Node containing key of rank k.
        if(x==null) return null;
        int t = size(x.left);
        if(t>k){
            return select(x.left, k);
        }else if(t<k){
            return select(x.right, k-t-1);
        }else{
            return x;
        }
    }

    @Override
    public Iterable<KeyType> keys() {

        Queue<KeyType> queue = new Queue<>();

        //start the recursion, collecting results in the queue
        inorder(root, queue);

        // when done return the queue
        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q){
        if(current == null){
            // do nothing - intentionally blank
            return;
        }
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }

    public boolean contains(KeyType key){
        return get(key) != null;
    }


}
