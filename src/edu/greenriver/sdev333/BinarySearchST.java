package edu.greenriver.sdev333;

import javax.swing.*;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    // fields
    private KeyType[] keys;
    private ValueType[] vals;
    private int N;

    /**
     * constructor that initializes and creates symbol table
     *
     * @param capacity - size of symbol table
     */
    public BinarySearchST(int capacity)
    {
        keys = (KeyType[]) new Comparable[capacity];
        vals = (ValueType[]) new Object[capacity];
    }

    /**
     * method that places key-value pair into symbol table
     *
     * @param key - key; where to insert node into table
     * @param value - value of node
     */
    @Override
    public void put(KeyType key, ValueType value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        {  vals[i] = value; return;  }
        for (int j = N; j > i; j--)
        {  keys[j] = keys[j-1]; vals[j] = vals[j-1];  }
        keys[i] = key; vals[i] = value;
        N++;

    }

    /**
     * method that returns associated value of specified key
     * @param key - key
     * @return value of key
     */
    @Override
    public ValueType get(KeyType key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else
        {
            return null;
        }
    }

    /**
     * method that returns size of symbol table
     * @return N
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * method that returns smallest key in the symbol table
     * @return min - keys[0]
     */
    @Override
    public KeyType min() {
        return keys[0];
    }

    /**
     * method that return the largest key in the symbol table
     * @return max - key[size-1]
     */
    @Override
    public KeyType max() {
        return keys[N-1];
    }

    /**
     * method that returns the largest key in the symbol table is less than
     * or equal to the specified key
     *
     * @param key - key
     * @return - floor key
     */
    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    /**
     * method that returns smallest key that is greater than or equal to
     * specified key
     * @param key - key
     * @return ceiling key
     */
    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key);
        return keys[i];
    }

    /**
     * method that returns number of keys that are smaller than specified
     * key
     * @param key - key
     * @return rank
     */
    @Override
    public int rank(KeyType key) {
        int lo = 0, hi = N-1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * method that returns the key rank of k
     * @param k - int
     * @return key rank of k
     */
    @Override
    public KeyType select(int k) {
        return keys[k];
    }

    /**
     * iterable method that returns all keys in the symbol table in sorted order
     *
     * @return
     */
    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
