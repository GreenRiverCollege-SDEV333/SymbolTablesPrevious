package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */

public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private int m;
    private SequentialSearchST<KeyType, ValueType>[] st;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int m){
        this.m = m;
        //construct an array of SequentialSearchST objects
        st = (SequentialSearchST<KeyType,ValueType>[]) new SequentialSearchST[m];
        //instantiate new Sequential Search objects for each index in the array
        for(int i = 0; i < m; i++){
            st[i] = new SequentialSearchST();
        }
    }
    public int hash(KeyType key){
        return (key.hashCode() & 0x7fffffff) % m;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        st[hash(key)].put(key,value);
    }

    @Override
    public ValueType get(KeyType key) {
        return (ValueType) st[hash(key)].get(key);
    }

    @Override
    public int size() {
        //iterate through array
            //at each iteration call st[i].size() add to the size counter
        int size = 0;
        for(int i=0; i < st.length; i ++){
            size += st[i].size();
        }
        return size;
    }

    //chatGPT helped me with this method could not find it in the text book.
    @Override
    public Iterable<KeyType> keys() {
        return new Iterable<KeyType>() {
            @Override
            public Iterator<KeyType> iterator() {
                return new Iterator<KeyType>() {
                    private int currentIndex = 0;
                    private Iterator<KeyType> currentIterator = st[currentIndex].keys().iterator();

                    @Override
                    public boolean hasNext() {
                        while (!currentIterator.hasNext() && currentIndex < m - 1) {
                            currentIndex++;
                            currentIterator = st[currentIndex].keys().iterator();
                        }
                        return currentIterator.hasNext();
                    }

                    @Override
                    public KeyType next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        return currentIterator.next();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }






}
