package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.List;

/**
 * Taken from Algorithms book, pg 150 and 155 (Iterator).
 *
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }

        N++;
    }

    // remove item from beginning of list
    public Item dequeue() {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            N--;
            if(isEmpty()) {
                last = null;
            }
            return item;
        } else {
            return null;
        }
    }

    private class ListIterator implements Iterator<Item> {

        private Node x = first;

        @Override
        public boolean hasNext() {
            return x != null;
        }

        @Override
        public Item next() {
            Item item = x.item;
            x = x.next;
            return item;
        }
    }
}