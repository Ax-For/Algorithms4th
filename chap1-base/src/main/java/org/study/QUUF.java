package org.study;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.study.utils.TimeLog;

import java.util.Arrays;

// quick-union 连通域问题
public class QUUF {
    private int count;
    private int[] id;

    public QUUF(int count) {
        this.count = count;
        this.id = new int[count];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }

    public int find(int p)
    {
        while (p!=id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q)
    {
        int pId = find(p);
        int qId = find(q);
        if(pId == qId) return;
        id[pId] = id[qId];
        count--;
    }

    public void status()
    {
        System.out.println(Arrays.toString(id));
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QUUF uf = new QUUF(n);
        TimeLog log = new TimeLog();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
//            StdOut.println(p + "<=========>" + q);
//            uf.status();
        }
        System.out.println("====================================");
//        uf.status();
        StdOut.println(uf.count() + " components");
        log.showCost();
    }
}
