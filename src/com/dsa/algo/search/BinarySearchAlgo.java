package com.dsa.algo.search;

public class BinarySearchAlgo {

    public static void main(String[] args) {
        int[] nums = {1, 5, 10, 22, 54, 78};
        System.out.println(binarySearch(nums, 22));
        System.out.println(binarySearch(nums, 222));
    }

    public static int binarySearch(int[] nums, int key) {
        int lo = 0, hi = nums.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] > key) hi = mid - 1;
            else if (nums[mid] < key) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
