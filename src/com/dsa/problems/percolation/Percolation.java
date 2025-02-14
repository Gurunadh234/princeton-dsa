package com.dsa.problems.percolation;


public class Percolation {

    public int top;
    public int bottom;
    public int grid;
    public boolean[] openSites;
    public WeightedQuickUnion wq;

    public Percolation(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("n should be greater then 0");
        }
        wq = new WeightedQuickUnion(n);
        openSites = new boolean[n * n];
        top = n * n;
        bottom = n * n + 1;
        grid = n;
    }

    public void open(int row, int col) {
        int index = getIndex(row, col);
        openSites[index] = true;

        if(row == 1) {
            wq.union(top, index);
        }
        if(row == grid) {
            wq.union(bottom, index);
        }

        if(row > 1 && isOpen(row - 1, col)) {
            wq.union(index, getIndex(row - 1, col));
        }
        if(row < grid && isOpen(row + 1, col)) {
            wq.union(index, getIndex(row + 1, col));
        }
        if(col > 1 && isOpen(row, col - 1)) {
            wq.union(index, getIndex(row, col - 1));
        }
        if(col < grid && isOpen(row, col + 1)) {
            wq.union(index, getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        int index = getIndex(row, col);
        return openSites[index];
    }

    public boolean isFull(int row, int col) {
        if(row < 1 || row > grid || col < 1 || col > grid) {
            throw new IllegalArgumentException("Index out of bound");
        }

        return wq.connected(top, getIndex(row, col));
    }

    public int numberOfOpenSites() {
        int openSitesCount = 0;
        for(boolean opened : openSites) {
            if(opened) {
                openSitesCount++;
            }
        }
        return openSitesCount;
    }

    public boolean percolates() {
        return wq.connected(top, bottom);
    }

    public int getIndex(int row, int col) {
        return (row - 1) * grid + (col - 1);
    }

    public WeightedQuickUnion getUnion() {
        return this.wq;
    }
}
