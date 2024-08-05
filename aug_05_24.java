/*2053. Kth Distinct String in an Array
A distinct string is a string that is present only once in an array.

Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".

Note that the strings are considered in the order in which they appear in the array.
    
Example 1:

Input: arr = ["d","b","c","b","c","a"], k = 2
Output: "a"
Explanation:
The only distinct strings in arr are "d" and "a".
"d" appears 1st, so it is the 1st distinct string.
"a" appears 2nd, so it is the 2nd distinct string.
Since k == 2, "a" is returned. 
Example 2:

Input: arr = ["aaa","aa","a"], k = 1
Output: "aaa"
Explanation:
All strings in arr are distinct, so the 1st string "aaa" is returned.
Example 3:

Input: arr = ["a","b","a"], k = 3
Output: ""
Explanation:
The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "". 

Constraints:

1 <= k <= arr.length <= 1000
1 <= arr[i].length <= 5
arr[i] consists of lowercase English letters.
 */

import java.util.ArrayList;
import java.util.List;

public class aug_05_24 {
public static void main(String args[]){

}

    public static String kthDistinct(String[] arr, int k) {
        int n = arr.length;
        List<String> distinctStrings = new ArrayList<>();

        // Iterate through each string in the array
        for (int i = 0; i < n; i++) {
            String currentString = arr[i];
            boolean isDistinct = true;

            // Check if the current string is distinct
            for (int j = 0; j < n; j++) {
                if (j == i) continue; // Skip comparing with itself
                if (arr[j].equals(currentString)) {
                    isDistinct = false;
                    break;
                }
            }

            // If the string is distinct, add it to the list
            if (isDistinct) {
                distinctStrings.add(currentString);
            }
        }

        // Check if there are enough distinct strings
        if (distinctStrings.size() < k) {
            return "";
        }

        return distinctStrings.get(k - 1);
    }
}