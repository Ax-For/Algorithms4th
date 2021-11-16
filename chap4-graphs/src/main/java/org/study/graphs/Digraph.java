package org.study.graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Digraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V){
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            this.adj[i] = new Bag<>();
        }
    }

    public Digraph(In in){
        if (in == null) throw new IllegalArgumentException("argument is null");
        int V = in.readInt();
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            this.adj[i] = new Bag<>();
        }
        this.E = 0;
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be non-negative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V(){
        return this.V;
    }

    public int E(){
        return this.E;
    }

    public void addEdge(int v, int w){
        this.adj[v].add(w);
        this.E++;
    }

    public Iterable<Integer> adj(int v){
        return this.adj[v];
    }

    public Digraph reverse(){
        Digraph digraph = new Digraph(this.V);
        for (int i = 0; i < this.V; i++) {
            for (int w : adj(i)){
                digraph.addEdge(w, i);
            }
        }
        return digraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges").append(NEWLINE);
        for (int v = 0; v < this.V; v++) {
            s.append(String.format("%d:", v));
            for (int w: adj(v)){
                s.append(String.format(" %d", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        StdOut.println(digraph);
    }
}
