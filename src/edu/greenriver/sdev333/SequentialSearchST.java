package edu.greenriver.sdev333;



/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {



    private Node first;
    private int size = 0;
    private class Node
    {   //Linked-list node
        private KeyType key;
        private ValueType val;
        private Node next;
        public Node(KeyType key, ValueType val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }
    @Override
    public void put(KeyType key, ValueType value) {
        //Search for key, Update value if found; grow table if new
        for(Node x = first; x != null; x = x.next)
        {
            if(key.equals(x.key))
            {
                x.val = value; // Search hit: update val.
                return;
            }
        }
        first = new Node(key,value,first); //Search miss: add new node.
        size++;
    }

    @Override
    public ValueType get(KeyType key) {
        //Search for key, return associated value.
        for(Node x = first; x != null; x = x.next)
        {
            if(key.equals(x.key))
            {
                return x.val; // search hit
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
        Node current = first;
        while(current != null)
        {
            queue.enqueue(current.key);
            current = current.next;
        }

        return queue;
    }
}
