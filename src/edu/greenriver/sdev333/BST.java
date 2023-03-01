package edu.greenriver.sdev333;
//see page 398 in text book

import com.sun.jdi.Value;

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
