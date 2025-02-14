package com.dsa.algo.questions;

public class SumThreeZero {

    public static void main(String[] args) {
        int[] nums = {30, -40, -20, -10, 40, 0, 10, 5};
        System.out.println(sumThreeEqualZero(nums));
    }

    /**
     * Sum of any three numbers should be zero
     * Return the occurrences
     * @param nums - integer array
     */
    public static int sumThreeEqualZero(int[] nums) {
        int count = 0;
        insertionSort(nums);

        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                boolean foundTriplet = findOccurrences(nums, j+1, nums.length - 1, -(nums[i] + nums[j]));
                if(foundTriplet) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Time complexity of N^2
     * @param nums
     */
    public static void insertionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            while (j >= 0 && temp < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = temp;
        }
    }

    /**
     * Time complexity of N^2 logN
     * @param nums
     * @param lo
     * @param hi
     * @param value
     * @return
     */
    public static boolean findOccurrences(int[] nums, int lo, int hi, int value) {
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] < value) {
                lo = mid + 1;
            }
            else if(nums[mid] > value) {
                hi = mid - 1;
            }
            else {
                return true;
            }
        }

        return false;
    }

    public static void printArr(int[] arr) {
        System.out.println("Printing array");
        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
