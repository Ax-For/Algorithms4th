package org.study.rank.quick;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickPair {

    public static void sort(int[] a, int[] b){
        StdRandom.shuffle(a);
        System.out.println("===============================================================");
        System.out.println("a = " + Arrays.toString(a));
        StdRandom.shuffle(b);
        System.out.println("===============================================================");
        System.out.println("b = " + Arrays.toString(b));
        sort(a, b, 0, a.length - 1);
    }

    private static int partition(int[] a, int[] b, int low, int high){
        int i = low - 1, j = high + 1, ii = 0;
        int v = b[low];
        while (true){
            while(a[++i] < v) if(i == high) break;
            while(a[--j] > v) if(j == low) break;
            if (i >= j) break;
            exch(a, i, j);
//            if (a[i] == v) ii = i;
//            if (a[j] == v) ii = j;
        }
//        if(a[j] != v)
//            exch(a, i, ii);
        v = a[j];
        int k = low - 1, l = high + 1;
        while (true){
            while(b[++k] < v) if(k == high) break;
            while(b[--l] > v) if(l == low) break;
            if (k >= l) break;
            exch(b, k, l);
//            if (b[k] == v) ii = k;
//            if (b[l] == v) ii = l;
        }
//        if(b[l] != v)
//            exch(b, k, ii);
        return j;
    }

    public static void sort(int[] a, int[] b, int low, int high){
        if(low >= high) return;
        int mid = partition(a, b, low, high);
        sort(a, b, low, mid - 1);
        sort(a, b, mid + 1, high);
    }

    private static void exch(int[] a, int x, int y){
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }


    public static void main(String[] args) {
        int N = 20;
        int[] a = new int[N];
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
            b[i] = i;
        }
        sort(a, b);
        System.out.println("===============================================================");
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("===============================================================");
        System.out.println("b = " + Arrays.toString(b));
    }
}
