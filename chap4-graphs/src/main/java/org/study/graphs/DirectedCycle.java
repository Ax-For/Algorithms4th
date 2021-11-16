package org.study.graphs;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]){
                dfs(G, v);
            }
        }
    }

    public void dfs(Digraph G, int s){
        this.marked[s] = true;
        this.onStack[s] = true;
        for (int w: G.adj(s)) {
            if (!marked[w]){
                edgeTo[w] = s;
                dfs(G, w);
            }
            else if (onStack[w]){
                this.cycle = new Stack<>();
                for (int i = s; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(s);
            }
        }
        onStack[s] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }
}
