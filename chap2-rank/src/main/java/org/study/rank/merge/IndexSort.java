package org.study.rank.merge;

import edu.princeton.cs.algs4.StdRandom;
import org.study.rank.Rank;

import java.util.Arrays;


// 使用归并排序，求得间接排序索引
public class IndexSort extends Rank {

    private static int[] aux;

    private static int[] idx;

    public static void merge(Comparable[] a, int low, int mid, int high) {
        int i = low, j = mid + 1;
        if (high + 1 - low >= 0) System.arraycopy(idx, low, aux, low, high + 1 - low);
        for (int k = low; k <= high; k++) {
            if (i > mid) idx[k] = aux[j++];
            else if (j > high) idx[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) idx[k] = aux[j++];
            else idx[k] = aux[i++];
        }
    }

    public static int[] sort(Comparable[] a){
        int N = a.length;
        aux = new int[N];
        idx = new int[N];
        for (int i = 0; i < N; i++) {
            idx[i] = i;
        }
        sort(a, 0,a.length - 1);
        return idx;
    }

    private static void sort(Comparable[] a, int low, int high){
        if(low >= high) return;
        int mid = (low + high) / 2;
        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[]{30, 27, 28, 75, 99, 85, 52, 87, 59, 3};
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(0, 100);
        }
        System.out.println(Arrays.toString(a));
        int[] sort = sort(a);
        System.out.println(Arrays.toString(sort));
        for (int i : sort) {
            System.out.print(a[i] + ", ");
        }
    }

}
