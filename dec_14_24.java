/*2762. Continuous Subarrays

You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:

Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
Return the total number of continuous subarrays.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:

Input: nums = [5,4,2,4]
Output: 8
Explanation: 
Continuous subarray of size 1: [5], [4], [2], [4].
Continuous subarray of size 2: [5,4], [4,2], [2,4].
Continuous subarray of size 3: [4,2,4].
Thereare no subarrys of size 4.
Total continuous subarrays = 4 + 3 + 1 = 8.
It can be shown that there are no more continuous subarrays.
 
Example 2:

Input: nums = [1,2,3]
Output: 6
Explanation: 
Continuous subarray of size 1: [1], [2], [3].
Continuous subarray of size 2: [1,2], [2,3].
Continuous subarray of size 3: [1,2,3].
Total continuous subarrays = 3 + 2 + 1 = 6.

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 109
 */

import java.util.Deque;
import java.util.LinkedList;

public class dec_14_24 {
    public static void main(String args[]) {

    }

    public long continuousSubarrays(int[] nums) {
        int l = 0;
        long res = 0; // Use long to avoid overflow
        Deque<Integer> minD = new LinkedList<>(), maxD = new LinkedList<>();

        for (int r = 0; r < nums.length; r++) {
            // Maintain the min deque
            while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[r]) {
                minD.pollLast();
            }
            // Maintain the max deque
            while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[r]) {
                maxD.pollLast();
            }

            // Add the current element index to the deques
            minD.offerLast(r);
            maxD.offerLast(r);

            // Shrink the window from the left until the condition holds
            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > 2) {
                l++;
                if (minD.peekFirst() < l)
                    minD.pollFirst();
                if (maxD.peekFirst() < l)
                    maxD.pollFirst();
            }

            // Add the valid subarrays count from l to r
            res += r - l + 1;
        }

        return res;
    }
}