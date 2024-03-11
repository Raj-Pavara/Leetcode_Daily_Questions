/*Custom Sort String
 
You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

 

Example 1:

Input:  order = "cba", s = "abcd" 

Output:  "cbad" 

Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".

Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:

Input:  order = "bcafg", s = "abcd" 

Output:  "bcad" 

Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.

Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.

 

Constraints:

1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
 */

public class march_11_24 {

  public static void main(String args[]) {
    System.out.println(customSortString("cba", "abcd"));
  }

  public static String customSortString(String order, String s) {
    // move all characters that don't appear in order, and just add
    // them to our returning string
    // now for the rest of the characters in s,
    // we need to count how many times it appears

    // iterate through our pairs
    // and add "letter" x times to our string, where x is the count in s

    int[] sCounts = new int[26];
    for (int i = 0; i < s.length(); ++i) {
      sCounts[s.charAt(i) - 'a']++; // increment the count
    }

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < order.length(); ++i) {
      char c = order.charAt(i);
      while (sCounts[c - 'a'] > 0) {
        builder.append(c);
        sCounts[c - 'a']--;
      }
    }
    // add any remaining characters
    for (int i = 0; i < 26; ++i) {
      while (sCounts[i] > 0) {
        builder.append((char) ('a' + i));
        sCounts[i]--;
      }
    }

    return builder.toString();
  }
}
