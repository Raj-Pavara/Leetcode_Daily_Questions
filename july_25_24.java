/*912. Sort an Array
Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.

Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.
 

Constraints:

1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104 */
public class july_25_24 {
    public static void main(String args[]) {

    }

    public static int[] sortArray(int[] nums) {
           mergesort(0, nums.length, nums);
           return nums;
    }
    public static void mergesort(int si, int ei, int arr[]) {
        if (si == ei) {
            return;
        }
        int mid = (si + ei) / 2;
        mergesort(si, mid, arr);
        mergesort(mid + 1, ei, arr);
        merge(si, ei, mid, arr);
    }

    public static void merge(int si, int ei, int mid, int arr[]) {
        int i = si, j = mid + 1;
        int temp[] = new int[ei - si + 1];
        int k = 0;
        while (i <= mid && j <= ei) {
            if (arr[i] < arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= ei) {
            temp[k] = arr[j];
            k++;
            j++;
        }
        for (int k2 = 0, z = si; k2 < temp.length; k2++, z++) {
            arr[z] = temp[k2];
        }
    }
}