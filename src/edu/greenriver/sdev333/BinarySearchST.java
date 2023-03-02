package edu.greenriver.sdev333;

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

    @Override
    public void put(KeyType key, ValueType value) {
        //check if the key is in the table
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0){
            //updating the existing key
            vals[i] = value;
            return;
        }

        //if not add a new key value
        for(int k = n; k > i; k--){
            keys[k] = keys[k-1];
            vals[k] = vals[k-1];
        }
        keys[i] = key;
        vals[i] = value;
    }

    @Override
    public ValueType get(KeyType key) {
        //if the st is empty
        if(isEmpty()){
            return null;
        }
        //find the rank of the key in the keys array
        int i = rank(key);
        if(i < n && key.compareTo(keys[i]) == 0){
            //return the value associated with the key
            return vals[i];
        }
        //if the keys is not in st
        return null;
    }

    @Override
    public int size() {
        //the size is equal to the number of keys
        return n;
    }

    @Override
    public KeyType min() {
        //if the st is empty
       if(isEmpty()){
           return null;
       }
       //return smallest key
       return keys[0];
    }

    @Override
    public KeyType max() {
        //if the st is empty
        if(isEmpty()){
            return null;
        }
        //return largest key
        return keys[n-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        //find the rank of the key in the keys array
        int i = rank(key);
        //if the key is less than all keys
        if(i == 0){
            return null;
        }
        //if the key is equal, return that key
        if (key.compareTo(keys[i-1]) == 0){
            return keys[i-1];
        }
        //return the keys at position i-1
        return keys[i-1];
    }

    @Override
    public KeyType ceiling(KeyType key) {
        //find the rank of the key in the keys array
        int i = rank(key);
        //if the key is greater than all keys in the st, return null
        if(i == n){
            return null;
        }
        //return the key at position i
        return keys[i];
    }

    @Override
    public int rank(KeyType key) {
        int lo =0;
        int hi = n -1;
        while (lo <= hi){
            int mid = lo + (hi -lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp<0){
                hi = mid -1;
            } else if (cmp>0) {
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return 0;
    }

    @Override
    public KeyType select(int k) {
        if(k < 0 || k >= n){
            return null;
        }
        return keys[k];
    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
