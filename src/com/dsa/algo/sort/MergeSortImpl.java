package com.dsa.algo.sort;

public class MergeSortImpl {

    public static void main(String[] args) {

    }

    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];

        mergeSort(arr, aux, 0, arr.length);
    }

    private static void mergeSort(int[] arr, int[] aux, int lo, int hi) {
        if(lo >= hi) return;

        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, aux, lo, mid);
        mergeSort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    public static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
    }
}
