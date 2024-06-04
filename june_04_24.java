/* Longest Palindrome

Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome.

Example 1:

Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
Example 2:

Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.
 
Constraints:

1 <= s.length <= 2000
s consists of lowercase and/or uppercase English letters only.

*/

import java.util.Arrays;

public class june_04_24 {
    public static void main(String args[]) {
       System.out.println(longestPalindrome("abccccdd"));
    }

    public static int longestPalindrome(String s) {
        if(s.length() == 1){
            return 1;
        }
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        int ans = 0;
        boolean isavailable;
        if(s.length() %2 != 0){
            isavailable = true;
        }
        else{
            isavailable = false;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                ans += 2;
                i++;
            }
            else{
                isavailable = true;
            }
        }
        if(isavailable){
            ans++;
        }
        return ans;

    }
}