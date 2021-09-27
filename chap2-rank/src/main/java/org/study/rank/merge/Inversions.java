package org.study.rank.merge;

import edu.princeton.cs.algs4.StdRandom;
import org.study.rank.Rank;

import java.util.Arrays;

// 在自然对数时间内计算数组的【倒置】数目
public class Inversions extends Rank {

    private static Comparable[] temp;

    public static long count(Comparable[] a)
    {
        temp = new Comparable[a.length];
        return count(a, 0, a.length - 1);
    }

    private static long count(Comparable[] a, int low, int high){
        long inversions = 0;
        if (low >= high) return inversions;
        int mid = (low + high) / 2;
        inversions += count(a, low, mid);
        inversions += count(a, mid + 1, high);
        inversions += merge(a, low, mid, high);
        return inversions;
    }

    private static long merge(Comparable[] a, int low, int mid, int high) {
        long inversions = 0;
        for (int i = low; i <= high; i++) {
            temp[i] = a[i];
        }
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = temp[j++];
            else if (j > high) a[k] = temp[i++];
            else if (less(temp[j], temp[i])) {
                a[k] = temp[j++];
                inversions += mid + 1 - i;
            } else a[k] = temp[i++];
        }
        return inversions;
    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(0,100);
        }
        System.out.println(Arrays.toString(a));
        System.out.println(count(a));
        System.out.println(Arrays.toString(a));
    }

}
