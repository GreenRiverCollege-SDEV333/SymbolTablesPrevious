package edu.greenriver.sdev333;
import java.util.NoSuchElementException;
/**
 * Binary Search Tree symbol table
 * Refer to p. 396-415 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    //fields
    private Node root;//top of treee
    //helper
    private class Node{
        private KeyType key;
        private ValueType value;
        private Node left;
        private Node right;
        //number of nodes in the subtree rooted here
        private int N;

        public Node(KeyType key, ValueType value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }
    //put helper to clean up code
    private Node put(Node current, KeyType key, ValueType value) {
        if (current == null) {
            return new Node(key, value, 1);
        }
        int CompareTO = key.compareTo(current.key);
        //go left (min/smaller number)
        if (CompareTO < 0) {
            current.left = put(current.left, key, value);
        }
        //go right (bigger/ max number)
        else if (CompareTO > 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }
        //update N
        current.N = size(current.left) + size(current.right) + 1;
        return current;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        root = put(root, key, value);
    }
    //get helper to clean up code
    private ValueType get(Node current, KeyType key){

        if (current == null){
            return null;
        }
        int cmp = key.compareTo(current.key);
        if(cmp < 0){
            return get(current.left, key);
        }
        else if (cmp > 0) {
            return get(current.right, key);
        }
        else{
            //System.out.println(current.value);
            return current.value;
        }
    }
    @Override
    public ValueType get(KeyType key) {
        return get(root,key);
    }
    //size helper
    private int size(Node current){
        if(current == null){
            return 0;
        }
        else{
            return current.N;
        }
    }
    @Override
    public int size() {
        return size(root);
    }
    //min helper
    private Node min(Node current){
        if(current.left == null){
            return current;
        }
        return min(current.left);
    }
    @Override
    public KeyType min() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node current = min(root);
        return current.key;
    }
    //max helper
    private Node max(Node current){
        if(current.right == null){
            return current;
        }
        else{
            return max(current.right);
        }
    }
    @Override
    public KeyType max() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node current = max(root);
        return current.key;
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
        //empty queue to hold my result

        Queue<KeyType> queue = new Queue<>();
        //start recursion
        inOrder(root, queue);
        //when done return the queue
        return queue;
    }

    private void inOrder(Node current, Queue<KeyType> q){
        if(current == null){
            //do nothing - intentionally blank
            return;
        }

        inOrder(current.left, q);
        q.enqueue(current.key);
        inOrder(current.right, q);
    }
}
