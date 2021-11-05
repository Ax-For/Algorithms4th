package org.study.graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

// 深度优先搜索解决连通分量问题
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        this.marked = new boolean[G.V()];
        this.id = new int[G.V()];
        this.count = 0;
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]){
                dfs(G, i);
                count++;
            }
        }
    }

    public void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)){
            if (!marked[w]){
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return this.count;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);

        int m = cc.count();
        System.out.println(m + " components");
        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Bag<>();
        }
        for (int i = 0; i < G.V(); i++) {
            components[cc.id(i)].add(i);
        }
        for (int i = 0; i < m; i++) {
            for (int v: components[i])
            {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
