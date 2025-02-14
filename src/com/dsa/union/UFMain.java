package com.dsa.union;

public class UFMain {
    public static void main(String[] args) {
        weightPathCompUF();
    }

    public static void weightPathCompUF() {
        WeightedQuickUnionUF w = new WeightedQuickUnionUF(10);

        w.union(2, 3);
        w.union(2, 2);
        System.out.println(w.connected(2, 3));

        w.printArr();
    }

    public static void quickFind(){
        QuickFindUF quickFindUF = new QuickFindUF(10);

        quickFindUF.union(2, 5);
        System.out.println(quickFindUF.connected(2, 4));
        System.out.println(quickFindUF.connected(2, 5));
        quickFindUF.union(1, 9);
        quickFindUF.union(2, 0);
        quickFindUF.union(7, 5);
        quickFindUF.union(5, 8);
        quickFindUF.printArr();
    }
}
