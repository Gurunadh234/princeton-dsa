package com.dsa.union;

public class QuickFindUF implements UF {

    private int[] id;

    public QuickFindUF(int n){
        id = new int[n];
        for(int i=0; i<n; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int idP = id[p];
        int idQ = id[q];
        for(int i=0; i<id.length; i++) {
            if(id[i] == idP) {
                id[i] = idQ;
            }
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void printArr() {
        for(int i=0; i<id.length; i++) {
            System.out.printf("{%d: %d}%n", i, id[i]);
        }
    }
}
