package edu.greenriver.sdev333;
//see page 398 in text book

import com.sun.jdi.Value;

import java.util.NoSuchElementException;

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
        private int N; // Number of Nodes in the subtree rooted here

        public Node(KeyType key, ValueType val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }

    }
    @Override
    public void put(KeyType key, ValueType val) {
        root = put(root,key,val);
    }
    //helper method for put using recursion
    private Node put(Node current, KeyType key, ValueType val){
        //current is the root of the subtree we are looking at

        //where we are supposed to be
        if (current == null) {

          return  new Node(key, val,1);
        }

        int cmp = key.compareTo(current.key);
            // cmp will be -1(negative) if key < current.key
            //cmp will be 0(zero) if the key == current.key
            // cmp will be +1(positive) if key > current.key

        if(cmp < 0){
            // go left
            current.left = put(current.left,key,val);
        }
        else if(cmp > 0){
            // go right
            current.right = put(current.right,key,val);
        }
        else{
            //key already exist, repalce the data
            current.val= val;
        }

        current.N =size(current.left) + size(current.right) + 1;

        return current;


        // go right
    }


    /*
    @Override                   //writing the get method with a loop!
    public ValueType get(KeyType key) {

        //if someone gives me a key, i want to find the value for that key
        Node current = root;
        while(current != null){
            int cmp = key.compareTo(current.key);
            //compareTo returns neg, zero, pos

            if(cmp < 0){
                current = current.left;
            }else if(cmp > 0){
                current = current.right;
            }else {
                return current.val;
            }
        }// end of the while loop

        return null;
    }*/

    public ValueType get(KeyType key) {
        return get(root,key);
    }

    private ValueType get(Node current, KeyType key){
        if(current == null){
            return null;
        }

        int cmp = key.compareTo(current.key);

        if(cmp < 0){
            return get(current.left, key);
        }
        else if(cmp > 0){
            return get(current.right, key);
        }
        else{
            return current.val;
        }
    }

    @Override
    public int size() {
       return size(root);
    }

    private int size(Node current){
        if(current == null){
            return 0;
        }else
        {
            return current.N;
        }
    }
    @Override
    public KeyType min() {
        if(isEmpty()) throw new NoSuchElementException();
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    @Override
    public KeyType max() {
        if(isEmpty()) throw new NoSuchElementException();
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x){
        if(x.right == null) return x;
        return min(x.right);
    }

    @Override
    public KeyType floor(KeyType key) {
        Node x = floor(root,key);
        if(x == null) throw new NoSuchElementException();
        return x.key;
    }

    private Node floor(Node x, KeyType key){
        if( x == null) return null;
        int cmp = key.compareTo(x.key);
        if( cmp == 0) return x;
        if(cmp < 0) return floor(x.left,key);
        Node t = floor(x.right,key);
        if(t != null) return t;
        else return x;

    }

    @Override
    public KeyType ceiling(KeyType key) {
        Node x = ceiling(root,key);
        if(x == null) throw new NoSuchElementException();
        return x.key;
    }

    private Node ceiling(Node x, KeyType key){
        if( x == null) return null;
        int cmp = key.compareTo(x.key);
        if( cmp == 0) return x;
        if(cmp > 0) return floor(x.right,key);
        Node t = floor(x.left,key);
        if(t != null) return t;
        else return x;

    }

    @Override
    public int rank(KeyType key) {
        return rank(key,root);

    }

    public int rank(KeyType key, Node x){
        //return number of keys less than key in the subtree rooted at x
        if( x == null) return 0;
        int cmp = key.compareTo(x.key);
        if( cmp < 0){
            return rank(key, x.left);
        }
        else if( cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }
        else{
            return size(x.left);
        }

    }

    @Override
    public KeyType select(int k) {
        if(k < 0|| k >= size()) throw new IllegalArgumentException();
        Node x = select(root,k);
        return x.key;
    }

    private Node select( Node x, int k){
        //return Node containng key of rank k
        if(x == null) return null;
        int t = size(x.left);
        if  (t>k) return select(x.left, k);
        else if( t < k) return select(x.right,k-t-1);
        else  return x;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();
        //start the recursion, collecting the results in the queue
        inorder(root,queue);
        //when done, return the queue
        return queue;
    }

    private void inorder(Node current,Queue<KeyType> q){
            if (current == null){
             // do nothing - intentionally blank
                return;
            }

            inorder(current.left,q);
            q.enqueue(current.key);
            inorder(current.right,q);
    }
}
