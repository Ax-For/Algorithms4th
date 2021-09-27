package org.study.rank.merge;

import org.study.rank.Rank;

public class Merge extends Rank {

    private static Comparable[] temp;

    public static void sort(Comparable[] a)
    {
        temp = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high)
    {
        if(low >= high) return;
        int mid = (high + low) / 2;
        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    public static void merge(Comparable[] a, int low, int mid, int high)
    {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            temp[k] = a[k];
        }
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = temp[j++];
            else if(j > high) a[k] = temp[i++];
            else if(less(temp[i], temp[j])) a[k] = temp[i++];
            else a[k] = temp[j++];
        }
    }
}
