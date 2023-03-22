package edu.greenriver.sdev333;

import com.sun.jdi.Value;

import java.security.Key;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    // fields:
    // array of linked lists
    private SequentialSearchST<KeyType, ValueType>[] st;
    private int M;

    public SeparateChainingHashST(int M) {
        // take their number of buckets, save it into my field
        this.M = M;

        // create new array
        st = new SequentialSearchST[M];

        // for each bucket, create a linked list
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(KeyType key) {
        // hash function
        // given a key, returns an int (bucket #, array index)
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key); // find the bucket number
        st[index].put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key); // find the bucket number
        return st[index].get(key);
    }

    @Override
    public int size() {
        // go through each bucket and add up the individual size
        int sum = 0;
        for (int i = 0; i < M; i++) {
            sum += st[i].size();
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (KeyType key : st[i].keys()) {
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
