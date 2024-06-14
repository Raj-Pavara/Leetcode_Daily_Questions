/*Minimum Increment to Make Array Unique

You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.

Return the minimum number of moves to make every value in nums unique.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: nums = [1,2,2]
Output: 1
Explanation: After 1 move, the array could be [1, 2, 3].
Example 2:

Input: nums = [3,2,1,2,1,7]
Output: 6
Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
 */
public class june_14_24 {
public static void main(String args[]){

}
public static int minIncrementForUnique(int[] nums) {
    int n = nums.length;
    int max = 0;
    int minIncrements = 0;

    // Find maximum value in array to determine range of frequencyCount array
    for (int val : nums) {
        max = Math.max(max, val);
    }

    // Create a frequencyCount array to store the frequency of each value in nums
    int[] frequencyCount = new int[n + max];

    // Populate frequencyCount array with the frequency of each value in nums
    for (int val : nums) {
        frequencyCount[val]++;
    }

    // Iterate over the frequencyCount array to make all values unique
    for (int i = 0; i < frequencyCount.length; i++) {
        if (frequencyCount[i] <= 1) continue;

        // Determine excess occurrences, carry them over to the next value,
        // ensure single occurrence for current value, and update minIncrements.
        int duplicates = frequencyCount[i] - 1;
        frequencyCount[i + 1] += duplicates;
        frequencyCount[i] = 1;
        minIncrements += duplicates;
    }

    return minIncrements;
}

}