/*1190. Reverse Substrings Between Each Pair of Parentheses
You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets. 

Example 1:

Input: s = "(abcd)"
Output: "dcba"
Example 2:

Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:

Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

Constraints:

1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
 */

import java.util.Stack;

public class july_11_24  {
public static void main(String args[]){
   System.out.println(reverseParentheses("(ed(et(oc))el)"));
}
public static String reverseParentheses(String s) {
    Stack<Character> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
        if(s.charAt(i) != ')'){
            stack.push(s.charAt(i));
        }
        else{
            while (stack.peek() != '(') {
             sb.append(stack.pop());   
            }
            stack.pop();
            for (int j = 0; j < sb.length(); j++) {
                stack.push(sb.charAt(j));
            }
            sb = new StringBuilder();
        }
    }
    while (!stack.isEmpty()) {
        sb.append(stack.pop());
    }
    return sb.reverse().toString();
}
}