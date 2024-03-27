 /*Subarray Product Less Than K 
 
Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
 
Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
 */
public class march_27_24{
public static void main(String args[]){
      int arr[] = {10,9,10,4,3,8,3,3,6,2,10,10,9,3};
      System.out.println(numSubarrayProductLessThanK(arr, 19));
}
public static int numSubarrayProductLessThanK(int[] nums, int k) {
   
    int s = 0, e = 0, prod = 1, ans = 0;
        while(e < nums.length) {
            prod *= nums[e++]; // include
            while(s < e && prod >= k) {
                prod /= nums[s++]; // exclude start
            }
            if(prod < k) {
                ans += e-s; // add to answer if it is valid
            }
        }
        return ans;
}
}