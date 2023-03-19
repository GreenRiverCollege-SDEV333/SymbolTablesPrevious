package edu.greenriver.sdev333;
// page 389

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.NoSuchElementException;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    // field
    private Node root;

    private class Node {
        private KeyType key;
        private ValueType val;
        private Node left;
        private Node right;
        private int N;// number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        // starts recursion
        root = put(root, key, value);
    }

    private Node put(Node current, KeyType key, ValueType val) {
        // current is the root of the subtree we are looking at
        if (current == null) {
            // creat a new node
            return new Node(key, val, 1);
        }
        // cmp -1 for key < current.key, 0 key == current.key, 1 key > current.key
        int cmp = key.compareTo(current.key);
        // go left
        if (cmp < 0) {
            current.left = put(current.left, key , val);
        }
        // go right
        else if (cmp > 0) {
            current.right = put(current.right, key, val);
        } else {
            // key already exist
            current.val = val;
        }
        // update the node's n and number of nodes in subtree
        // size of left, right and self
        current.N = size(current.left) + size(current.right) + 1;

        return current;
    }

    /*
     @Override
    public ValueType get(KeyType key) {
        // someone gives me a key, I want to find the value that goes with that key
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            // compareTo returns neg, zero, pos

            if (cmp < 0) {
                current = current.left;
            } else if ( cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        } // end of while loop

        return null;
    }
     */
    @Override
    public ValueType get(KeyType key) {
       // using recursion approach for get
        return get(root, key);
    }

    private ValueType get(Node current, KeyType key) {
        // if author uses  x instead of current
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

    // helper class for int size
    private int size (Node current) {
        if (current == null) return 0;

        return current.N;
    }

    private Node min(Node current) {
        if (current.left == current.right) {
            return current;
        }
        return min(current.left);
    }
    @Override
    public KeyType min() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }

        Node current = min(root);
        return current.key;
    }

    @Override
    public KeyType max() {
        return null;
    }

    private Node floor(Node current, KeyType key) {
        if (current == null) {
            return null;
        }
        int cmp = key.compareTo(current.key);
        if(cmp == 0) {
            return current;
        }
        if(cmp  < 0) {
            return floor(current.left, key);
        }
        Node t = floor(current.right, key);
        if (t != null) {
            return t;
        } else {
            return current;
        }
    }
    @Override
    public KeyType floor(KeyType key) {
        Node current = floor(root,key);
        if (current == null) {
            throw new NoSuchElementException();
        }
        return current.key;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        return null;
    }

    private int rank(KeyType key, Node current) {
        // return numbers of keys less tha nthe key in the subtree rooted at current
        if(current == null) {
            return 0;
        }
        int cmp = key.compareTo(current.key);
        if (cmp < 0) {
            return rank(key,current.left);
        } else if (cmp > 0) {
            return 1 + size(current.left) + rank (key, current.right);
        } else {
            return size(current.left);
        }
    }
    @Override
    public int rank(KeyType key) {
        return rank(key,root);
    }

    private Node select(Node current, int k){
        // return node containg the key of  rank k
        if (current == null) {
            return null;
        }
        int t = size(current.left);
        if (t > k) {
            return select(current.left, k);
        } else if (t < k) {
            return select(current.right,k-t-1);
        }
        else {
            return current;
        }
    }
    @Override
    public KeyType select(int k) {
        if(k < 0 || k >=size()) {
            throw new IllegalArgumentException();
        }
        Node current = select(root, int k);
        return current.key;
    }

    @Override
    public Iterable<KeyType> keys() {
        // create a new empty queue to hol a result
        Queue<KeyType> queue = new Queue<>();

        // start the recursion, collecting results in the queue
        inorder(root,queue);
        // when done, return queue
        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q) {
        if (current == null) {
            // do nothing
            return;
        }

        inorder(current.left,q);
        q.enqueue(current.key);
        inorder(current.right,q);
    }
}
