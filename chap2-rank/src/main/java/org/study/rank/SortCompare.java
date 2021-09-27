package org.study.rank;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.study.rank.merge.FasterMerge;
import org.study.rank.merge.Merge;
import org.study.rank.merge.MergeBU;
import org.study.rank.merge.MergeX;
import org.study.rank.quick.Quick;

import java.util.Arrays;

// 比较不同排序算法的优劣
public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch sw = new Stopwatch();
        if      (alg.equals("Insertion"))       Insertion.sort(a);
        else if (alg.equals("InsertionX"))      InsertionX.sort(a);
//        else if (alg.equals("BinaryInsertion")) BinaryInsertion.sort(a);
        else if (alg.equals("Selection"))       Selection.sort(a);
//        else if (alg.equals("Bubble"))          Bubble.sort(a);
        else if (alg.equals("Shell"))           Shell.sort(a);
        else if (alg.equals("ShellX"))          ShellX.sort(a);
        else if (alg.equals("Merge"))           Merge.sort(a);
        else if (alg.equals("FasterMerge"))     FasterMerge.sort(a);
        else if (alg.equals("MergeX"))          MergeX.sort(a);
        else if (alg.equals("MergeBU"))         MergeBU.sort(a);
        else if (alg.equals("Quick"))           Quick.sort(a);
//        else if (alg.equals("Quick3way"))       Quick3way.sort(a);
//        else if (alg.equals("QuickX"))          QuickX.sort(a);
//        else if (alg.equals("Heap"))            Heap.sort(a);
        else if (alg.equals("System"))          Arrays.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return sw.elapsedTime();
    }

    /**
     * 统计对于随机数组序列算法排序的耗时
     * @param alg 使用的算法
     * @param n 待排序的数组大小
     * @param trials 重复次数
     * @return 耗时
     */
    public static double timeRandomInput(String alg, int n, int trials)  {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    /**
     * 统计对于有序数组序列算法排序的耗时
     * @param alg 使用的算法
     * @param n 待排序的数组大小
     * @param trials 重复次数
     * @return 耗时
     */

    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = "Quick";
        String alg2 = "Merge";
        int n = 100000;
        int trials = 10;
        double t1, t2;
        t1 = timeSortedInput(alg1, n, trials);   // Total for alg1.
        System.out.println("t1 = " + t1 + "s");
        t2 = timeSortedInput(alg2, n, trials);   // Total for alg2.
        System.out.println("t2 = " + t2 + "s");

        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);

        t1 = timeRandomInput(alg1, n, trials);   // Total for alg1.
        System.out.println("t1 = " + t1 + "s");
        t2 = timeRandomInput(alg2, n, trials);   // Total for alg2.
        System.out.println("t2 = " + t2 + "s");

        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.4f times faster than %s\n", t2/t1, alg2);
    }
}
