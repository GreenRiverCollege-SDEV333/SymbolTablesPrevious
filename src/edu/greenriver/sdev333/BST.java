package edu.greenriver.sdev333;

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


