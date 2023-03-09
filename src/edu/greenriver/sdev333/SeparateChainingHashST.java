package edu.greenriver.sdev333;

import java.security.Key;
import java.util.ArrayList;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private SequentialSearchST[] st;

    private int M;  // M is the number of buckets

    public SeparateChainingHashST(int M){
        // take their number of buckets, save it  into my field
        this.M = M;

        // create the array
        st = new SequentialSearchST[M];

        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    public SeparateChainingHashST(){
        this(997);
    }

    private int hash(KeyType key){
        // has functio = they give me a key, I return an int
        return ( key.hashCode() & 0x7fffffff ) % M;
    }

    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key);    //find the bucket number
        //put the key and value into that bucket
        st[index].put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key);
        return (ValueType) st[index].get(key);
    }

    @Override
    public int size() {
            // got hrought each bucket and add up each individual size
        int sum = 0;
        for (int i = 0; i < M; i++){
            sum += st[i].size();
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {
//        ArrayList<KeyType> keyList = new ArrayList<>();
////        for (int i = 0; i < M; i++){
////            keyList.addAll( st[i].keys() );
////            keyList.
////        }
        Queue<KeyType> collector = new Queue<>();
        for (int i = 0; i < M; i++){
            for (Object key : st[i].keys()){
                collector.enqueue((KeyType) key);
            }
        }
        return collector;
    }
}
