package org.study.graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

// 深度优先搜索路径
public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    public void dfs(Graph G, int v){
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]){
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int i = v; i != this.s; i = edgeTo[i]){
            stack.push(i);
        }
        stack.push(this.s);
        return stack;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths paths = new DepthFirstPaths(G, s);
        for (int i = 0; i < G.V(); i++) {
            System.out.printf("%d to %d: ", s, i);
            if (paths.hasPathTo(i)){
                for (int v : paths.pathTo(i)){
                    if (v == s) System.out.print(v);
                    else System.out.print("-" + v);
                }
            }
            System.out.println();
        }
    }
}
