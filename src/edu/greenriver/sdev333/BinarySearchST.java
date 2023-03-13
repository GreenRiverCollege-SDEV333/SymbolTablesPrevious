//package edu.greenriver.sdev333;
//
//import com.sun.jdi.Value;
//
//import java.security.Key;
//
///**
// * Binary search (in an ordered array implementation) of Symbol Table
// * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
// * @param <KeyType>
// * @param <ValueType>
// */
//public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
//
//    // private fields
//    private Key[] keys;
//    private Value[] vals;
//    private int n;
//
//    // constructor
//    public BinarySearchST( int capacity ) {
//        keys = (Key[]) new Comparable[capacity];
//        vals = (Value[]) new Object[capacity];
//    }
//
//    @Override
//    public void put(KeyType key, ValueType value) {
//        // search for key. Update value if found, grow table if new
//        int i = rank(key);
//        if( i < n && ((KeyType)keys[i]).compareTo(key) == 0) {
//            vals[i] = (Value) value;
//            return;
//        }
//        for ( int j = n; j > i; j-- ) {
//            keys[j] = keys[j - 1];
//            vals[j] = vals[j - 1];
//        }
//        keys[i] = (Key) key;
//        vals[i] = (Value) value;
//        n++;
//    }
//
//    @Override
//    public ValueType get(KeyType key) {
//        if( isEmpty() ) {
//            return null;
//        }
//        // need to finish
//        int i = rank(key);
//        if( i < n && ((KeyType)keys[i]).compareTo(key) == 0) {
//            return (ValueType) vals[i];
//        }
//        else {
//            return null;
//        }
//    }
//
//    @Override
//    public int size() {
//
//        return n;
//    }
//
//    @Override
//    public KeyType min() {
//        return (KeyType) keys[0];
//    }
//
//    @Override
//    public KeyType max() {
//        return (KeyType) keys[n - 1];
//    }
//
//    @Override
//    public KeyType floor(KeyType key) {
//        return null;
//    }
//
//    @Override
//    public KeyType ceiling(KeyType key) {
//        int i = rank(key);
//        return (KeyType) keys[i];
//    }
//
//    @Override
//    public int rank(KeyType key) {
//        int lo = 0;
//        int hi = n - 1;
//        while( lo <= hi ) {
//            int mid = lo + (hi - lo) / 2;
//            int cmp = key.compareTo((KeyType) keys[mid]);
//
//            if( cmp < 0) {
//                hi = mid - 1;
//            }
//            else if (cmp > 0){
//                lo = mid + 1;
//            }
//            else {
//                return mid;
//            }
//        }
//        return lo;
//    }
//
//    @Override
//    public KeyType select(int k) {
//        return (KeyType) keys[k];
//    }
//
//    @Override
//    public Iterable<KeyType> keys( Key lo, Key hi) {
//        Queue<Key> queue = new Queue<Key>();
//
//        for (int i = rank((KeyType) lo); i < rank((KeyType) hi); i++) {
//            queue.enqueue(keys[i]);
//            if( contains((KeyType) hi) ) {
//                queue.enqueue(keys[rank((KeyType) hi)]);
//            }
//        }
//        return queue;
//    }
//}
