package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private int m;
    private SequentialSearchST<KeyType, ValueType>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        // Create m linked lists
        this.m = m;
        st = (SequentialSearchST<KeyType, ValueType>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(KeyType key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        st[hash(key)].put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        return (ValueType) st[hash(key)].get(key);
    }

    @Override
    public int size() {
        return this.m;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> keys = new Queue<>();

        for(SequentialSearchST table : st) {
            for (Object key : table.keys()) {
                keys.enqueue((KeyType) key);
            }
        }
        return keys;
    }
}
