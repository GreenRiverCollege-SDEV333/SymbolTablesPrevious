package edu.greenriver.sdev333;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * FIFO queue, page 151 of the red book
 */
public class Queue<ItemType> implements Iterable<ItemType> {
    private class Node {
        ItemType data;
        Node next;
    }

    // Fields for the class
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public void enqueue(ItemType item) {
        Node oldLast = last;
        last = new Node();
        last.data = item;
        last.next = null;

        if (isEmpty()){
            last = null;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public ItemType dequeue(){
        ItemType item = first.data;
        first = first.next;
        size--;
        if (isEmpty()){
            last = null;
        }
        return null; //change this
    }
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ItemType> iterator() {
        return (Iterator<ItemType>) new ListIterator();
    }

    private class ListIterator implements Iterator<ItemType>
    {

        private Node current;

        public ListIterator(){
            current = first;
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
            ItemType item = current.data;
            current = current.next;
            return item;
        }
    }}
