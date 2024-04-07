/*Valid Parenthesis String
 
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true
 
Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */

import java.util.Stack;

public class april_07_24 {

  public static void main(String args[]) {
    System.out.println(checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));

  }

  public static boolean checkValidString(String s) {
    int leftCount = 0, rightCount = 0;
        
    for (char c : s.toCharArray()) {
        leftCount += c == '(' ? 1 : -1;
        rightCount += c == ')' ? -1 : 1;
        
        if (rightCount < 0) {
            break;
        }
        
        leftCount = Math.max(leftCount, 0);
    }

    return leftCount == 0;
  }

}
