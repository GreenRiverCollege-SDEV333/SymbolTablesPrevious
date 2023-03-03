package edu.greenriver.sdev333;

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
        // number of nodes in the subtree rooted here
        private int N;

        public Node (KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        //starts the recursion
        root = put(root, key, value);
    }

    //helper method for put for recursion
    private Node put(Node current, KeyType key, ValueType val) {
        //current is the roof of the subtree that we are looking at

        //we are where we are supposed to be
        if(current == null) {
            //create new node
            return new Node(key,val,1);
        }

        int cmp = key.compareTo(current.key);
            //cmp will be -1 if key < current.key
            //cmp will be 0 if key == current.key
            //cmp will be +1 of key is > current.key
        //go left
        if(cmp < 0) {
            current.left = put(current.left, key, val);
        }
        //go right
        else if(cmp > 0) {
            current.right = put(current.right, key, val);
        }
        else {
            //key already exists
            current.val = val;
        }
        //update the Node's N - number of Nodes
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }



    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    private ValueType get(Node current, KeyType key) {
        if(current == null) {
            return null;
        }
        int cmp = key.compareTo(current.key);

        if(cmp < 0) {
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
        if(root == null) {
            return 0;
        }
        else {
            return root.N;
        }
    }

    private int size(Node current) {
        if (current == null) {
            return 0;
        }
        else {
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
