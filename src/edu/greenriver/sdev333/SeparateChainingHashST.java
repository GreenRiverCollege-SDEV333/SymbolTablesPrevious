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
    // array of linked lists - but we wrote a linked list in SequentialSearchST
    //      vv linked list      vv                vv array of them, st is variable name
    private SequentialSearchST<KeyType, ValueType>[] st;
    private int M; // M is the number of buckets (or piles)

    public SeparateChainingHashST() {
        // default constructor, takes no parameters
        // call the other constructor with a value
        this(997);
    }

    public SeparateChainingHashST(int M) {
        // take their number of buckets, save it in to my field
        this.M = M;

        // create the array
        st = new SequentialSearchST[M];

        // for each position in the array (for each bucket)
        // create a linked list (sequentialsearchst) in each bucket
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();

        }


    }


    private int hash(KeyType key) {
        // hash function = they give me a key, I return an int (bucket #, array index)
        return (key.hashCode() & 0x7fffffff) % M; // % (mod) gives you a # between 0 and 100

    }



    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key); // find the bucket number
        // put the key and value into the bucket
        st[index].put(key, value); // this put method is from the sequentialsearchst

    }

    @Override
    public ValueType get(KeyType key) {
        // find the bucket number and save in index
        int index = hash(key);
        // go into that bucket and see if it's there
        return st[index].get(key); // sequentialsearchst get method
    }

    @Override
    public int size() {
        // go through each bucket and add up each individual size
        int sum = 0;
        for (int i = 0; i < M; i++) {
            sum += st[i].size();
        }
        return sum;
    }

    @Override
    public boolean contains(KeyType key){
        // same as OrderedSymbolTable
        return get(key) != null;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        // for every bucket
        for (int i = 0; i < M; i++) {
            // take each key in the bucket and then add into the collector
            for(KeyType key : st[i].keys()){
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
