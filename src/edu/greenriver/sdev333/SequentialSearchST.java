package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    // private field
    private Node first;

    // private helper class
    private class Node {
        // linked list node
        KeyType key;
        ValueType val;
        Node next;
        public Node(KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {

        for(Node x = first; x != null; x = x.next ){
            if(key.equals(x.key)){
                x.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
    }

    /**
     * Gets the value of the key passed as a parameter
     * @param key
     * @return the associated value of the given key
     */
    @Override
    public ValueType get(KeyType key) {

        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)){
                return x.val;
            }
        }
        return null;
    }

    @Override
    public int size() {

        return 0;
    }

    @Override
    public Iterable<KeyType> keys() {

        Queue<KeyType> keyQueue = new Queue<>();

        Node current = first;
        while(current != null) {
            keyQueue.enqueue(current.key);
            current = current.next;
        }
        return keyQueue;
    }
}
