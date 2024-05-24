/*Maximum Score Words Formed by Letters

Given a list of words, list of  single letters (might be repeating) and score of every character.

Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).

It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.

 

Example 1:

Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
Words "dad" and "dog" only get a score of 21.
Example 2:

Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
Output: 27
Explanation:
Score  a=4, b=4, c=4, x=5, z=10
Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
Word "xxxz" only get a score of 25.
Example 3:

Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
Output: 0
Explanation:
Letter "e" can only be used once.
 

Constraints:

1 <= words.length <= 14
1 <= words[i].length <= 15
1 <= letters.length <= 100
letters[i].length == 1
score.length == 26
0 <= score[i] <= 10
words[i], letters[i] contains only lower case English letters.
 */

import java.util.Arrays;

public class may_24_24 {
public static void main(String args[]){

}
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int W = words.length;
        // Count how many times each letter occurs
        int[] freq = new int[26];
        for (char c: letters) {
            freq[c - 'a']++;
        }
        int maxScore = 0;
        // Iterate over every subset of words
        int[] subsetLetters = new int[26];
        for (int mask = 0; mask < 1 << W; mask++) {
            // Reset the subsetLetters array
            Arrays.fill(subsetLetters, 0);
            for (int i = 0; i < W; i++) {
                if ((mask & (1 << i)) > 0) {
                    // Count the letters in this word
                    int L = words[i].length();
                    for (int j = 0; j < L; j++) {
                        subsetLetters[words[i].charAt(j) - 'a']++;
                    }
                }
            }
            maxScore = Math.max(maxScore, subsetScore(subsetLetters, score, freq));
        }
        return maxScore;
    }

    private int subsetScore(int[] subsetLetters, int[] score, int[] freq) {
        int totalScore = 0;
        // Calculate score of subset
        for (int c = 0; c < 26; c++) {
            totalScore += subsetLetters[c] * score[c];
            // Check if we have enough of each letter to build this subset of words
            if (subsetLetters[c] > freq[c]) {
                return 0;
            }
        }
        return totalScore;
    }
}
