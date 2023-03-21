package edu.greenriver.sdev333;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {

    private KeyType[] keys;
    private ValueType[] values;
    private int size;

    public BinarySearchST(int capacity) {
        keys = (KeyType[]) new Comparable[capacity];
        values = (ValueType[]) new Object[capacity];
    }

    private void resize(int capacity) {
        KeyType[] tempKeys = (KeyType[]) new Comparable[capacity];
        ValueType[] tempValues = (ValueType[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        keys = tempKeys;
        values = tempValues;
    }

    @Override
    public void put(KeyType key, ValueType value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (value == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        if (size == keys.length) {
            resize(2 * keys.length);
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public ValueType get(KeyType key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    @Override
    public void delete(KeyType key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i == size || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < size-1; j++) {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }
        size--;
        keys[size] = null;
        values[size] = null;
        if (size > 0 && size == keys.length/4) {
            resize(keys.length/2);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public KeyType min() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    @Override
    public KeyType max() {
        if (isEmpty()) {
            return null;
        }
        return keys[size-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        int i = rank(key);

        if (i == 0) {
            return null;
        } else if (i < size && keys[i].compareTo(key) == 0) {
            return keys[i];
        } else {
            return keys[i - 1];
        }
    }

    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key);

        if (i == size) {
            return null;
        } else {
            return keys[i];
        }
    }

    @Override
    public int rank(KeyType key) {
        int lo = 0, hi = size - 1;

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
        return null;
    }
}
