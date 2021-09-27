package org.study.rank;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;


public class Rank {

    public static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] a, int i, int j)
    {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a)
    {
        System.out.println(Arrays.toString(a));
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i], a[i-1])) return false;
        }
        return true;
    }

}
