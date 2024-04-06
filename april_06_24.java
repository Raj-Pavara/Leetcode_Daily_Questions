/* Minimum Remove to Make Valid Parentheses
 
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 
Example 1:

Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:

Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:

Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.
 

Constraints:

1 <= s.length <= 105
s[i] is either'(' , ')', or lowercase English letter.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class april_06_24{
public static void main(String args[]){
    String str = "a)b(c)d";
   System.out.println(minRemoveToMakeValid(str));
}
public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    al.add(i);
                }
                else{
                    stack.pop();
                }
            }
            else if(s.charAt(i) == '('){
                stack.push(i);
            }
        }
   
        while (!stack.isEmpty()) {
            al.add(stack.pop());
        }
        Collections.sort(al);
       
        StringBuilder sb = new StringBuilder("");
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            if(i != al.size() && j == al.get(i)){
                i++;
            }
            else{
                sb.append(s.charAt(j));
            }
        }
        
        return sb.toString();

}
}