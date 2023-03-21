package edu.greenriver.sdev333;

import java.security.Key;
import java.util.NoSuchElementException;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

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
    public void put(KeyType key, ValueType val) {
        root = put(root, key, val);
    }

    public Node put(Node x, KeyType key, ValueType val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public ValueType get(KeyType key) {
        // someone gives me a key, I want to find the value that goes with that key
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
                // compareTo returns neg, zero, pos
            if (cmp < 0) {
                current = current.left;
            }
            else if (cmp > 0) {
                current = current.right;
            }
            else {
                return current.val;
            }
        }
        return null;
    }

    private ValueType get(Node current, KeyType key) {
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
        return size(root);
    }

    private int size(Node current) {
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
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public KeyType max() {
        if (isEmpty()) throw new NoSuchElementException();
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }

    private Node floor(Node x, KeyType key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }

    private Node ceiling(Node x, KeyType key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }

    private int rank(KeyType key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    @Override
    public KeyType select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        Node x = select(root, k);
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    @Override
    public Iterable<KeyType> keys() {
        return keys(min(), max());
    }

    public Iterable<KeyType> keys(KeyType lo, KeyType hi) {
        Queue<KeyType> queue = new Queue<KeyType>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<KeyType> queue, KeyType lo, KeyType hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
