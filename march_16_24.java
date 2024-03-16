/*Contiguous Array
 
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.


Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class march_16_24{
public static void main(String args[]){
      int arr[] = {0,1,0};
      System.out.println(findMaxLength(arr));
}
public static int findMaxLength(int[] nums) {
    int N = nums.length;
    int[] mp = new int[2*N+2];
    int current = N;
    int result = 0;

    for(int i = 0; i < N; i++) {
        current += (nums[i] << 1) - 1;
        if(current == N) {
            result = i+1;
        }
        else if(mp[current] == 0) {
            mp[current] = i+1;
        }
        else {
            result = Math.max(result, i - mp[current]+1);
        }
    }
    return result;
}
}