package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private int m; //hash table size
    private SequentialSearchST<KeyType, ValueType>[] st; //array of st objects

    public SeparateChainingHashST() {
        //default constructor
        //calls parameterized constructor with a value
        this(997);
    }
    public SeparateChainingHashST(int m) {
        //create linked lists
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }
    private int hash(KeyType key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    @Override
    public void put(KeyType key, ValueType val) {
        int index = hash(key); //find the bucket number

        //put the key and value into that bucket
        st[index].put(key, val);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key); //find the bucket number

        //go into bucket and see if it's there
        return st[index].get(key);
    }

    @Override
    public int size() {
        //go through each bucket and add up each individual size
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += st[i].size();
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        // for every bucket
        for (int i = 0; i < m; i++) {
            // take every key in that bucket and add it to the collector
            for (KeyType key : st[i].keys()) {
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
