package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    //fields
    //array of linked lists -  but we wrote a linked list in sequential search st
    //        VV this is the linked list VV
    private SequentialSearchST<KeyType,ValueType>[] st;
    private int M; //

    public SeparateChainingHashST(){
        this(997);
    }
    public SeparateChainingHashST(int M){
        //take their number of buckets, save it into my field
      this.M = M;

      // create the array
        st = new SequentialSearchST[M];

        // for each position in the array(for each bucket)
        //create a linked list(sequentialsearchst) in each bucket

        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(KeyType key){
        //hash function = they give me a key, i return an int(bucket #  = array index)
        return (key.hashCode() & 0x7fffffff) % M ; // this modulus function will give us a number between 0 and M.
    }




    @Override
    public void put(KeyType key, ValueType value) {
    int index = hash(key);  ///find the bucket number
        // put the ket and value into that bucket
        st[index].put(key,value);
    }

    @Override
    public ValueType get(KeyType key) {
        int index = hash(key); ///find the bucket number
        // go into tha tbucket and see if its there
       return st[index].get(key);

    }

    @Override
    public int size() {
        // go through each bucket and couhnt up the size
        int sum = 0;
        for(int i = 0; i < M ; i++){
            sum += st[i].size();
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector =  new Queue<>();
        for (int i = 0; i < M; i++) {
            for (KeyType key : st[i].keys()) {
                collector.enqueue(key);
            }
        }
        return collector;
    }
}
