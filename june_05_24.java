/*Find Common Characters
Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.

 

Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class june_05_24 {
    public static void main(String args[]) {

    }

 public static List<String> commonChars(String[] words) {
        int wordsSize = words.length;
        int[] commonCharacterCounts = new int[26];
        int[] currentCharacterCounts = new int[26];
        List<String> result = new ArrayList<>();

        // Initialize commonCharacterCounts with the characters from the first
        // word
        for (char ch : words[0].toCharArray()) {
            commonCharacterCounts[ch - 'a']++;
        }

        for (int i = 1; i < wordsSize; i++) {
            Arrays.fill(currentCharacterCounts, 0);

            // Count characters in the current word
            for (char ch : words[i].toCharArray()) {
                currentCharacterCounts[ch - 'a']++;
            }

            // Update the common character counts to keep the minimum counts
            for (int letter = 0; letter < 26; letter++) {
                commonCharacterCounts[letter] = Math.min(
                    commonCharacterCounts[letter],
                    currentCharacterCounts[letter]
                );
            }
        }

        // Collect the common characters based on the final counts
        for (int letter = 0; letter < 26; letter++) {
            for (
                int commonCount = 0;
                commonCount < commonCharacterCounts[letter];
                commonCount++
            ) {
                result.add(String.valueOf((char) (letter + 'a')));
            }
        }

        return result;
    }
}