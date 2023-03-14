package edu.greenriver.sdev333;



/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    //fields:
    //array of linkedLists
    private SequentialSearchST<KeyType, ValueType> [] st;
    //M = number of buckets
    private int M;

    public SeparateChainingHashST(){
        this(997);

    }

    public SeparateChainingHashST(int M){
        this.M = M;
        //create array
        st = new SequentialSearchST[M];
        //create linkedList for each position in array
        for(int i = 0; i<M; i++){
            st[i] = new SequentialSearchST<>();
        }

    }
    private int hash(KeyType key){
        //return int from key provided to get array index to put item in/get item from
        return (key.hashCode() & 0x7fffffff) % M;
    }
    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key);
        //put key/value into bucket
        st[index].put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key);
        return st[index].get(key);
    }

    @Override
    public int size() {
        int size = 0;
        for(int i=0; i < M; i++){
            size += st[i].size();
        }
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();
        for(int i =0; i<M; i++){
            for (KeyType key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }
}
