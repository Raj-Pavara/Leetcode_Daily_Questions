/*2707. Extra Characters in a String

You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.

Return the minimum number of extra characters left over if you break up s optimally.

Example 1:

Input: s = "leetscode", dictionary = ["leet","code","leetcode"]

Output: 1
Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

Example 2:

Input: s = "sayhelloworld", dictionary = ["hello","world"]
Output: 3
Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.

Constraints:

1 <= s.length <= 50
1 <= dictionary.length <= 50
1 <= dictionary[i].length <= 50
dictionary[i] and s consists of only lowercase English letters
dictionary contains distinct words

*/

import java.util.HashMap;
import java.util.Map;

public class sept_23_24 {
    public static void main(String args[]) {

    }

    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        var root = makeTrie(dictionary);
        var dp = new int[n + 1];

        for (int start = n - 1; start >= 0; start--) {
            dp[start] = dp[start + 1] + 1;
            var node = root;
            for (int end = start; end < n; end++) {
                if (!node.children.containsKey(s.charAt(end))) {
                    break;
                }
                node = node.children.get(s.charAt(end));
                if (node.isEnd) {
                    dp[start] = Math.min(dp[start], dp[end + 1]);
                }
            }
        }

        return dp[0];
    }

    public Trie makeTrie(String[] dictionary) {
        var root = new Trie();
        for (var word : dictionary) {
            var node = root;
            for (var c : word.toCharArray()) {
                node.children.putIfAbsent(c, new Trie());
                node = node.children.get(c);
            }
            node.isEnd = true;
        }
        return root;
    }
}

class Trie {
    Map<Character, Trie> children = new HashMap();
    boolean isEnd = false;
}
