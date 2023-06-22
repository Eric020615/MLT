package com.example.mlt.Graph;

public class Edge {
    public int  u;
    public int  v;
    //Create the new edge u and v
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
    public boolean equals(Object o) {
        return u == ((Edge)o).u && v == ((Edge)o).v;
    }
}