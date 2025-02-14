package com.dsa.union;

public class WeightedQuickUnionUF {

    private int[] id;
    private int[] sz;

    public WeightedQuickUnionUF(int n) {
        id = new int[n];
        sz = new int[n];
        for(int i=0; i<n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while(i != id[i]) {
            id[i] = id[id[i]]; // Path compression
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if(sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        }
        else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
    }

    public void printArr() {
        for(int i=0; i<id.length; i++) {
            System.out.printf("{%d: %d, %d}%n", i, id[i], sz[i]);
        }
    }
}
