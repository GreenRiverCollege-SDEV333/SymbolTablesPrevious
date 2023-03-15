package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private int M; // M is the number of buckets (or pile)
    private SequentialSearchST<KeyType, ValueType>[] st; // array of ST objects

    public SeparateChainingHashST(int M){
        // Take their number of buckets, save it into my filed
        this.M = M;
        st = (SequentialSearchST<KeyType, ValueType>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(KeyType key){
        // hash function = they give me a key, I return an int (bucket #, array index)
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key);
        // put the key and value into that bucket
        st[index].put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key);
        return st[index].get(key);
    }

    @Override
    public int size() {
        //go through each bucket and add up each individual size
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
            for (KeyType key : st[i].keys()){
                collector.enqueue(key);
            }
        }
        return collector;
    }

    public boolean contains(KeyType key) {
        ValueType value = get(key);
        return value != null;
    }
}
