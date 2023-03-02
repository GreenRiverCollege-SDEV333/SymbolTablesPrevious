package edu.greenriver.sdev333;

/**
 * Sequential search (unordered linked list implementation) of Symbol Table
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 * @param <ValueType>
 */
public class SequentialSearchST <KeyType, ValueType> implements SymbolTable<KeyType, ValueType> {

    //Fields
    private Node head;

    //helper/ inner classes
    private class Node{
        KeyType key;
        ValueType value;
        Node next;


    public Node(KeyType key, ValueType value, Node next){
        this.key = key;
        this.value = value;
        this.next = next;
        }

    }
    @Override
    public void put(KeyType key, ValueType value) {
        //Search for key. Update value if found
        for(Node current = head; current != null; current = current.next){
            if(key.equals(current.key)){
                current.value = value;
                return;
            }
            current = new Node(key, value, current);
        }
    }

    @Override
    public ValueType get(KeyType key) {
        //Search for key, return associated value
        for(Node current = head; current != null; current = current.next){
            if(key.equals(current.key)){
                return current.value; //return value when search hit
            }
        }
        return null; //not find
    }

    @Override
    public int size() {
        //Initialize a counter to 0
        int count = 0;
        //increment the counter for each node
        for(Node current = head; current != null; current = current.next){
            count++;
        }
        //return the final count
        return count;
    }

    @Override
    public Iterable<KeyType> keys() {
        return null;
    }
}
