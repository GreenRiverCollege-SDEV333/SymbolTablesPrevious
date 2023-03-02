//public class SeparateChainingHashST<Key, Value>{
//
//    private int m;
//    private SequentialSearchST<Key, Value>[] st;
//
//    public SeparateChainingHashST(){
//        this(997);
//    }
//
//    public SeparateChainingHashST(int m){
//        // Create m linked lists
//        this.m = m;
//        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
//        for (int i = 0; i < m; i++) {
//            st[i] = new SequentialSearchST<>();
//        }
//    }
//
//    private int hash(Key key){
//        return (key.hashCode() & 0x7fffffff) % m;
//    }
//
//    public Value get(Key key){
//        return (Value) st[hash(key)].get(key);
//    }
//
//    public void put(Key key, Value val){
//        st[hash(key)].put(key, val);
//    }
//
//    public Iterable<Key> keys(){
//        // See Exercise 3.4.19
//        return null;
//    }
//
//}
