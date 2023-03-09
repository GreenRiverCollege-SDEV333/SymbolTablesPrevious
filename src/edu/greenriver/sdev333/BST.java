package edu.greenriver.sdev333;
import edu.greenriver.sdev333.OrderedSymbolTable;
import edu.greenriver.sdev333.BST;

import java.util.NoSuchElementException;

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
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }

        return current.key;
    }

    @Override
    public KeyType max() {
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
            throw new NoSuchElementException();
        }
        return x.key;
    }

    private Node floor (Node x, KeyType key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    private Node ceiling (Node x, KeyType key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return floor(x.right, key);
        }
        Node t = floor(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling(root, key);
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x.key;
    }

    private int rank(KeyType key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) +rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    @Override
    public int rank(KeyType key) {
        return rank(key, root);
    }

    private Node select (Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k-t-1);
        } else {
            return x;
        }
    }

    @Override
    public KeyType select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        Node x = select(root, k);
        return x.key;
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
