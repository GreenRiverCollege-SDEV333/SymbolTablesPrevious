package edu.greenriver.sdev333;

import java.security.Key;

/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    //book: Algorithm 3.3
    //field
    private Node root;

    private class Node{
        private KeyType key;
        private ValueType value;
        private Node left;
        private Node right;
        private int N; //number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N){
            this.key = key;
            this.value = val;
            this.N = N;
        }
    }
    @Override
    public void put(KeyType key, ValueType value) {
            root = put(root, key, value);
    }

    //helper method for put for recursion
    private Node put(Node current, KeyType key, ValueType val){
        //current is the root of the subtree we are looking at

        //we are at where we are going to be
        if(current == null){
            //create a new Node
            return new Node(key, val, 1);
            }
        int cmp = key.compareTo(current.key);
        //cmp will be -1 (negative) if key < current.key
        //cmp will be 0 (zero) if key == current.key
        //cmp will be 1 (positive) if key > current.key


        if(cmp < 0){
            //go left
            current.left = put(current.left, key, val);
        } else if (cmp > 0) {
            //go right
            current.right = put(current.right, key, val);
        }else{
            //key already exist
            current.value = val;
        }

        //update the node's N - number of nodes in the subtree
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    public ValueType get(KeyType key){
        return get(root, key);
    }

    private ValueType get(Node current, KeyType key){
        //author uses x instead of current
        if(current == null){
            return null;
        }

        int cmd = key.compareTo(current.key);
        if(cmd < 0){
            return get(current.left, key);
        } else if (cmd > 0) {
            return get(current.right, key);
        }else{
            return current.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node current) {
        if(current == null){
            return 0;
        }else{
            return current.N;
        }
    }

    @Override
    public KeyType min() {
        if(root == null){
            return null;
        }
        Node current = root;
        while (current.left != null){
            current = current.left;
        }
        return current.key;
    }

    @Override
    public KeyType max() {
       if(root == null){
           return null;
       }
       Node current = root;
       while (current.right != null){
           current = current.right;
       }
       return current.key;
    }

    @Override
    public KeyType floor(KeyType key) {
        Node current = floor(root, key);
        if(current == null){
            return null;
        }
        return current.key;
    }

    private Node floor(Node current, KeyType key){
        if(current == null){
            return null;
        }
        int cmp = key.compareTo(current.key);
        if(cmp == 0){
            return current;
        }
        if(cmp < 0){
            return floor(current.left, key);
        }
        Node n = floor(current.right, key);
        if(n != null){
            return n;
        }
        return current;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        if(root == null){
            return null;
        }

        Node current = root;
        Node ceilingNode = null;

        while (current != null){
            int cmp = key.compareTo(current.key);
            if(cmp < 0){
                ceilingNode = current;
                current = current.left;
            }else if(cmp > 0){
                current = current.right;
            }else{
                return current.key;
            }
        }
        return (ceilingNode != null) ? ceilingNode.key : null;
    }

    @Override
    public int rank(KeyType key) {
        return rank (root, key);
    }

    private int rank(Node current, KeyType key){
        if(current == null){
            return 0;
        }
        int cmp = key.compareTo(current.key);
        if(cmp < 0){
            return rank(current.left, key);
        }else if(cmp > 0){
            return size(current.left) + 1 + rank(current.right, key);
        }else{
            return size(current.left);
        }
    }

    @Override
    public KeyType select(int k) {
        return select(root, k).key;
    }

    private Node select(Node current, int k){
        if(current == null){
            return null;
        }
        int lSize = size(current.left);
        if(lSize > k){
            return select(current.left, k);
        }else if(lSize < k){
            return select(current.right, k - lSize - 1);
        }else{
            return current;
        }
    }

    @Override
    public Iterable<KeyType> keys() {
        //create a new empty queue to hold my results
        Queue<KeyType> queue = new Queue<>();

        //start the recursion, collecting results in the queue
        inorder(root, queue);

        //when done, return the queue
        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q){
        if(current == null){
            //DO NOTHING - end method immediately
            return;
        }
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }
}
