package edu.greenriver.sdev333;

import java.security.Key;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * COde is listed on p. 465 of the book
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    //fields:
    //arrays of linked lists
    //      VV linked list VV        VV array of them, st is variable name

    private SequentialSearchST<KeyType, ValueType>[] st;
    private int M;  // M is the number of buckets

    public SeparateChainingHashST() {
       //default constructor, take no parameters
       //call the other constructor with a value
       this(997);
    }

    public SeparateChainingHashST(int M){
        //take their number of buckets, save it into my field
        this.M = M;

        //create the array
        st = new SequentialSearchST[M];
        // st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];

        // for each position in the array (for each bucket)
        // create a linked list (sequentialsearchst) in each bucket
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
        int index = hash(key);      // find the bucket number
        // put the key and value into the bucket
        st[index].put(key, value);
    }

    //  public void put(Key key, Value val){
    //      st[hash(key)].put(key, val);
    //  }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key);      //find the bucket number
        // go into that bucket and see if it's there
        return st[index].get(key);
    }

    //  public Value get(Key key){
    //      return (Value) st[hash(key)].get(key);
    //  }

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
    public Iterable<KeyType> keys() {
       Queue<KeyType> collector = new Queue<>();
       // for every bucket
        for (int i = 0; i < M; i++) {
            //take each key in that bucket and add it into the collector
            for (KeyType key: st[i].keys()) {
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
