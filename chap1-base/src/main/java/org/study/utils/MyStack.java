package org.study.utils;

import com.sun.istack.internal.NotNull;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<E> implements Iterable<E>{

    private Node first;
    private int n;

    public MyStack() {
        first = null;
        n = 0;
    }

    private class Node
    {
        private E item;
        private Node next;
    }

    private class LinkedIterator implements Iterator<E>
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
        public E next() {
            if(!hasNext()) throw new NoSuchElementException();
            E item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        return n;
    }

    public void push(E item)
    {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public E pop()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        E item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public E peek()
    {
        if(isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator(first);
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
