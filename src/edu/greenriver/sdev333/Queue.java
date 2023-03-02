package edu.greenriver.sdev333;

import java.util.Iterator;

/**
 * FIFO queue, first in first out linked list
 * implementing the queue as a linked list
 */
//Queue will take in items of any type
public class Queue<ItemType> implements Iterable<ItemType> {
    //Need a node in a linked list
    private class Node {
        ItemType data;

        Node next;
    }

    //fields for the class we need to...
    //have a head variable to refer to the first node
    //have a tail variable to refer to the very last node of the list
    //head and tail aka first and last
    //we are used to seeing it as head and tail
    private Node first;
    private Node last;
    private int size; //counter that keeps track of how many nodes are in the list

    public boolean isEmpty() {
        //list is empty when first is null
        return first == null;
    }

    public int size(){
        return size;
    }

    //keeping track of the last node
    public void enqueue(ItemType item){
       Node oldlast = last; //variable oldlast to keep track of the last node
       last = new Node(); // last becomes a new node
       last.data = item; //fill in its data
       last.next = null; //fill in its next

       //if list is empty first is last
        if (isEmpty()) {
           first = last;
       }
       //otherwise the oldlast.next will be the new last
        else {
           oldlast.next = last;
       }

       size++;
    }

    public ItemType dequeue() {
        ItemType item = first.data;
        first = first.next;
        size--;
        if (isEmpty()){
            last = null;
        }
        return item;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new ListIterator();
    }

    //helper class
    public class ListIterator implements Iterator<ItemType> {

        //variable that keeps track of my place
        private Node current;

        public ListIterator() {
            current = first; //set current to the front of the list
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            //check to see if current is not equal to null
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public ItemType next() {
            //grab the current item
            ItemType item = current.data;
            //move current forward
            current = current.next;
            //return item
            return item;
        }
    }
}
