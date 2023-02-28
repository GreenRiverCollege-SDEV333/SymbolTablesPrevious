package edu.greenriver.sdev333;

import org.w3c.dom.Node;

import java.util.Iterator;

/**Page 151
 *
 * @param <ItemType>
 */
public class Queue<ItemType> implements Iterable<ItemType>{

    private Node first;
    private Node last;
    private int size;
    private class Node{
        ItemType data;
        Node next;
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
            last = null;
        }else{
            oldlast.next = last;
        }
    }

    public ItemType dequeue(){
        ItemType item = first.data;
        first = first.next;
        size--;
        if(isEmpty()){
            last = null;
        }
        return item;
    }

    private class ListIterator implements Iterator<ItemType>{
        private Queue.Node current;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public ItemType next() {
            return null;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }

    @Override
    public Iterator<ItemType> iterator() {
        return new ListIterator();
    }
}
