package edu.greenriver.sdev333;


import java.security.Key;
import java.util.NoSuchElementException;

/**
 * Binary search (in an ordered array implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class BinarySearchST<KeyType extends Comparable<KeyType>, ValueType> implements OrderedSymbolTable<KeyType, ValueType> {
    private KeyType[] keys;
    private ValueType[] vals;
    private int n;
    private static final int CAPACITY = 10;

    /*
    Initializing an empty symbol table.
     */
    public BinarySearchST()
    {
        this(CAPACITY);
    }
    public BinarySearchST(int capacity)
    {
        keys = (KeyType[])  new Comparable[capacity];
        vals = (ValueType[]) new Comparable[capacity];
    }
    //resize() method
    private void checkSize() {
        if (n == keys.length) {
            //resize up (double up the array size)

            // step 1 - create a new larger array
            KeyType[] temp = (KeyType[]) new Comparable[n * 2];
            ValueType[] tempV = (ValueType[])  new Object[ n* 2];
            // Step 2 = copy items from data to temp
            for (int i = 0; i < n; i++) {
                temp[i] = keys[i];
                tempV[i] = vals[i];
            }

            //Step 3 - repoint/ reference data to point to new array
            keys = temp;
            vals = tempV;

            // Optional:
            //overwrite
            temp = null;
            tempV = null;
        } //end of if (need to resize)
    }// end of method
    @Override
    public void put(KeyType key, ValueType value) {
        int i = rank(key);
        if(i < n && keys[i].compareTo(key) == 0)
        {
            vals[i] = value;
            return;
        }
        if( n == keys.length)
        {
            checkSize();
        }
        for(int j = n; j > i; j --)
        {
            keys[j] = keys[j-1]; vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = value;
        n++;
    }

    @Override
    public ValueType get(KeyType key) {
        if(isEmpty())
        {
            return null;
        }
        int i = rank(key);
        if(i< n && keys[i].compareTo(key) == 0)
        {
            return vals[i];
        }
        else
        {
            return null;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public KeyType min() {
        if (isEmpty())
        {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return keys[0];
    }

    @Override
    public KeyType max() {
        if (isEmpty())
        {
            throw new NoSuchElementException("called max() with empty symbol table");
        }
        return keys[n-1];
    }

    /**
     *  Returns the largest key in this symbol table less than or equal to
     * @param key
     * @return
     */
    @Override
    public KeyType floor(KeyType key) {
        if(key == null)
        {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        int i = rank(key);
        if( i < n && key.compareTo(keys[i]) == 0)
        {
            return keys[i];
        }
        if( i == 0)
        {
            throw new NoSuchElementException("argument to floor() is too small");
        }
        else
        {
            return keys[i -1];
        }

    }

    // returns the smallest key in this map greater than or equal to
    @Override
    public KeyType ceiling(KeyType key) {
        if (key == null)
        {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        int i = rank(key);
        if ( i == n)
        {
            throw new NoSuchElementException("argument to ceiling() is too large");
        }
        else
        {
            return keys[i];
        }
    }

    @Override
    public int rank(KeyType key) {
        int lo = 0;
        int hi = n -1;
        while(lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0)
            {
                hi = mid -1;
            }
            else if(cmp > 0)
            {
                lo = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public KeyType select(int k) {
        if( k < 0 || k > size())
        {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    @Override
    public Iterable<KeyType> keys() {
        return Keys(min(), max());
    }

    public Iterable<KeyType> Keys(KeyType lo, KeyType hi)
    {
        if(lo == null)
        {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if(hi == null)
        {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<KeyType> queue = new Queue<KeyType>();
        if(lo.compareTo(hi) > 0)
        {
            return queue;
        }
        for (int i = rank(lo);i < rank(hi); i ++)
        {
            queue.enqueue(keys[i]);
        }
        if(contains(hi))
        {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }
}
