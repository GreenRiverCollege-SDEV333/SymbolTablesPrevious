import java.util.Queue;

public class BinarySearchST <Key extends Comparable<Key>, Value>{
    private Key[] keys;
    private Value[] vals;
    private int n;

    public BinarySearchST(int capacity){
        // See Algorithm 1.1 for standard array-resizing code
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return n;
    }

    public Value get(Key key){
//        if (isEmpty()){
//            return null;
//        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0 ){
            return vals[i];
        }
        else{
            return null;
        }
    }

    public int rank(Key key){
        //See page 381
        int lo = 0, hi = n-1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0){
                hi = mid - 1;
            }
            else if (cmp > 0){
                lo = mid + 1;
            }
            else{
                return mid;
            }
        }
        return lo;
    }

    public void put (Key key, Value val){
        //Search for key. Update value if found; grow table if new.
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }

        for (int j = n; j < i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public void delete(Key key){
        //See Exercise 3.1.16 for this method
    }

    public Key min(){
        return keys[0];
    }

    public Key max(){
        return keys[n-1];
    }

    public Key select(int k){
        return keys[k];
    }

    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }

    public Key floor(Key key){
        //See exercise 3.1.17
        return key;
    }

//    public Key delete(Key key){
//        //See exercise 3.1.16
//        return key;
//    }

//    public Iterable<Key> keys(Key lo, Key hi){
//        Queue<Key> queue = new Queue<Key>();
//
//        for {int i = rank(lo); i < rank(hi); i++){
//            queue.enqueue(keys[i]);
//        }
//        if(contains(hi)){
//            queue.enqueue(keys[rank(hi)]);
//        }
//        return queue;
//        }
//    }

}
