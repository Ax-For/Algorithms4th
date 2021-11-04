package org.study.rank.pq;

import edu.princeton.cs.algs4.StdOut;

public class UnorderedArrayMaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;

    public UnorderedArrayMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }


    public void insert(Key key){
        pq[n++] = key;
    }

    public Key delMax(){
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (less(max, i)) max = i;
        }
        exchange(max, n - 1);
        return pq[--n];
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        UnorderedArrayMaxPQ<String> pq = new UnorderedArrayMaxPQ<>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            StdOut.println(pq.delMax());
    }
}
