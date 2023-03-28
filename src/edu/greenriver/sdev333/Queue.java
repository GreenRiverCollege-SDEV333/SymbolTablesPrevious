package edu.greenriver.sdev333;

import java.util.Iterator;
import java.util.ListIterator;

public class Queue<ItemType> implements Iterable<ItemType> {
    private class Node{
        ItemType data;
        Node next;
    }

    //fields
    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }

    public void enqueue(ItemType item){
        Node oldLast = last;
        last = new Node();
        last.next = null;

        if(isEmpty()){
            last = null;
        }else{
            oldLast.next = last;
        }
        size++;
    }

    public ItemType dequeue(){
        ItemType item = first.data;;
        first = first.next;
        size--;
        if(isEmpty()){
            last = null;
        }
        return item;
    }

    @Override
    public Iterator<ItemType> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<ItemType>{

        private Node current;

        public ListIterator(){
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
