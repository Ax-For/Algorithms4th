package org.study.utils;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyBag<E> implements Iterable<E> {

    // 首指针
    private Node<E> first;
    // 大小
    private int size;

    private static class Node<E>{
        // 当前元素
        private E e;
        // 下一元素的指针
        private Node<E> next;
    }

    private class LinkedBag implements Iterator<E>{

        private Node<E> current;

        public LinkedBag(Node<E> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            E e = current.e;
            current= current.next;
            return e;
        }
    }

    public MyBag() {
        first = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void add(E e)
    {
        Node<E> oldFirst = first;
        first = new Node<>();
        first.e = e;
        first.next = oldFirst;
        size++;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedBag(first);
    }

    public static void main(String[] args) {
        MyBag<String> bag = new MyBag<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
