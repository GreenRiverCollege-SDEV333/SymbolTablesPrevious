package edu.greenriver.sdev333;

import java.util.Iterator;

/**
 * FIFO queue, page 151 of the red book
 */
public class Queue<ItemType> implements Iterable<ItemType>{
    private Node first; // link to the least recently added node
    private Node last; // link to most recently added node
    private int size; // number of items on the queue


    private class Node {
        // nested class to define nodes
        ItemType data;
        Node next;
    }

    public boolean isEmpty() { return first == null;}
    public int size() {return size;}

    public void enqueue(ItemType item) {
        // add item to the end of the list
        Node oldLast = last;
        last = new Node();
        last.data = item;
        last.next = null;

        if (isEmpty()) {
            last = null;
        } else {
            oldLast.next = last;
        }

        size++;
    }

    public void dequeue() {
        ItemType item = first.data;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
    }

    /**
     * return an iterator over elements to type
     * @return an Iterator
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements  Iterator<ItemType> {
        private Node current;

        public ListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {

            return current != null;
        }

        @Override
        public ItemType next() {
            ItemType item = current.data;
            current = current.next;
            return item;
        }
    }
}
