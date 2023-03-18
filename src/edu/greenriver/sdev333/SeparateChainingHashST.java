package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    // fields:
    // we need an array of linked lists- but we already wrote a linked list in SequentialSearchST
    // SequentialSearchST<KeyType, ValueType> this is the linked list, [] says we want to have an array of linked lists
    // and st is the variable name
    private SequentialSearchST<KeyType, ValueType>[] st;
    private int M; // M represents the number of buckets/piles
    //when someone makes a new hash table they tell us how many buckets they want (M)

    //add a constructor
    public SeparateChainingHashST(int M) {
        //take their number of buckets, save it into my field
        this.M = M;

        //create the array
        st = new SequentialSearchST[M];

        //for each position in the array (for each bucket)
        // create a linked list (sequentialSearchst) in each bucket
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }

    }

    private int hash(KeyType key) {
        // hash function = they give me a key, I return an int (the bucket number) we can think of the bucket number as the arrya index
        return (key.hashCode() & 0x7fffffff) % M; //moding by M gives us a number between 0 and M

    }


    @Override
    public void put(KeyType key, ValueType value) {
        int index = hash(key);  //find the bucket number
        //put the key and value into that bucket
        st[index].put(key,value);

        //so if someone gives you the key and the value, go find me the hash number for the key
        // thats going to be the index for the array,
        // then go into the array at that position and put that key and value
        // into that bucket

    }

    //if someone says im going to give you a key, go find it for me
    @Override
    public ValueType get(KeyType key) {
        //figure out what bucket im in
        int index = hash(key); //find the bucket number
        //go into the bucket and see if its there
        return st[index].get(key); //if its in there we get our value back, if its not in there null returns back
    }

    //if someone says get me the size/number of items in our hash system
    @Override
    public int size() {
        //we need to go into each bucket and find the number of items in each bucket
        // we need to go through each bucket and add up each individual size
        int sum = 0;
        for (int i = 0; i < M; i++) {
            sum += st[i].size; // add each bucket size to the sum
        }
        return sum;
    }

    @Override
    public Iterable<KeyType> keys() {

        return null;
    }
}
