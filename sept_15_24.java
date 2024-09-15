/*1371. Find the Longest Substring Containing Vowels in Even Counts

Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

Example 1:

Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.

Example 2:

Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.

Example 3:

Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.

Constraints:

1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
 
*/

import java.util.HashMap;
import java.util.Hashtable;

public class sept_15_24 {
    public static void main(String args[]) {

    }

    public static int findTheLongestSubstring(String s) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int cnt = 0, ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o'
                    || s.charAt(i) == 'u') {
                cnt ^= (1 << ((int) (s.charAt(i) - 'a')));
            }
            if (mp.containsKey(cnt)) {
                ans = Math.max(ans, i - mp.get(cnt));
            } else {
                mp.put(cnt, i);
            }
        }
        return ans;
    }
}