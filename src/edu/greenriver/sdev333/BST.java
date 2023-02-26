package edu.greenriver.sdev333;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    // field
    private Node root;

    // helper class
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N;  // number of nodes in the subtree rooted here

        private Node(KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {

    }

    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    private ValueType get(Node current, KeyType key) {
        // author uses x instead of current

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

    /*
    // non-recursive version
    @Override
    public ValueType get(KeyType key) {
        // find the value for the given key

        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
                // pos - key.compareTo > current.key

            if (cmp < 0) {
                // go left
                current = current.left;
            }
            else if (cmp > 0) {
                // go right
                current = current.right;
            }
            else {
                return current.val;
            }
        }  // end while loop

        // key is not present in tree
        return null;
    } */

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
        return null;
    }
}
