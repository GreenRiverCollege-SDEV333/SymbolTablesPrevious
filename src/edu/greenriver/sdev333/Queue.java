package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

public class Queue<ItemType> implements Iterable<ItemType> {
    private Node first;
    private Node last;
    private int size;

    private class Node{
        private ItemType data;
        private Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public void enqueue(ItemType item){
        Node oldlast = last;
        last = new Node();
        last.data = item;
        last.next = null;

        if(isEmpty()){
            first = last;
        }
        else{
            oldlast.next = last;
        }
        size++;
    }
    public ItemType dequeue(){
        ItemType item = first.data;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        return item;
    }

    @Override
    public Iterator<ItemType> iterator(){
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<ItemType>{

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        private Node current;

        public LinkedListIterator(){
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws //NoSuchElementException if the iteration has no more elements
         */
        @Override
        public ItemType next() {
            ItemType item = current.data;
            current = current.next;
            return item;
        }
    }
}
