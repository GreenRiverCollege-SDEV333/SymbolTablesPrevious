package edu.greenriver.sdev333;
import edu.greenriver.sdev333.OrderedSymbolTable;
import edu.greenriver.sdev333.BST;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    // fields
    private Node root;

    // helper class
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N; // number of nodes in the subtree rooted here

        public Node (KeyType key, ValueType value, int N) {
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

    //
    private Node put(Node current, KeyType key, ValueType val) {
        // current is the root of the subtree we are looking at

        // we are at where we are supposed to be
        if (current == null) {
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(current.key);
            // cmp will be -1 (negative) if key < current.key
            // cmp will be 0 (zero) if key == current.key
            // cmp will be +1 (positive) if key > current.key

        // go left
        if (cmp < 0) {
            current.left = put(current.left, key, val);
            // go right
        } else if (cmp > 0) {
            current.right = put(current.right, key, val);
        } else {
            // key already exists
            current.val = val;
        }

        // update the node's N - number of nodes in the subtree
        current.N = size(current.left) + size(current.right) + 1;

        return current;
    }

    @Override
    /*public ValueType get(KeyType key) {
        // someone gives me a key, I want to find the value
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }// end of while loop
        // nothing was found
        return null;
    }*/

    public ValueType get(KeyType key) {
        return get(root,key);
    }

    private ValueType get(Node current, KeyType key) {
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
        return size(root);
    }

    private int size(Node current) {
        if (current == null) {
            return 0;
        } else {
            return current.N;
        }
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
        inorder(root,queue);

        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q) {
        if (current == null) {
            // do nothing - intentionally blank
            return;
        }
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }
}
