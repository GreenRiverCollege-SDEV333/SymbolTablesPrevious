import edu.greenriver.sdev333.SymbolTable;

import java.util.HashMap;

/**
 * HashMapWrapper is a class that takes the java.util.HashMap and presents
 * a public interface that matches the SymbolTable interface (API) from the
 * Sedgewick and Wayne, Algorithms book.
 * @param <KeyType> data type (class) of the keys (do not need to be Comparable)
 * @param <ValueType> data type (class) of the values
 */
public class HashMapWrapper<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private HashMap<KeyType, ValueType> map;

    public HashMapWrapper() {
        map = new HashMap<>();
    }

    @Override
    public void put(KeyType key, ValueType value) {
        map.put(key, value);
    }

    @Override
    public ValueType get(KeyType key) {
        return map.get(key);
    }

    @Override
    public void delete(KeyType key) {
        map.remove(key);
    }

    @Override
    public boolean contains(KeyType key) {
        return map.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Iterable<KeyType> keys() {
        return map.keySet();
    }
}
