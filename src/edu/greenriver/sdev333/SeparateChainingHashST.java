package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    // fields

    private SequentialSearchST<KeyType,ValueType>[] st;
    private int M; // M is the number of buckets
    public SeparateChainingHashST()
    {
        // default constructor , takes no parameters
        // call the other constructor with a value

        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;

        st = new SequentialSearchST[M];

        //for each position in the array (for each bucket)
        // create a linked list (squentialsearchse) in each bucket
        for (int i = 0; i < M; i++)
        {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(KeyType key)
    {
          // hash function = they give me a key, i return an int(bucket #. array index)
        return (key.hashCode() & 0x7fffffff) % M;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key);
        st[index].put(key,value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key);
        return st[index].get(key);
    }

    @Override
    public int size() {
        // go through each bucket and add up the each individual size
        int sum = 0;
        for(int i =0; i <M; i ++)
        {
            sum += st[i].size();
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        for(int i = 0 ; i < M; i ++)
        {
            for(KeyType key : st[i].keys())
            {
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
