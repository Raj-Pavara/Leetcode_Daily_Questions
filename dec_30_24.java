/*2466. Count Ways To Build Good Strings

Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.

Example 1:

Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation: 
One possible valid good string is "011". 
It can be constructed as follows: "" -> "0" -> "01" -> "011". 
All binary strings from "000" to "111" are good strings in this example.

Example 2:

Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".

Constraints:

1 <= low <= high <= 105
1 <= zero, one <= low
 */

import java.util.Arrays;

public class dec_30_24 {
    public static void main(String args[]) {

    }

    private static final int MOD = 1_000_000_007;
    private int[] dp;

    private int solve(int low, int high, int zero, int one, int len) {
        if (len > high)
            return 0; // Base case
        if (dp[len] != -1)
            return dp[len]; // Memoization

        int count = 0;
        if (len >= low && len <= high) {
            count = 1; // Count valid string
        }

        // Recursive calls for 'zero' and 'one'
        int addOne = solve(low, high, zero, one, len + one) % MOD;
        int addZero = solve(low, high, zero, one, len + zero) % MOD;

        return dp[len] = (count + addOne + addZero) % MOD;
    }

    public int countGoodStrings(int low, int high, int zero, int one) {
        dp = new int[100001];
        Arrays.fill(dp, -1); // Initialize memoization array
        return solve(low, high, zero, one, 0);
    }
}