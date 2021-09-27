package org.study.rank;

public class InsertionX extends Rank{

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        int exchanges = 0;
        // 放置哨兵位
        for (int i = N - 1; i > 0; i--) {
            if(less(a[i], a[i-1])){
                exch(a, i, i-1);
                exchanges++;
            }
        }
        if(exchanges == 0) return;
        for (int i = 2; i < N; i++) {
            Comparable temp = a[i];
            int j = i;
            while(less(temp, a[j-1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
    }
}
