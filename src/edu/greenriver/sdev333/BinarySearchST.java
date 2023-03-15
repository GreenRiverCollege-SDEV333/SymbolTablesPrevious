package edu.greenriver.sdev333;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    private KeyType[] keys;
    private ValueType[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (KeyType[])new Comparable[capacity];
        vals = (ValueType[])new Object[capacity];
    }

    @Override
    public void put(KeyType key, ValueType value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        N++;           
    }



    @Override
    public ValueType get(KeyType key) {
        //System.out.println("running get...");
        if(keys.isEmpty()) return null;
        //System.out.println("not empty?");
        int i = rank(key);
        //System.out.println("rank is: " + i +"  Size is: " + keys.size());
        if (i < keys.size() + 1 && keys.get(i).compareTo(key) == 0) {
            return vals.get(i);
        }else{
            //System.out.println("else returns null here");
            return null;
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public KeyType min() {
        return keys.get(0);
    }

    @Override
    public KeyType max() {
        return keys.get(keys.size() - 1);
        //return keys[size-1];
    }

    /** ???????????
     *
     * @param key
     * @return
     */
    @Override
    public KeyType floor(KeyType key) {
        int i = rank(key);
        if(i == 0){
            //return keys[0];
            return keys.get(0);
        }else if(i < keys.size()){
        //return keys[i-1];
            return keys.get(i-1);
        }else{
            return null;
        }
    }

    /** ???????????  Shouldn't this be the next one after the key???
     *
     * @param key
     * @return
     */
    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key);
        //return keys[i];
        return keys.get(i);
    }

    @Override
    public int rank(KeyType key) {
        int lo = 0;
        int hi = keys.size() - 1;
        while(lo <= hi){
            int mid = lo + (hi -lo) / 2;
            int cmp = key.compareTo(keys.get(mid));
            if(cmp < 0){
                hi = mid - 1;
            }else if(cmp > 0){
                lo = mid + 1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    @Override
    public KeyType select(int k) {
        return keys.get(k);
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();

        for (int i = 0; i < N; i++) {
            queue.enqueue(keys[i]);
        }

        return queue;
    }

    public boolean contains(KeyType key){
        return keys.contains(key);
    }
}