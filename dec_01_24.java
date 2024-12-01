/*1346. Check If N and Its Double Exist

Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]

Example 1:

Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]

Example 2:

Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.

Constraints:

2 <= arr.length <= 500
-103 <= arr[i] <= 103

*/

import java.util.HashMap;

public class dec_01_24 {
    public static void main(String args[]) {

    }

    public boolean checkIfExist(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store elements

        for (int num : arr) { // Iterate over the array
            // Check if 2 * num or num / 2 exists in the map
            if (map.containsKey(2 * num) || (num % 2 == 0 && map.containsKey(num / 2))) {
                return true; // Return true if the condition is met
            }
            // Add the current number to the map
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return false; // Return false if no such pair exists
    }
}