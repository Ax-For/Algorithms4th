package org.study.graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// 图的邻接表表示
public class Graph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;            // 顶点数目
    private int E;                  // 边数目
    private Bag<Integer>[] adj;     // 邻接表

    // 创建一个含有V个顶点但不含有边的图
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            this.adj[v] = new Bag<>();
        }
    }

    // 从标准输入流读入一张图
    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    // 顶点数
    public int V() {
        return V;
    }

    // 边数
    public int E() {
        return E;
    }

    // 向图中添加一条边v-w
    public void addEdge(int v, int w) {
        this.adj[v].add(w);
        this.adj[w].add(v);
        this.E++;
    }

    // 获取和v相邻的所有顶点
    Iterable<Integer> adj(int v) {
        return this.adj[v];
    }

    // 对象的字符串表示
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w: this.adj(v)){
                s.append(w).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
