/*Remove K Digits
 
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 

Constraints:

1 <= k <= num.length <= 10^5
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 
*/

import java.util.Stack;

public class april_11_24 {

  public static void main(String args[]) {
    System.out.println(removeKdigits("10001", 1));
  }

  public static String removeKdigits(String num, int k) {
        Stack<Character> stk = new Stack<>();
        StringBuilder ans = new StringBuilder();
        for (char c : num.toCharArray()) {
            while (k > 0 && !stk.isEmpty() && stk.peek() > c) {
                stk.pop();
                k--;
            }
            stk.push(c);
        }
        while (k > 0) {
            stk.pop();
            k--;
        }
        for (char c : stk) {
            if (ans.length() == 0 && c == '0') continue;
            ans.append(c);
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }
}