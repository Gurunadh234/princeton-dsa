package com.dsa.algo.questions;

import java.util.Arrays;

/**
 * <a href="https://www.coursera.org/learn/algorithms-part1/assignment-submission/lhs1X/interview-questions-analysis-of-algorithms-ungraded/attempt">Bitonic</a>
 * Question 2
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed
 * immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct
 * integer values, determines whether a given integer is in the array.
 * Standard version: Use ~3lgN compares in the worst case.
 * Signing bonus: Use ~2lgN compares in the worst case (and prove that no algorithm can guarantee to perform fewe than
 * ~2lgN compares in the worst case.
 */
public class BitonicArray {

    private static int[][] bitonicArrays = {
            {1, 3, 7, 12, 9, 5, 2},
            {2, 4, 8, 10, 15, 14, 9, 3, 1},
            {-10, -5, 0, 8, 20, 15, 7, 2, -3},
            {5, 10, 20, 30, 25, 15, 8, 4, 1},
            {1, 6, 9, 14, 17, 21, 19, 13, 7, 3},
            {3, 9, 18, 24, 30, 29, 20, 15, 10, 5, 2},
            {100, 200, 500, 800, 750, 600, 400, 300},
            {1, 2, 3, 4, 5, 4, 3, 2, 1},
            {1, 2, 3, 4, 5, 6, 7, 8},
            {5, 4, 3, 2, 1}
    };
    private static int[] peaks = {3, 4, 4, 3, 5, 4, 3, 4, 7, 0};

    public static void main(String[] args) {
        Arrays.stream(bitonicArrays).forEach(bitonic -> {
            searchInBitonicArray(bitonic, bitonic[4]);
        });
    }

    public static void searchInBitonicArray(int[] nums, int key) {
        int peakIndex = findPeak(nums);
        int index = searchKey(nums, 0, peakIndex, key, true);

        if(index != -1) {
            System.out.printf("Found key in first half %d: %d, key: %d%n", index, nums[index], key);
        }
        else {
            index = searchKey(nums, peakIndex + 1, nums.length - 1, key, false);
            if(index != -1) {
                System.out.printf("Found key in second half %d: %d, key: %d%n", index, nums[index], key);
            }
            else {
                System.out.println("Key not found");
            }
        }
    }

    public static int findPeak(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] > nums[mid+1]) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }

    public static int searchKey(int[] nums, int lo, int hi, int key, boolean forward) {
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] == key) return mid;

            if(forward) {
                if(nums[mid] < key) lo = mid + 1;
                else hi = mid - 1;
            }
            else {
                if(nums[mid] > key) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    public static void testFindPeak() {
        for(int i=0; i<bitonicArrays.length; i++) {
            int peak = findPeak(bitonicArrays[i]);
            boolean check = peak == peaks[i];
            System.out.println("Peak " + (check ? "matched " + peak : "not matched"));

            assert check;
        }
    }
}
