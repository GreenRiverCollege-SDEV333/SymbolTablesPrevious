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
    private int N;

    //constructor
    public BinarySearchST(int capacity) {
        keys = (KeyType[])new Comparable[capacity];
        vals = (ValueType[])new Object[capacity];
    }

    /**
     * putting the key/value into the tree
     * @param key
     * @param value
     */
    @Override
    public void put(KeyType key, ValueType value) {
        //Searching for the key and updating the value if found
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;
    }

    @Override
    public ValueType get(KeyType key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i<N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * returns the smallest appropriate key
     * @return
     */
    @Override
    public KeyType min() {
        return keys[0];
    }

    /**
     * returns the biggest key value
     * @return
     */
    @Override
    public KeyType max() {
        return keys[N-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        return null;
    }

    /**
     * computes the number of keys in the table that are smaller than the key
     * @param key
     * @return
     */
    @Override
    public int rank(KeyType key) {
        int lo = 0;
        int hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public KeyType select(int k) {
        return null;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();

        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }

        return queue;
    }
}
