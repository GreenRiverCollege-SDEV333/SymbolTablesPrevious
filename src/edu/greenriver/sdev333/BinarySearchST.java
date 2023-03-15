package edu.greenriver.sdev333;

import java.util.Queue;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    private KeyType[] keys;
    private ValueType[] vals;
    private int n;

    public BinarySearchST(int capacity){
        // See Algorithm 1.1 for standard array-resizing code
        keys = (KeyType[]) new Comparable[capacity];
        vals = (ValueType[]) new Object[capacity];
    }

    @Override
    public void put(KeyType key, ValueType value) {
        //Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0){
            vals[i] = value;
            return;
        }

        for (int j = n; j < i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;
        vals[i] = value;
        n++;
    }

    @Override
    public ValueType get(KeyType key) {
        if (isEmpty()){
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0 ){
            return vals[i];
        }
        else{
            return null;
        }
        //return null;
    }

    @Override
    public boolean isEmpty() {
        return OrderedSymbolTable.super.isEmpty();
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public KeyType min() {
        return keys[0];
    }

    @Override
    public KeyType max() {
        return keys[n-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        //See exercise 3.1.17
        return key;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key);
        return keys[i];
    }

    @Override
    public int rank(KeyType key) {
        //See page 381
        int lo = 0, hi = n-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0){
                hi = mid - 1;
            }
            else if (cmp > 0){
                lo = mid + 1;
            }
            else{
                return mid;
            }
        }
        return lo;
    }

    @Override
    public KeyType select(int k) {
        return keys[k];
    }

//    public KeyType delete(KeyType key){
//        //See exercise 3.1.16
//        return key;
//    }

//    @Override
//    public Iterable<KeyType> keys(KeyType lo, KeyType hi) {
//        Queue<KeyType> queue = new Queue<KeyType>();
//
//        for (int i = rank(lo); i < rank(hi); i++){
//            queue.enqueue(keys[i]);
//        }
//            if(contains(hi)){
//                queue.enqueue(keys[rank(hi)]);
//            }
//            return queue;
//        }
//    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
