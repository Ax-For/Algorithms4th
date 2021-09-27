package org.study.rank;

public class ShellX extends Rank{

    public static void sort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while(h < N / 3) h = h * 3 + 1;
        while (h >= 1){
            // 方法一：添加哨兵位，减轻判断压力，后发现反而降低性能
//            // 摆放哨兵位,使得 [0,h) 位置为对应子数组的最小值
//            for (int i = N - 1; i >= h; i--) {
//                    if(less(a[i], a[i - h]))
//                        exch(a, i, i - h);
//            }
//            // 平移索引
//            for (int i = 2 * h; i < N; i++) {
//                int j = i;
//                Comparable temp = a[j];
//                while(less(temp, a[j - h])) {
//                    a[j] = a[j - h];
//                    j -= h;
//                }
//                a[j] = temp;
//            }
            // 方法二：取消交换操作，而使用向右平移，性能有所提升
            for (int i = h; i < N; i++) {
                int j = i;
                Comparable temp = a[j];
                while(j >= h && less(temp, a[j - h])) {
                    a[j] = a[j - h];
                    j -= h;
                }
                a[j] = temp;
            }
            h /= 3;
        }
//        if(isSorted(a))
//        {
//            System.out.println("排序成功！");
//        }
//        else
//        {
//            System.out.println("排序失败！");
//        }
    }
}
