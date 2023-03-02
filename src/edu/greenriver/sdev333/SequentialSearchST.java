package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    //fields
    private Node first;
    private int size;

    private class Node{//linked-list Node
        KeyType key;
        ValueType val;
        Node next;
        public Node(KeyType key, ValueType val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(KeyType key, ValueType value) {
        //search for key, return associated value using for loop
        for (Node current = first; current != null;current = current.next){
            //search if key equal to current.key
            if (key.equals(current.key)){
                current.val = value; //update matched key to value
                return;
            }
        }
        //if search found no same key, add new node
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public ValueType get(KeyType key) {
        //search for key, return associated value using for loop
        for (Node current = first; current != null;current = current.next){
            //if search found
            if (key.equals(current.key)){
                return current.val;
            }
        }
        //if not found
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        // Create a new queue to hold the keys
        Queue<KeyType> queue = new Queue<>();

        // Iterate over the queue and add each key to the queue
        for (Node current = first; current != null; current = current.next) {
            queue.enqueue(current.key);
        }

        // Return the keys in the queue as an iterable
        return queue;
    }
}
