package com.dsa.union;

public class QuickUnionUF implements UF{

    private int[] id;

    public QuickUnionUF(int n) {
        id = new int[n];
        for(int i=0; i<n; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while(i != id[i]) {
            i = id[i];
        }
        return i;
    }

    @Override
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        id[rootP] = rootQ;
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public void printArr() {
        for(int i=0; i<id.length; i++) {
            System.out.printf("{%d: %d}%n", i, id[i]);
        }
    }
}
