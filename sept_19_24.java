/*241. Different Ways to Add Parentheses

Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.

Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
 
Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
The integer values in the input expression do not have a leading '-' or '+' denoting the sign. */

import java.util.ArrayList;
import java.util.List;

public class sept_19_24 {
    public static void main(String args[]) {

    }

    public List<Integer> recUtil(String expression) {
        List<Integer> ans = new ArrayList<>();
        int n = expression.length();
        if (n == 0)
            return ans;
        if (n == 1) {
            ans.add(Integer.parseInt(expression));
            return ans;
        }
        if (n == 2 && Character.isDigit(expression.charAt(0))) {
            ans.add(Integer.parseInt(expression));
            return ans;
        }
        for (int i = 0; i < n; i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch))
                continue;
            List<Integer> left = recUtil(expression.substring(0, i));
            List<Integer> right = recUtil(expression.substring(i + 1));

            for (int l : left) {

                for (int r : right) {
                    if (ch == '+')
                        ans.add(l + r);
                    else if (ch == '-')
                        ans.add(l - r);
                    else
                        ans.add(l * r);

                }
            }
        }
        return ans;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        return recUtil(expression);
    }
}