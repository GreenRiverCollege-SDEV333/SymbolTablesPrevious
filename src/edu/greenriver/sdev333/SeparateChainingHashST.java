package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    //fields:
    //array of linked lists - but we wrote a linked list in SeparateChainingHashST
    private SequentialSearchST<KeyType, ValueType>[] st;
    private int M; //M is the number of buckets (or piles)

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int M){
        //take their number of buckets, save it into my field
        this.M = M;

        //create the array
        st = new SequentialSearchST[M];

        //for each position in the array(for each bucket)
        //create a linked list (SequentialSearchST) in each bucket
        for(int i = 0; i < M; i++){
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(KeyType key){
        //hash function = they give me a key, I return an int (bucket # , array index)
        return (keys().hashCode() & 0x7fffffff) % M;
    }



    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key); //find the bucket number;
        //put the key and value into that bucket
        st[index].put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key); //find the bucket number
        //go into that bucket and see if it is there
        return st[index].get(key);
    }

    @Override
    public int size() {
        //go through each bucket and add up each individual size
        int sum = 0;
        for(int i = 0; i < M; i++){
            sum+= st[i].size();
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        //for every bucket
        for(int i = 0; i < M; i++){
            //take each key in that bucket and add it into the collector
            for(KeyType key : st[i].keys()){
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
