package edu.greenriver.sdev333;

import java.util.LinkedList;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    // fields
    private KeyType[] keys; // array to store keys
    private ValueType[] vals; //array to store values
    private int n; //number of element in the table

    // constructor
    public BinarySearchST(int capacity){
        keys = (KeyType[]) new Comparable[capacity];
        vals = (ValueType[]) new Object[capacity];
    }


    @Override
    public void put(KeyType key, ValueType value) {
        int i = rank(key); // find the index of the given key using binary search
        if (i < n && keys[i].compareTo(key) == 0){ //if index smaller than size and index equal to key
            vals[i] = value; //assign value ot index i of vals array
            return;
        }
        //if not found add new key and new value to keys and vals array
        for (int j = n; j > i ; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        //increase the size
        n++;
    }

    @Override
    public ValueType get(KeyType key) {
        if (isEmpty())
        {
            return null;
        }
        int i = rank(key); // find the index of the given key using binary search
        if (i < n && keys[i].compareTo(key) == 0){ //if index smaller than size and index equal to key
            return vals[i]; //return value of the key
        } else { //if not found return null
            return null;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public KeyType min() {
        //return the first index in array keys
        return keys[0];
    }

    @Override
    public KeyType max() {
        // return the last index of array keys
        return keys[n-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        // find the index of the given key using binary search
        int i = rank(key);
        // if the key is found, return it
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        } else if (i == 0) {// if the key is smaller than all the keys in the table, return null
            return null;
        } else {  // otherwise, return the largest key in the table that is less than the given key
            return keys[i-1];
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key); //search key using binary search rank method
        return keys[i]; //return keys at index i
    }

    @Override
    // method computes the rank of a key using binary search
    public int rank(KeyType key) {
        int low = 0, high = n - 1;
        while (low <= high){
            // find the middle index
            int mid = low + (high - low) / 2;
            // compare the given key with the key at the middle index
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0){  // search left if key is smaller than middle key
                high = mid - 1;// update new high
            } else if (cmp > 0) { // search right if  key is larger than the key at the middle
                low = mid + 1; //update new low
            } else { // return middle key of equal
                return mid;
            }
        }
        // return the index where it should be if not found
        return low;
    }

    @Override
    public KeyType select(int k) {
        //return the k index of array keys
        return keys[k];
    }

    @Override
    public Iterable<KeyType> keys() {
        return keys(min(), max());
    }

    // return an iterable of all keys in the table in the range [lo, hi] in ascending order
    public Iterable<KeyType> keys(KeyType lo, KeyType hi) {
        // create a queue to store the keys
        Queue<KeyType> queue = new Queue<KeyType>();

        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)){
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }
}
