package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

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
        private int N; // number of nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root, key, value);
    }

    public Node put(Node current, KeyType key, ValueType value ){
        //current is null create a new node n = 1
        if(current == null){
            return new Node(key, value, 1);
        }
        //compare key past in to current key
        int cmp = key.compareTo(current.key);

        //if cmp less than 0 then put on left, if greater than 0 put on right else change current val
        if (cmp < 0 ) {
            current.left = put(current.left, key, value);
        } else if (cmp > 0 ){
            current.right = put(current.right, key, value);
        } else {
            current.val = value;
        }
        //increment n
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }

    @Override
    public ValueType get(KeyType key) {
        // someone gives me a key, I want to find the value that goes with that key
        //loop style
//        Node current = root;
//        while(current != null){
//            int cmp = key.compareTo(current.key);
//            if (cmp < 0 ){
//                 current = current.left;
//            } else if (cmp > 0){
//                current = current.right;
//            } else {
//                return current.val;
//            }
//        }
//        return null;

        return get(root,key);
    }

    //recursion helper
    private ValueType get(Node current, KeyType key){
        //author uses x instead of current
        if (current == null){
            return null;
        }

        int cmp = key.compareTo(current.key);
        if (cmp < 0 ){
            return get(current.left, key);
        } else if (cmp > 0 ){
            return get(current.right, key);
        } else {
            return current.val;
        }
    }

    @Override
    public int size() {
        if (root == null){
            return 0;
        } else {
            return root.N;
        }
    }

    private int size(Node current) {
        if (current == null){
            return 0;
        } else {
            return current.N;
        }
    }

    @Override
    public KeyType min() {
        //if empty then error (no minimum exists)
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = min(root);
        return current.key;
    }

    public Node min(Node current){
        //when current.left == null current == min
        if(current.left == null){
            return current;
        }
        //recursively calls min(current.left)until current.left == null at which point we have found the minimum and then return it.
        return min(current.left);
    }

    @Override
    public KeyType max() {
        //if empty then error (no Maximum exists)
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node current = max(root);
        return current.key;
    }

    public Node max(Node current){
        //when current.right == null current == max
        if(current.right == null){
            return current;
        }
        //recursively calls min(current.right)until current.right == null at which point we have found the maximum and then return it.
        return max(current.right);
    }

    //Dont understand this one yet
    @Override
    public KeyType floor(KeyType key) {
        Node current = floor(root, key);
        if (current == null) throw new NoSuchElementException();
        return current.key;
    }

    public Node floor(Node current, KeyType key){
        if ( current == null ){
            return null;
        }
        int cmp = key.compareTo(current.key);
        if (cmp == 0) return current;
        if (cmp < 0 ) return floor(current.left, key);
        Node t = floor(current.right, key);
        if (t != null) return t;
        else return current;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node current = ceiling(root, key);
        if (current == null) throw new NoSuchElementException();
        return current.key;
    }

    public Node ceiling(Node current, KeyType key){
        if ( current == null ){
            return null;
        }
        int cmp = key.compareTo(current.key);
        if (cmp == 0) return current;
        if (cmp > 0 ) return ceiling(current.right, key);
        Node t = ceiling(current.left, key);
        if (t != null) return t;
        else return current;
    }

    //Dont fully understand this one yet
    @Override
    public int rank(KeyType key) {
        return rank(key,root);
    }

    @Override
    public int rank(KeyType key, Node current) {
        //return number of keys less than key in the subtree rooted at x
        if(current == null){
            return 0;
        }
        int cmp = key.compareTo(current.key);
        if (cmp > 0){
            return rank(key, current.left);
        } else if (cmp < 0) {
            return 1 + size(current.left) + rank(key, current.right);
        } else {
            return size(x.left);
        }
    }

    @Override
    public KeyType select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException();
        Node current = select(root, k);
        return current.key;
    }

    public Node select(Node current, int k ){
        if (current == null){
            return null;
        }
        int t = size(current.left);
        if (t > k){
            return select(x.left, k);
        } else if (t < k){
            return select(x.right, k-t-1);
        } else {
            return current;
        }
    }

    @Override
    public Iterable<KeyType> keys() {
        //        return new Iterable<>() {
        //            public Iterator<KeyType> iterator() {
        //                return new BSTIterator<KeyType, ValueType>(root);
        //            }
        //        };

        // or

        return () -> new BSTIterator<KeyType, ValueType>(root);
    }

    public class BSTIterator<KeyType extends Comparable<KeyType>, ValueType> implements Iterator<KeyType> {

        private Node current;
        private Stack<Node> stack;

        public BSTIterator(Node root) {
            current = root;
            stack = new Stack<>();
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public KeyType next() {
            //set current to top of the stack
            current = stack.pop();
            //set a variable for current key to be returned later
            KeyType key = (KeyType) current.key;
            //current.right night null then move to current.right
            //when current is not null push to stack then move left
            if (current.right != null) {
                current = current.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
            return key;
        }
    }
}
