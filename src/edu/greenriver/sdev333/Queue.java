package edu.greenriver.sdev333;

import java.util.Iterator;

/**
 * FIFO queue, page 151 of the red book
 */
public class Queue<ItemType> implements Iterable<ItemType> {
    // private helper node class:
    private class Node {
        private ItemType data;
        private Node next;
    }

    // fields:
    private Node first;
    private Node last;
    private int size;

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(ItemType item) {
        Node oldlast = last;
        last = new Node();
        last.data = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }

        size++;
    }

    public ItemType dequeue() {
        ItemType item = first.data;
        first = first.next;
        size--;
        if (isEmpty()) {
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
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<ItemType> {
        private Node current;

        public LinkedListIterator() {
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