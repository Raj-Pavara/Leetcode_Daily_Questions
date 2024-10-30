/*1671. Minimum Number of Removals to Make Mountain Array

You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array nums​​​, return the minimum number of elements to remove to make nums​​​ a mountain array.

Example 1:

Input: nums = [1,3,1]
Output: 0
Explanation: The array itself is a mountain array so we do not need to remove any elements.

Example 2:

Input: nums = [2,1,1,5,6,2,3,1]
Output: 3
Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].

Constraints:

3 <= nums.length <= 1000
1 <= nums[i] <= 109
It is guaranteed that you can make a mountain array out of nums.
 */

import java.util.Arrays;

public class oct_30_24 {
    public static void main(String args[]) {

    }

    public static int minimumMountainRemovals(int[] nums) {
        // Combination of Longest Increasing Subsequence and Trapping Rain Water
        int n = nums.length;
        int[] leftDP = new int[n];
        int[] rightDP = new int[n];
        Arrays.fill(leftDP, 1);
        Arrays.fill(rightDP, 1);
        // Find LIS from left
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    leftDP[i] = Math.max(leftDP[i], leftDP[j] + 1);
                }
            }
        }
        // Find LIS from right
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    rightDP[i] = Math.max(rightDP[i], rightDP[j] + 1);
                }
            }
        }
        // Find largest mountain by finding largest sum
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // Make sure there is a left and right slope, or else it's not a mountain
            if (leftDP[i] != 1 && rightDP[i] != 1) {
                ans = Math.max(ans, leftDP[i] + rightDP[i]);
            }
        }
        /*
         * Debugging purposes
         * for (int i : leftDP) {
         * System.out.println(i);
         * }
         * System.out.println("RIGHT");
         * for (int j : rightDP) {
         * System.out.println(j);
         * }
         */
        // Return the number of numbers to remove to form the mountain
        return n - ans + 1;
    }
}