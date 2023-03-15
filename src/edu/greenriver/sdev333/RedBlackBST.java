package edu.greenriver.sdev333;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

/**
 * Red Black (Balanced) Binary Search Tree symbol table
 * Refer to p. 424-448 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class RedBlackBST <KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{ // BST node with color bit (see page 433)
        KeyType key;
        ValueType val;
        Node left, right;
        int n;
        boolean color;

        public Node(KeyType key, ValueType val, int i, boolean red) {
        }
    }

    private boolean isRed(Node x) {  // See page 433
        if (x == null) return false;
        return x.color == RED; //if it doesn't work change x to h
    }
    private Node rotateLeft(Node h) {    // See page 434
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {    // See page 434
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {     // See page 436
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


    @Override
    public void put(KeyType key, ValueType value) {
        // Search for key. Update value if found; grow table if new
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, KeyType key, ValueType val){
        if (h == null){      // Do standard insert, with red link to parent
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);

        if (cmp < 0){
            h.left = put(h.left, key, val);
        }
        else if(cmp > 0){
            h.right = put(h.right, key, val);
        }
        else{
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);
        }

        if (isRed(h.left) && !isRed(h.left.left)){
            h = rotateRight(h);
        }

        if (isRed(h.right) && !isRed(h.right)){
            flipColors(h);
        }

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    private int size(Node left) {

        return size();
    }

    @Override
    public ValueType get(KeyType key) {
        return null;
    }

    @Override
    public int size() { // See page 398
        return 0;
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
