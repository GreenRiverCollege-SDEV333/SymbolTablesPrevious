package edu.greenriver.sdev333;

import java.util.ArrayList;

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
        for(Node currentNode = first; currentNode != null; currentNode = currentNode.next){
            if(key.equals(currentNode.key)){
                currentNode.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public ValueType get(KeyType key) {
        for(Node currentNode = first; currentNode != null; currentNode = currentNode.next){
            if(key.equals(currentNode.key)){
                return currentNode.value;
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
        ArrayList<KeyType> keyList = new ArrayList<>();
        for(Node currentNode = first; currentNode != null; currentNode = currentNode.next){
            keyList.add(currentNode.key);
        }
        return keyList;
    }
}
