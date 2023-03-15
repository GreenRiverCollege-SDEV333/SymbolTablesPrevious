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

    public BinarySearchST(int capacity) {
        keys = (KeyType[])new Comparable[capacity];
        vals = (ValueType[])new Object[capacity];
    }

    @Override
    public void put(KeyType key, ValueType value) {
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
        N++;                // Okay here, as if simply replacing value, function has already returned
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

    @Override
    public KeyType min() {
        return null;
    }

    @Override
    public KeyType max() {
        return null;
    }

    /**
     * Return the largest key that is less than or equal to the given key
     * @param key
     * @return
     */
    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    /**
     * Return the smallest key that is greater than or equal to the given key
     * @param key
     * @return
     */
    @Override
    public KeyType ceiling(KeyType key) {
        return null;
    }

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

    /**
     * Returns a queue of keys for this data structure.
     * @return
     */
    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();

        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }

        return queue;
    }
}