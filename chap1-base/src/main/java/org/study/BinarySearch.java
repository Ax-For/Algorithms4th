package org.study;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int midVal = a[mid];
            if (midVal < key) lo = mid + 1;
            else if (midVal > key) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(0, 101);
        }
        int key = a[0];
        System.out.println("key = " + key);
        Arrays.sort(a);
        System.out.println("a = " + Arrays.toString(a));
        int rank = rank(key, a);
        System.out.println("rank = " + rank);
    }
}
