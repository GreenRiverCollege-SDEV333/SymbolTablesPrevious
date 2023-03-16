package edu.greenriver.sdev333;

/**
 * Hash Table (separate chaining implementation) of Symbol Table
 * Refer to p. 458-468 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
import java.util.ArrayList;

public class SeparateChainingHashST<KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private int size; // number of key-value pairs
    private int capacity; // hash table size
    private ArrayList<Node<KeyType, ValueType>>[] st; // array of chains

    private static class Node<KeyType, ValueType> {
        private final KeyType key;
        private ValueType value;
        private Node<KeyType, ValueType> next;

        public Node(KeyType key, ValueType value, Node<KeyType, ValueType> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // constructor
    public SeparateChainingHashST(int capacity) {
        this.capacity = capacity;
        st = (ArrayList<Node<KeyType, ValueType>>[]) new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            st[i] = new ArrayList<Node<KeyType, ValueType>>();
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (value == null) {
            delete(key);
            return;
        }

        int i = hash(key);
        for (Node<KeyType, ValueType> node : st[i]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        st[i].add(new Node<KeyType, ValueType>(key, value, null));
        size++;
    }

    @Override
    public ValueType get(KeyType key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int i = hash(key);
        for (Node<KeyType, ValueType> node : st[i]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void delete(KeyType key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int i = hash(key);
        Node<KeyType, ValueType> prev = null;
        for (Node<KeyType, ValueType> node : st[i]) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    st[i].remove(node);
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        ArrayList<KeyType> list = new ArrayList<KeyType>();
        for (int i = 0; i < capacity; i++) {
            for (Node<KeyType, ValueType> node : st[i]) {
                list.add(node.key);
            }
        }
        return list;
    }

    // hash function
    private int hash(KeyType key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
}
