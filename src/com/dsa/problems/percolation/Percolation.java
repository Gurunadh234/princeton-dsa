package com.dsa.problems.percolation;


public class Percolation {

    public int virtualTop;
    public int virtualBottom;
    public int grid;
    public boolean[] openSites;
    public WeightedQuickUnion percolationWQ;
    public WeightedQuickUnion fullnessWQ;

    public Percolation(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("n should be greater then 0");
        }
        percolationWQ = new WeightedQuickUnion(n * n + 2);
        fullnessWQ = new WeightedQuickUnion(n * n + 1);
        openSites = new boolean[n * n];
        grid = n;
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    public void open(int row, int col) {
        int pIndex = getIndex(row, col);
        openSites[pIndex] = true;

        if(row == 1) {
            percolationWQ.union(virtualTop, pIndex);
            fullnessWQ.union(virtualTop, pIndex);
        }
        if(row == grid) {
            percolationWQ.union(virtualBottom, pIndex);
        }

        int qIndex;
        if(row > 1 && isOpen(row - 1, col)) {
            qIndex = getIndex(row - 1, col);
            percolationWQ.union(pIndex, qIndex);
            fullnessWQ.union(pIndex, qIndex);
        }
        if(row < grid && isOpen(row + 1, col)) {
            qIndex = getIndex(row + 1, col);
            percolationWQ.union(pIndex, qIndex);
            fullnessWQ.union(pIndex, qIndex);
        }
        if(col > 1 && isOpen(row, col - 1)) {
            qIndex = getIndex(row, col - 1);
            percolationWQ.union(pIndex, qIndex);
            fullnessWQ.union(pIndex, qIndex);
        }
        if(col < grid && isOpen(row, col + 1)) {
            qIndex = getIndex(row, col + 1);
            percolationWQ.union(pIndex, qIndex);
            fullnessWQ.union(pIndex, qIndex);
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

        return fullnessWQ.connected(virtualTop, getIndex(row, col));
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
        return percolationWQ.connected(virtualTop, virtualBottom);
    }

    public int getIndex(int row, int col) {
        return (row - 1) * grid + (col - 1);
    }

    public WeightedQuickUnion getUnion() {
        return this.percolationWQ;
    }
}
