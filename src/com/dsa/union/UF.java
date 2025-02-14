package com.dsa.union;


public interface UF {
    void union(int p, int q);
    boolean connected(int p, int q);
    void printArr();
}
