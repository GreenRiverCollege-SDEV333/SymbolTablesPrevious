package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    private Node first;             // first node in the linked list

    private class Node{
        // linked-list node
        KeyType key;
        ValueType val;
        Node next;

        public Node(KeyType key, ValueType val, Node next){
            this.key = key;
            this.val= val;
            this.next = next;
        }
    }

    @Override
    public void put(KeyType key, ValueType val) {
        // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;                             // Search hit: update val
            }
        }

        first = new Node(key, val, first);      // Search miss: add new node
    }

    @Override
    public ValueType get(KeyType key) {
        // Search for key, return associated value
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)){
                return x.val;       // search hit
            }
        }
        return null;               // search miss
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<KeyType> keys() {
        //create a new empty queue to hold my results
        Queue<KeyType> queue = new Queue<>();

        Node current = first;
        //if is not empty
        while (current != null) {
            //added to queue
            queue.enqueue(current.key);
            //sends to next
            current = current.next;
        }

        return queue;
    }
}
