package edu.greenriver.sdev333;

import java.security.Key;
import java.util.LinkedList;
import java.util.PrimitiveIterator;
import java.util.Queue;

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

    private class Node{ //BST node with color bit
        KeyType key; //key
        ValueType val;
        Node left, right; //subtrees
        int n; //# nodes in this subtree
        boolean color; //color of link from parent to this node

        Node(KeyType key, ValueType val, int n, boolean color){
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if(x == null){
            return false;
        }
        return x.color == RED;
    }

    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);
        return x;
    }

    void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, KeyType key, ValueType val){
        if(x == null){
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = put(x.left, key, val);
        }else if(cmp > 0){
            x.right = put(x.right, key, val);
        }else{
            x.val = val;
        }

        if(isRed(x.right) && !isRed(x.left)){
            x = rotateLeft(x);
        }
        if(isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        }
        if(isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }

        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    private ValueType get(Node root, KeyType key) {
        if(root == null){
            return null;
        }
        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            return get(root.left, key);
        }else if (cmp > 0){
            return get(root.right, key);
        }else{
            return root.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        if(x == null){
            return 0;
        }
        return x.n;
    }

    @Override
    public KeyType min() {
        if(root == null){
            return null;
        }

        Node x = root;
        while (x.left != null){
            x = x.left;
        }
        return x.key;
    }

    @Override
    public KeyType max() {
        if(root == null){
            return null;
        }

        Node x = root;
        while (x.right != null){
            x = x.right;
        }
        return x.key;
    }

    @Override
    public KeyType floor(KeyType key) {
        if(key == null){
            throw new IllegalArgumentException("Key can not be null.");
        }

        if(isEmpty()){
            return null;
        }

        Node x = floor(root, key);
        if(x == null){
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, KeyType key) {
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp == 0){
            return x;
        }
        if(cmp < 0){
            return floor(x.left, key);
        }
        Node h = floor(x.right, key);
        if(h != null){
            return h;
        }
        return x;
    }


    @Override
    public KeyType ceiling(KeyType key) {
        return null;
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }

    private int rank(KeyType key, Node x) {
        if(x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return rank(key, x.left);
        }else if(cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }else{
            return size(x.left);
        }
    }

    @Override
    public KeyType select(int k) {
        if(k < 0 || k >= size()){
            throw new IllegalArgumentException("Rank is out of bounds.");
        }
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k){
        int t = size(x.left);
        if(t > k){
            return select(x.left, k);
        }else if(t < k){
            return select(x.right, k - t - 1);
        }else{
            return x;
        }
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new LinkedList<>();
        inorder(root,  queue);
        return queue;
    }

    private void inorder(Node x, Queue<KeyType> queue){
        if(x == null){
            return;
        }
        inorder(x.left, queue);
        queue.add(x.key);
        inorder(x.right, queue);
    }
}
