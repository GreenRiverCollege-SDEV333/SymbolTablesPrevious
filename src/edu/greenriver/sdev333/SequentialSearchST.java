package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    private Node first;
    private int count;
    private class Node {
        private KeyType key;
        private ValueType val;
        private Node next;

        public Node (KeyType key, ValueType val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public void setNext(Node x) {
            this.next = x;
        }
    }

    @Override
    public void put(KeyType key, ValueType val) {
        if(first == null) {
            first = new Node(key, val, null);
        }
        else {
            Node x = first;
            for(; x.next != null; x = x.next) {
                if(key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }
            Node newNode = new Node(key, val, null);
            x.setNext(newNode);
        }
        count++;
    }

    @Override
    public ValueType get(KeyType key) {
        for(Node x = first; x != null; x = x.next) {
            if(key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> queue = new Queue<>();
        Node current = first;
        while (current !=null){
            queue.enQueue(current.key);
            current = current.next;
        }
        return queue;
    }
}
