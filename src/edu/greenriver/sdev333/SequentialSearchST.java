package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */

//Generic KeyType and ValueType
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {
    public Node first; //also referred to as the root
    private int size = 0;
    private class Node {
        KeyType key;
        ValueType val;
        Node next;
        public Node(KeyType key, ValueType val, Node next ){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    @Override
    public ValueType get(KeyType key) {

        //start at first/root, while current != null, increment current = current.next
        for (Node current = first; current != null; current = current.next){
            //check if current key is equal to the key that we are passing in
            //then return current value
            if (key.equals(current.key)) {
                return current.val;
            }
        }
        //default return null
        return null;
    }

    @Override
    public void put(KeyType key, ValueType val) {
        //if current(first) is not null then loop through and find the key then change the value
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.val = val;
                return;
            }
        }
        //thia only runs if first is null
        first = new Node(key, val, first);
    }



    @Override
    public int size() {
        for (KeyType s : this.keys()) {
            size++;
        }
        return size;
    }

    @Override
    public Iterable<KeyType> keys() {
        return new Iterable<KeyType>() {
            @Override
            public Iterator<KeyType> iterator() {
                return new SequentialSearchSTKeyIterator(first);
            }
        };
    }


    public class SequentialSearchSTKeyIterator implements Iterator<KeyType> {

        private Node current;

        public SequentialSearchSTKeyIterator(Node first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public KeyType next() {
            //next is null/ doesn't exist
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            KeyType key = current.key;
            current = current.next;
            return key;
        }
    }
}
