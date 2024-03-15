/*Product of Array Except Self
 
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

 

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 

Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */

public class march_15_24 {

  public static void main(String args[]) {
    int arr[] = { 1, 2, 3, 4 };
    int ans[] = productExceptSelf(arr);
    for (int i = 0; i < ans.length; i++) {
      System.out.println(ans[i]);
    }
  }

  public static int[] productExceptSelf(int[] nums) {
    int ans[] = new int[nums.length];

    int product = 1;
    for (int i = 0; i < ans.length; i++) {
      ans[i] = product;
      product *= nums[i];
    }

    product = 1;
    for (int i = ans.length - 1; i >= 0; i--) {
      ans[i] *= product;
      product *= nums[i];
    }

    return ans;
  }
}
