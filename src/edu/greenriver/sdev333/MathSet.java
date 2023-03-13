package edu.greenriver.sdev333;

/**
 * A MathSet represents a finite mathematical set.
 * Sets have a collection of unique elements (keys) - no duplicate keys allowed.
 * Set operations include contains, size, union, intersection, and difference.
 * Set complement and element (key) deletion is not supported by this API.
 * @param <KeyType>
 */
public interface MathSet<KeyType> {
    /**
     * Puts the specified key into the set.
     * @param key key to be added into the set
     */
    void add(KeyType key);

    /**
     * Is the key in the set?
     * @param key key to check
     * @return true if key is in the set, false otherwise
     */
    boolean contains(KeyType key);

    /**
     * Is the set empty?
     * @return true if the set is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Number of keys in the set
     * @return number of keys in the set.
     */
    int size();

    /**
     * Determine the union of this set with another specified set.
     * Returns A union B, where A is this set, B is other set.
     * A union B = {key | A.contains(key) OR B.contains(key)}.
     * Does not change the contents of this set or the other set.
     * @param other specified set to union
     * @return the union of this set with other
     */
    MathSet<KeyType> union(MathSet<KeyType> other);

    /**
     * Determine the intersection of this set with another specified set.
     * Returns A intersect B, where A is this set, B is other set.
     * A intersect B = {key | A.contains(key) AND B.contains(key)}.
     * Does not change the contents of this set or the other set.
     * @param other specified set to intersect
     * @return the intersection of this set with other
     */
    MathSet<KeyType> intersection(MathSet<KeyType> other);

    /**
     * Determine the difference of this set with another specified set.
     * Returns A difference B, where A is this set, B is other set.
     * A difference B = {key | A.contains(key) AND !B.contains(key)}.
     * Does not change the contents of this set or the other set.
     * @param other specified set to difference
     * @return the difference of this set with other
     */
    MathSet<KeyType> difference(MathSet<KeyType> other);

    /**
     * Retrieves a collection of all the keys in this set.
     * @return a collection of all keys in this set
     */
    Iterable<KeyType> keys();
}
