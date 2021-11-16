package org.study.graphs;

import edu.princeton.cs.algs4.StdOut;

// 拓扑排序
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()){
            DepthFirstOrder dfo = new DepthFirstOrder(G);
            order = dfo.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG(){
        return order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph digraph = new SymbolDigraph(filename, separator);
        Topological top = new Topological(digraph.digraph());
        for (int v: top.order()){
            StdOut.println(digraph.nameOf(v));
        }
    }
}
