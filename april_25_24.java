/* Longest Ideal Subsequence
You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:

t is a subsequence of the string s.
The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
Return the length of the longest ideal string.
A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.

Example 1:

Input: s = "acfgbd", k = 2
Output: 4
Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
Example 2:

Input: s = "abcd", k = 3
Output: 4
Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
 

Constraints:

1 <= s.length <= 105
0 <= k <= 25
s consists of lowercase English letters.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class april_25_24 {
public static void main(String args[]){
   System.out.println(longestIdealString("acfgbd", 2));
}

    public static int longestIdealString(String s, int k) {
        int N = s.length();

        // Initialize all dp values to -1 to indicate non-visited states
        int[][] dp = new int[N][26];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Find the maximum dp[N-1][c] and return the result
        int res = 0;
        for (int c = 0; c < 26; c++) {
            res = Math.max(res, dfs(N - 1, c, dp, s, k));
        }
        return res;
    }

    public static int dfs(int i, int c, int[][] dp, String s, int k) {
        // Memoized value
        if (dp[i][c] != -1) {
            return dp[i][c];
        }

        // State is not visited yet
        dp[i][c] = 0;
        boolean match = c == (s.charAt(i) - 'a');
        if (match) {
            dp[i][c] = 1;
        }

        // Non base case handling
        if (i > 0) {
            dp[i][c] = dfs(i - 1, c, dp, s, k);
            if (match) {
                for (int p = 0; p < 26; p++) {
                    if (Math.abs(c - p) <= k) {
                        dp[i][c] = Math.max(dp[i][c], dfs(i - 1, p, dp, s, k) + 1);
                    }
                }
            }
        }
        return dp[i][c];
    }
}