package org.study.rank.merge;

import org.study.rank.Rank;

// 自底向上的归并算法
public class MergeBU extends Rank {

    private static Comparable[] temp;

    public static void sort(Comparable[] a) {
        int N = a.length;
        temp = new Comparable[N];
        for (int sz = 1; sz < N; sz *= 2) {
            for (int low = 0; low < N - sz; low += 2 * sz) {
                merge(a, low, low + sz - 1, Math.min(low + 2 * sz - 1, N - 1));
            }
        }
    }

    public static void merge(Comparable[] a, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            temp[k] = a[k];
        }
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = temp[j++];
            else if (j > high) a[k] = temp[i++];
            else if (less(temp[i], temp[j])) a[k] = temp[i++];
            else a[k] = temp[j++];
        }
    }

}
