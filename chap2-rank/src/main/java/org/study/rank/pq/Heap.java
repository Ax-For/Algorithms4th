package org.study.rank.pq;

import edu.princeton.cs.algs4.Inversions;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.study.rank.Rank;

import java.util.Arrays;

public class Heap
//        extends Rank
{

//    public static void sort(Comparable[] a) {
//        int N = a.length - 1;
//        // 堆生成
//        for (int i = N / 2; i > 0; i--) {
//            sink(a, i, N);
//        }
//        // 下沉排序
//        while (N > 1) {
//            exch(a, 1, N--);
//            sink(a, 1, N);
//        }
//    }
//
//    public static void sink(Comparable[] a, int k, int n) {
//        while (2 * k <= n) {
//            int j = 2 * k;
//            if (j < n && less(a[j], a[j + 1])) j++;
//            if (!less(a[k], a[j])) break;
//            exch(a, k, j);
//            k = j;
//        }
//    }
//
//    public static void main(String[] args) {
//        Integer[] num = new Integer[]{null, 4, 1, 9, 3, 6, 5, 2, 7};
//        System.out.println(Arrays.toString(num));
//
//        sort(num);
//        System.out.println(Arrays.toString(num));
//    }

    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = "S O R T E X A M P L E !".split(" ");
        System.out.println(Arrays.toString(a));
        Heap.sort(a);
        show(a);
//        Inversions
    }

}
