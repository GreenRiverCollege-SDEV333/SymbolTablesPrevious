package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType>
{
    private Node first;
    private int size = 0;
    private class Node
    {
        private KeyType key;
        private ValueType value;
        private Node next;
        public Node(KeyType key, ValueType value, Node next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    @Override
    public void put(KeyType key, ValueType value) {
        for(Node current = first; current != null; current = current.next){
            if(key.equals(current.key)){
                current.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;

    }

    @Override
    public ValueType get(KeyType key) {
        for(Node current = first; current != null; current = current.next){
            if(key.equals(current.key)){
                return current.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();
        for(Node current = first; current != null; current = current.next){
            queue.enqueue(current.key);

        }
        return queue;
    }
}
