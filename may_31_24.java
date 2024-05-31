/*Single Number III

Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.

You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.

Example 1:

Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:

Input: nums = [-1,0]
Output: [-1,0]
Example 3:

Input: nums = [0,1]
Output: [1,0]
 
Constraints:

2 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each integer in nums will appear twice, only two integers will appear once.
 */
public class may_31_24  {
public static void main(String args[]){

}
public int[] singleNumber(int[] nums) {
    // Step 1: XOR all numbers to get xor of the two unique numbers
     int xorResult = 0;
     for (int num : nums) {
         xorResult ^= num;
     }

     // Step 2: Find a set bit (rightmost set bit in xorResult)
     int setBit = xorResult & -xorResult;

     // Step 3: Divide numbers into two groups and find the unique numbers
     int num1 = 0;
     int num2 = 0;
     for (int num : nums) {
         if ((num & setBit) != 0) {
             num1 ^= num;  // XOR numbers where the set bit is set
         } else {
             num2 ^= num;  // XOR numbers where the set bit is not set
         }
     }

     return new int[]{num1, num2};   
 }
}