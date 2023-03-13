package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * Code is listed on pg. 465
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    // fields
    // array of linked lists - but we wrote a linked list in SequentialSearchST
    private SequentialSearchST<KeyType, ValueType>[] st;
    private int M;      // M is number of buckets (or piles)

    public SeparateChainingHashST() {
        this(997);  // prime # ensure more unique lists, ~1000x faster according to comment from book
    }
    public SeparateChainingHashST(int M) {
        this.M = M;

        // create the array
        st = new SequentialSearchST[M];

        // for each position in the array create a linked list
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    /**
     * Returns a boolean value indicating whether the
     * given key exists in the data structure or not.
     * @param key
     * @return
     */
    @Override
    public boolean contains(KeyType key) {
        return get(key) != null;
    }

    private int hash(KeyType key) {
        // hash function = they give me a key, I return an int (bucket #)
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * save the given key/value pair
     * @param key
     * @param value
     */
    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key);      // find bucket #
        st[index].put(key, value);   // put the key inside the bucket
    }

    /**
     * Return the key type from the given array index / linked list
     * @param key
     * @return
     */
    @Override
    public ValueType get(KeyType key) {
        int index = hash(key);      // find index
        return st[index].get(key);  // go into bucket and return that value (or null)
    }

    /**
     * Loop through the buckets and sum their size, return that value
     * @return
     */
    @Override
    public int size() {
        int sum = 0;
        for (int i = 0; i < M; i++) {
            sum += st[i].size();
        }
        return sum;
    }

    /**
     * Return the keys from each separate linked list in the individual
     * array indexes
     * @return
     */
    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        // for every bucket
        for (int i = 0; i < M; i++) {
            // take each key and add it into the collector
            for (KeyType key: st[i].keys()) {
                collector.enqueue(key);
            }
        }

        return collector;
    }
}
