package org.study.rank.pq;

import edu.princeton.cs.algs4.IndexMaxPQ;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class OrderedArrayMaxPQ <Key extends Comparable<Key>>{

    private Key[] pq;
    private int n;

    public OrderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public Key delMax(){
        return pq[--n];
    }

    public void insert(Key key){
        int i = n - 1;
        while( i >= 0 && less(key, pq[i])){
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = key;
        n++;
    }


    private boolean less(Key i, Key j){
        return i.compareTo(j) < 0;
    }

    private void exchange(int i, int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        OrderedArrayMaxPQ<String> pq = new OrderedArrayMaxPQ<>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }

}
