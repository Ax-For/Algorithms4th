package org.study.search.st;


import edu.princeton.cs.algs4.Queue;
import org.omg.CORBA.Object;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int n = 0;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(Key k, Value v) {
        if(k == null) throw new IllegalArgumentException("argument to get() is null");
        if(v == null) {
            delete(k);
            return;
        }
        int i = rank(k);
        if(i < n && keys[i].compareTo(k) == 0){
            values[i] = v;
            return;
        }

        if (n == keys.length) resize(2 * keys.length);

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = k;
        values[i] = v;

        n++;
    }

    public Value get(Key k) {
        if (k == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;
        int i = rank(k);
        if(i < n && k.compareTo(keys[i]) == 0) return values[i];
        return null;
    }

    public void delete(Key k) {
        if (k == null) throw new IllegalArgumentException("argument to delete() is null");
        if(isEmpty()) return;

        int i = rank(k);

        if (i == n || keys[i].compareTo(k) != 0){
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        n--;
        keys[n] = null;
        values[n] = null;
        if(n > 0 && n == keys.length / 4) resize(keys.length / 2);
    }

    public boolean contains(Key k) {
        if (k == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(k) != null;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n - 1];
    }

    public Key floor(Key k) {
        if (k == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(k);
        if (i < n && keys[i].compareTo(k) == 0) return keys[i];
        if (i == 0) throw new NoSuchElementException("argument to floor() is too small");
        return keys[i - 1];
    }

    public Key ceiling(Key k) {
        if (k == null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i = rank(k);
        if (i == n) throw new NoSuchElementException("argument to ceiling() is too large");
        return keys[i];
    }

    public int rank(Key k) {
        if (k == null) throw new IllegalArgumentException("argument to rank() is null");
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (keys[mid].compareTo(k) < 0) {
                low = mid + 1;
            } else if (keys[mid].compareTo(k) > 0) {
                high = mid - 1;
            } else
                return mid;
        }
        return low;
    }

    public Key select(int i) {
        if(i < 0 || i >= size()) throw new IllegalArgumentException("called select with invalid argument");
        return keys[i];
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("symbol table underflow error");
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("symbol table underflow error");
        delete(max());
    }

    public int size(Key low, Key high) {
        if (low == null || high == null) throw new IllegalArgumentException("argument to rank() is null");
        if (low.compareTo(high) > 0) return 0;
        int i = rank(low);
        int j = rank(high);
        if (contains(high)) return j - i + 1;
        return j - i;
    }

    public Iterable<Key> keys(Key low, Key high) {
        if (low == null || high == null) throw new IllegalArgumentException("argument to rank() is null");

        Queue<Key> queue = new Queue<>();
        if (low.compareTo(high) > 0) return queue;
        for (int i = rank(low); i < rank(high); i++)
            queue.enqueue(keys[i]);
        if (contains(high)) queue.enqueue(keys[rank(high)]);
        return queue;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private void resize(int capacity){
        if (capacity <= n) return;
        Key[] tempK = (Key[]) new Comparable[capacity];
        Value[] tempV = (Value[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }
        keys = tempK;
        values = tempV;
    }


}
