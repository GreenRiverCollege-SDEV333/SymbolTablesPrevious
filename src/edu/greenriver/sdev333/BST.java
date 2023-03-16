package edu.greenriver.sdev333;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    //field
    private Node root;

    //helper class
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N; //number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;

        }
    }
    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root, key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private Node put(Node current, KeyType key, ValueType value) {
        if (current == null) {
            return new Node(key, value, 1);
        }
        int comp = key.compareTo(current.key);
        if (comp < 0) {
            current.left = put(current.left, key, value);
        }
        else if (comp > 0) {
            current.right = put(current.right, key, value);
        }
        else {
            current.val = value;
        }
        current.N = 1 + size(current.left) + size(current.right);
        return current;
    }

    @Override
    public KeyType min() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    @Override
    public KeyType max() {
        if (root == null) {
            return null;
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    @Override
    public KeyType floor(KeyType key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, KeyType key) {
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);
        if (comp == 0) {
            return x;
        }
        if (comp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        }
        else {
            return x;
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node ceiling(Node x, KeyType key) {
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);
        if (comp == 0) {
            return x;
        }
        if (comp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        }
        else {
            return x;
        }
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }

    private int rank(KeyType key, Node x) {
        if (x == null) {
            return 0;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return rank(key, x.left);
        }
        else if (comp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        }
        else {
            return size(x.left);
        }
    }

    private int size(Node left) {
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
