/*664. Strange Printer
There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

Example 1:

Input: s = "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:

Input: s = "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 

Constraints:

1 <= s.length <= 100
s consists of lowercase English letters. */

import java.util.Arrays;

public class aug_21_24 {
    public static void main(String args[]) {

    }

    public int strangePrinter(String s) {
        int[][] dp = new int[101][101];
        for (int i = 0; i < 101; i++) {
            Arrays.fill(dp[i], 1);
        }

        int n = s.length();
        if (n == 0) {
            return 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                dp[j][j + i] = i + 1;

                for (int k = j + 1; k <= j + i; k++) {
                    int temp = dp[j][k - 1] + dp[k][j + i];
                    if (s.charAt(k - 1) == s.charAt(j + i)) {
                        temp--;
                    }
                    dp[j][j + i] = Math.min(temp, dp[j][j + i]);
                }
            }
        }
        return dp[0][n - 1];
    }
}