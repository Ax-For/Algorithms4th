package org.study;


import edu.princeton.cs.algs4.*;

public class DrawBox {
    public static void main(String[] args) {
        double xlo = .2, xhi = .5;
        double ylo = .5, yhi = .6;
        int T = 10000;
        Interval1D xInterval = new Interval1D(xlo, xhi);
        Interval1D yInterval = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(xInterval, yInterval);
        box.draw();
        Counter counter = new Counter("hits");
        for (int i = 0; i < T; i++) {
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y);
            if (box.contains(p)) counter.increment();
            else p.draw();
        }
        StdOut.println(counter);
        StdOut.println(box.area());
    }
}
