package edu.greenriver.sdev333;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    private KeyType [] keys;
    private ValueType [] values;
    private int n;

    public BinarySearchST(int capacity){
        keys = (KeyType[]) new Comparable[capacity];
        values = (ValueType[]) new Comparable[capacity];
    }
    @Override
    public void put(KeyType key, ValueType value) {
        int i = rank(key);
        if(i<n && keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }
        for(int j = n; j > i; j--){
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }


    @Override
    public ValueType get(KeyType key) {
        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0){
            return values[i];
        }
        else{
            return null;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public KeyType min() {
        return keys[0];
    }

    @Override
    public KeyType max() {
        return keys[n-1];
    }

    @Override
    public KeyType floor(KeyType key) {
        return null;
    }

    @Override
    public KeyType ceiling(KeyType key) {
        int i = rank(key);
        return keys[i];
    }

    @Override
    public int rank(KeyType key){
        int low = 0;
        int hi = n-1;
        while(low <= hi){
            int mid = low + (hi - low)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0){
                hi = mid - 1;
            }
            else if(cmp > 0){
                low = mid + 1;
            }
            else return mid;
        }
        return low;
    }

    @Override
    public KeyType select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue queue = new Queue<KeyType>();
        for(int i = 0; i<n; i++){
            queue.enqueue(keys[i]);
        }
        return queue;
    }
    @Override
    public Iterable<KeyType> keys(KeyType low, KeyType hi) {
        Queue queue = new Queue<KeyType>();
        for(int i = rank(low); i< rank(hi); i++){
            queue.enqueue(keys[i]);
        }
        if(contains(hi)){
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }
}
