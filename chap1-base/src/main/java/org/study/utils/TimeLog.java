package org.study.utils;

public class TimeLog {

    private final long start;

    public TimeLog() {
        start = System.currentTimeMillis();
    }

    public void showCost()
    {
        System.out.printf("Time cost %.3fs", (System.currentTimeMillis() - start) / 1000.0);
    }
}
