package org.study.rank;

public class Shell extends Rank {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            // 外循环，索引移动，左侧为已排序部分
            for (int i = h; i < N; i++) {
                // 内循环，用于比较和交换
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }
}
