package org.study.rank.merge;

import org.study.rank.Rank;

public class FasterMerge extends Rank {

    private static Comparable[] temp;

    public static void sort(Comparable[] a) {
        temp = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (low >= high) return;
        int mid = (low + high) / 2;
        sort(a, low, mid);
        sort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    public static void merge(Comparable[] a, int low, int mid, int high) {
        if (mid + 1 - low >= 0) System.arraycopy(a, low, temp, low, mid + 1 - low);

        for (int i = mid + 1; i <= high; i++) {
            temp[i] = a[high + mid + 1 - i];
        }

        int i = low, j = high;
        for (int k = low; k <= high; k++) {
            if (less(temp[j], temp[i])) a[k] = temp[j--];
            else a[k] = temp[i++];
        }
    }
}
