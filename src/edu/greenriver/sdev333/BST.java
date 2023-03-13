package edu.greenriver.sdev333;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition,
 * pages 398-399
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

    /**
     * This method adds a key/value pair to the tree, after first ascertaining its
     * correct location.  It calls the private method put(Node,KeyType,ValueType)
     * which performs this action recursively.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(KeyType key, ValueType value) {
        // in case the tree is empty, having = allows us to save/start the tree
        root = put(root, key, value);
    }

    // helper method for put, uses recursion
    private Node put(Node current, KeyType key, ValueType val) {
        // current is the root of the subtree we are looking at

        // we are where we are supposed to be
        if (current == null) {
            // create a new node
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(current.key);
        // cmp will be < 0 if key < current
        // cmp = 0 if key == current
        // cmp > 0 if key > current

        if (cmp < 0) {
            // go left
            current.left = put(current.left, key, val);
        } else if (cmp > 0) {
            // go right
            current.right = put(current.right, key, val);
        } else {
            // key already exists, replace the data (val)zs
            current.val = val;
        }

        // update the node's N, number of nodes in subtree
        // size of left + size of right + self
        current.N = size(current.left) + size(current.right) + 1;

        return current;
    }

    /**
     * Returns the object for key, calls the helper method
     * get(Node,KeyType), which works recursively to get the
     * given KeyType.
     *
     * @param key
     * @return
     */
    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }

    /*
     * recursive version of get() method
     */
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

    /**
     * Returns the size of the tree, calls the helper method size(Node)
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    // helper method for public int size()
    private int size(Node current) {
        if (current == null) {
            return 0;
        } else {
            return current.N;
        }
    }

    /**
     * lowest key
     * @return
     */
    @Override
    public KeyType min() {

        Node n = root;
        while (n.left != null) {
            n = n.left;
        }

        return n.key;
    }

    /**
     * highest key
     * @return
     */
    @Override
    public KeyType max() {
        Node n = root;
        while (n.right != null) {
            n = n.right;
        }

        return n.key;
    }

    /**
     * return the key node if present, otherwise
     * the node just below key in ordering
     * find largest key that is less than or equal
     * to the given key
     * ex 3.1.17
     * @param key
     * @return
     */
    @Override
    public KeyType floor(KeyType key) {

        Node x = floor(root, key);

        if (x == null) {
            return null;
        }

        return x.key;
    }

    /*
     * Look recursively for the floor, i.e. the largest key
     * that is less/equal to the given key
     * code from book Algorithms
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, KeyType key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0)
            return x;

        if (cmp < 0)
            return floor(x.left, key);

        Node t = floor(x.right,key);

        if (t != null)
            return t;
        else
            return x;
    }


    /**
     * return key node if present, otherwise return
     * the next highest node that is present
     * find smallest key that is greater than or equal
     * to the given key
     * ex 3.1.16
     * @param key
     * @return
     */
    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling(root, key);

        if (x == null) {
            return null;
        }

        return x.key;
    }

    /**
     * Look recursively for the ceiling, i.e. the smallest key
     * that is greater/equal to the given key
     * @param x
     * @param key
     * @return
     */
    private Node ceiling(Node x, KeyType key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0)
            return x;

        if (cmp > 0)
            return ceiling(x.right, key);

        Node t = ceiling(x.left,key);

        if (t != null)
            return t;
        else
            return x;
    }

    /**
     * return N value from node to left of this one, or 0 if null
     * @param key
     * @return
     */
    @Override
    public int rank(KeyType key) {
        return rank(root, key);
    }

    private int rank(Node n, KeyType key) {
        if (n == null)
            return 0;

        int compare = key.compareTo(n.key);

        if (compare < 0)
            return rank(n.left, key);

        if (compare > 0)
            return 1 + size(n.left) + rank(n.right, key);
        else
            return size(n.left);
    }

    /**
     * Code from the book, Algorithms.
     * @param k
     * @return
     */
    @Override
    public KeyType select(int k) {
        return select(k, root).key;
    }

    /*
     * Recursive helper method for select(int k).
     * @param k
     * @param n
     * @return
     */
    private Node select(int k, Node n) {
        if (n == null) {
            return null;
        }
        int t = size(n.left);
        if (t > k) {
            return select(k, n.left);
        } else if (t < k) {
            return select(k-t-1, n.right);
        } else {
            return n;
        }
    }


    /*
     * implement our in-order traversal here
     */
    @Override
    public Iterable<KeyType> keys() {
        // new empty queue to hold results
        Queue<KeyType> queue = new Queue<>();

        // start the recursion
        inorder(root, queue);

        return queue;
    }


    // Helper method for keys(), recursively finds list of
    // keys inside tree
    private void inorder(Node current, Queue<KeyType> q) {
        if (current == null) {
            // do nothing - intentionally blank
            return;
        }

        // left subtree
        inorder(current.left, q);

        // add self to queue
        q.enqueue(current.key);

        // right subtree
        inorder(current.right, q);
    }
}