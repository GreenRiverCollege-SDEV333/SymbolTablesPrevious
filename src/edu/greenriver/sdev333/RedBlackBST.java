package edu.greenriver.sdev333;

import java.util.NoSuchElementException;

/**
 * Red Black (Balanced) Binary Search Tree symbol table
 * Refer to p. 424-448 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class RedBlackBST <KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    private Node root;
    private boolean RED = true;
    private boolean BLACK = false;

    private class Node {
        KeyType key;
        ValueType val;
        Node left;
        Node right;
        int n;
        boolean color;

        Node(KeyType key, ValueType val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }
    private boolean isRed(Node h) {
        if(h==null) {
            return false;
        }
        return h.color == RED;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);

        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1 + size(h.left) + size(h.right);

        return x;
    }
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    @Override
    public void put(KeyType key, ValueType val) {
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node h, KeyType key, ValueType val) {
        if(h==null) {
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if(cmp < 0) {
            h.left = put(h.left, key, val);
        } else if(cmp >0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }

        if(isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if(isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    @Override
    public ValueType get(KeyType key) {
        return get(root, key);
    }
    //helper
    private ValueType get(Node x, KeyType key) {
        //return value associate with key in subtree rooted at node x
        //return null if key is not present in subtree rooted at node x
        if(x==null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if(x==null) {
            return 0;
        } else {
            return x.n;
        }
    }

    @Override
    public KeyType min() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node x = min(root);
        return x.key;
    }
    private Node min(Node x) {
        if(x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public KeyType max() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node x = max(root);
        return x.key;
    }
    private Node max(Node x) {
        if(x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node x = floor(root, key);
        if(x==null) {
            throw new NoSuchElementException();
        }
        return x.key;
    }
    private Node floor(Node x, KeyType key) {
        if(x==null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp ==0) {
            return x;
        } else if(cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if(t != null) {
            return t;
        } else {
            return x;
        }
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
        //return number of keys less than key in subtree rooted at Node x
        if(x==null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            return rank(key, x.left);
        } else if( cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    @Override
    public KeyType select(int k) {
        if(k < 0 || k>= size()) {
            throw new IllegalArgumentException();
        }
        Node x = select(root, k);
        return x.key;
    }
    private Node select(Node x, int k) {
        //return Node that contains key of rank k
        if(x==null) {
            return null;
        }
        int t = size(x.left);
        if(t > k) {
            return select(x.left, k);
        } else if(t < k) {
            return select(x.right, k-t-1);
        } else {
            return x;
        }
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();

        //start the recursion, collecting the results in the queue
        inorder(root,queue);

        //when done, return the queue
        return queue;
    }

    //helper for keys method
    public void inorder(Node current, Queue<KeyType> q){
        if (current == null) {
            //if current = null do nothing
            return;
        }

        inorder(current.left, q);

        q.enqueue(current.key);

        inorder(current.right, q);
    }
}
