/*3097. Shortest Subarray With OR at Least K II

You are given an array nums of non-negative integers and an integer k.

An array is called special if the bitwise OR of all of its elements is at least k.

Return the length of the shortest special non-empty 
subarray of nums, or return -1 if no special subarray exists.

Example 1:

Input: nums = [1,2,3], k = 2

Output: 1

Explanation:

The subarray [3] has OR value of 3. Hence, we return 1.

Example 2:

Input: nums = [2,1,8], k = 10

Output: 3

Explanation:

The subarray [2,1,8] has OR value of 11. Hence, we return 3.

Example 3:

Input: nums = [1,2], k = 0

Output: 1

Explanation:

The subarray [1] has OR value of 1. Hence, we return 1.

Constraints:

1 <= nums.length <= 2 * 105
0 <= nums[i] <= 109
0 <= k <= 109 
*/
public class nov_10_24 {
    public static void main(String args[]) {

    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int[] count = new int[32];
        int start = 0, end = 0, min = Integer.MAX_VALUE;
        while (end < nums.length) {
            updateBits(count, nums[end], 1);
            while (start <= end && getVal(count) >= k) {
                min = Math.min(min, end - start + 1);
                updateBits(count, nums[start], -1);
                start++;
            }
            end++;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void updateBits(int count[], int num, int val) {
        for (int i = 0; i < 32; i++) {
            if (((num >> i) & 1) == 1)
                count[i] += val;
        }
    }

    public int getVal(int[] count) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] > 0)
                ans = ans | (1 << i);
        }
        return ans;
    }
}