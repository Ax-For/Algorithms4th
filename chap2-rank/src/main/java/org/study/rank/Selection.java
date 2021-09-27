package org.study.rank;

import edu.princeton.cs.algs4.StdIn;

public class Selection extends Rank{

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < a.length; i++) {
            // 将 a[i] 与 a[i+1...N-1] 中的最小值交换位置
            int min = i;
            for (int j = i+1; j < N; j++) {
                if(less(a[j], a[min])) min = j;
            }
            if(i != min)
                exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }

}
