package edu.greenriver.sdev333;

/**
 * Ordered Symbol Table API, extends the Symbol Table API
 * Refer to p. 366-369 of Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType> the data type (class) of the keys in the symbol table, must be Comparable
 * @param <ValueType> the data type (class) of the values in the symbol table
 */
public interface OrderedSymbolTable <KeyType extends Comparable<KeyType>, ValueType> extends SymbolTable<KeyType, ValueType> {
    void put(KeyType key, ValueType value);
    ValueType get(KeyType key);
    default void delete(KeyType key) {
        // Refer to p. 364 of Sedgewick and Wayne, Algorithms, 4th edition
        put(key, null);
    }
    default boolean contains(KeyType key) {
        return get(key) == null;
    }
    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    KeyType min();
    KeyType max();
    KeyType floor(KeyType key);
    KeyType ceiling(KeyType key);
    int rank(KeyType key);
    KeyType select(int k);
    default void deleteMin() {
        // Refer to p. 368 of Sedgewick and Wayne, Algorithms, 4th edition
        delete(min());
    }
    default void deleteMax() {
        // Refer to p. 368 of Sedgewick and Wayne, Algorithms, 4th edition
        delete(max());
    }
    default int size(KeyType lo, KeyType hi) {
        // Refer to p. 368 of Sedgewick and Wayne, Algorithms, 4th edition
        if (hi.compareTo(lo) < 0) {
            return 0;
        }
        else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        }
        else {
            return rank(hi) - rank(lo);
        }
    }
    default Iterable<KeyType> keys(KeyType lo, KeyType hi) {
        // Refer to p. 368 of Sedgewick and Wayne, Algorithms, 4th edition
        return keys(min(), max());
    }

    Iterable<KeyType> keys();
}
