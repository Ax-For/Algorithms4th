package org.study.utils;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

// FIFO 队列
public class MyQueue<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int size;

    public MyQueue() {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size(){
        return size;
    }

    // 入队列 【尾插法】
    public void enqueue(T item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    // 出队列 【首出法】
    public T dequeue()
    {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        else{
            T item = first.item;
            first = first.next;
            size--;
            if (isEmpty()) last = null;
            return item;
        }
    }

    // 查看队首元素
    public T peek()
    {
        if(isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    private class LinkedIterator implements Iterator<T>
    {

        private Node current;

        public LinkedIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current == null;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator(first);
    }

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else if (!queue.isEmpty())
                StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }



}
