package org.study.rank.quick;

import edu.princeton.cs.algs4.StdRandom;
import org.study.rank.Rank;

import java.util.Arrays;

public class Quick extends Rank {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (low >= high) return;
        int mid = partition(a, low, high);
        sort(a, low, mid - 1);
        sort(a, mid + 1, high);
    }


    private static int partition(Comparable[] a, int low, int high) {
        int i = low, j = high + 1;
        Comparable v = a[low];
        while (true) {
            while (less(a[++i], v)) if (i == high) break;
            while (less(v, a[--j])) if (j == low) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, low);
        return j;
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[N];
//        for (int i = 0; i < N; i++) {
//            a[i] = StdRandom.uniform(0, 100);
//        }
        sort(a);
    }
}
