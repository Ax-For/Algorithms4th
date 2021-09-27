package org.study;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.study.utils.TimeLog;

// weighted union-find 连通域问题
public class WQUUF {

    private int count;
    private int[] id;
    private int[] size;

    public WQUUF(int count) {
        this.count = count;
        this.id = new int[count];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
        this.size = new int[count];
        for (int i = 0; i < size.length; i++) {
            size[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        if (size[pId] < qId) {
            id[pId] = qId;
            size[qId] += size[pId];
        } else {
            id[qId] = pId;
            size[pId] += size[qId];
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WQUUF uf = new WQUUF(n);
        TimeLog log = new TimeLog();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
//            if (uf.connected(p, q)) continue;
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
