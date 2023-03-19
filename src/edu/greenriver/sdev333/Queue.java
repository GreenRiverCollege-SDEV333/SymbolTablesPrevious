package edu.greenriver.sdev333;

import java.util.Iterator;

public class Queue<ItemType> implements Iterable<ItemType> {
    // FIFO Queue page 151
    private class Node{
        ItemType data;
        Node next;
    }

    private Node first;
    private Node last;
    private int size;

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
            last = null;
        } else {
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

    public Iterator<ItemType> iterator() {
        return null;
    }

    private class ListIterator implements Iterator<ItemType> {
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
