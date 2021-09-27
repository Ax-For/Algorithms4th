package org.study;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import org.study.utils.TimeLog;

import java.util.Arrays;

// quick-find 解决连通问题
public class QFUF {

    private int count;
    private int[] id;

    public QFUF(int count) {
        this.count = count;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    // 获取连通分量数目
    public int count() {
        return count;
    }

    // 判断触点是否连通
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 查找触点所属连通分量
    public int find(int p) {
        return id[p];
    }

    // 连接两个分量
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) id[i] = pId;
        }
        count--;
    }

    public void status() {
        System.out.println(Arrays.toString(id));
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QFUF uf = new QFUF(n);
        TimeLog log = new TimeLog();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
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
