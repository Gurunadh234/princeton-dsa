package com.dsa.problems.percolation;

public class WeightedQuickUnion {

    public int[] id;
    public int[] sz;

    public WeightedQuickUnion(int n) {
        int size = n * n + 2;
        id = new int[size];
        sz = new int[size];
        for(int i=0; i<size; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while(i != id[i]) {
            id[i] = id[id[i]];
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
