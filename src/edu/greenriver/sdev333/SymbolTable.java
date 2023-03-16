package edu.greenriver.sdev333;

/**
 * Symbol Table API
 * Refer to p. 363-365 of Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType> the data type (class) of the keys in the symbol table
 * @param <ValueType> the data type (class) of the values in the symbol table
 */
public interface SymbolTable <KeyType, ValueType> {
    void put(KeyType key, ValueType value);
    ValueType get(KeyType key);
    default void delete(KeyType key) {
        // Refer to p. 364 of Sedgewick and Wayne, Algorithms, 4th edition
        put(key, null);
    }
    default boolean contains(KeyType key) {
        return get(key) != null;
    }
    default boolean isEmpty() {
        return size() == 0;
    }
    int size();
    Iterable<KeyType> keys();
}
