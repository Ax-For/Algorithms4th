package org.study;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class VisualAccumulator {
    private double total;
    private int N;

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    public double getTotal() {
        return total;
    }

    public int getN() {
        return N;
    }

    public void addDataVal(double val) {
        N++;
        total += val;
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(Color.RED);
        StdDraw.point(N, total/N);
    }

    public static void main(String[] args) {
        int T = 4000;
        VisualAccumulator accumulator = new VisualAccumulator(T, 1.0);
        for (int i = 0; i < T; i++) {
            if (StdRandom.uniform() < 0.5) accumulator.addDataVal(StdRandom.gaussian(0.4, 0.05));
            else accumulator.addDataVal(StdRandom.gaussian(0.6, 0.05));
        }
        System.out.println("mean: " + accumulator.getTotal() / accumulator.getN());
    }
}
