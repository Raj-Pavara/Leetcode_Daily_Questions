/*The Number of Beautiful Subsets
 You are given an array nums of positive integers and a positive integer k.

A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.

Return the number of non-empty beautiful subsets of the array nums.

A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

 

Example 1:

Input: nums = [2,4,6], k = 2
Output: 4
Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
Example 2:

Input: nums = [1], k = 1
Output: 1
Explanation: The beautiful subset of the array nums is [1].
It can be proved that there is only 1 beautiful subset in the array [1].
 

Constraints:

1 <= nums.length <= 20
1 <= nums[i], k <= 1000
 */
public class may_23_24 {
public static void main(String args[]){

}
public int beautifulSubsets(int[] nums, int k) {
    return countBeautifulSubsets(nums, k, 0, 0);
}

private int countBeautifulSubsets(int[] nums, int difference, int index, int mask) {
    // Base case: Return 1 if mask is greater than 0 (non-empty subset)
    if (index == nums.length)
        return mask > 0 ? 1 : 0;

    // Flag to check if the current subset is beautiful
    boolean isBeautiful = true;

    // Check if the current number forms a beautiful pair with any previous number
    // in the subset
    for (int j = 0; j < index && isBeautiful; ++j){
        isBeautiful = ((1 << j) & mask) == 0 || 
                    Math.abs(nums[j] - nums[index]) != difference;
        }

    // Recursively calculate beautiful subsets including and excluding the current
    // number
    int skip = countBeautifulSubsets(nums, difference, index + 1, mask);
    int take;
    if (isBeautiful) {
        take = countBeautifulSubsets(nums, difference,
                index + 1, mask + (1 << index));
    } else {
        take = 0;
    }

    return skip + take;
}
}