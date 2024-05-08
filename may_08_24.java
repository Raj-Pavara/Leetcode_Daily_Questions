/* Relative Ranks

You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:

The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.

Example 1:

Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
Example 2:

Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

Constraints:

n == score.length
1 <= n <= 104
0 <= score[i] <= 106
All the values in score are unique.

 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class may_08_24 {
    public static void main(String args[]) {
 
        int arr[] = {10,3,8,9,4};
        String ans[] = findRelativeRanks(arr);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        } 

    }

      public static String[] findRelativeRanks(int[] score) {
        int N = score.length;
        int[] scoreCopy = new int[N];
        System.arraycopy(score, 0, scoreCopy, 0, N);
       
        // Save the index of each athlete
        Map<Integer, Integer> scoreToIndex = new HashMap<>();
        for (int i = 0; i < N; i++) {
            scoreToIndex.put(scoreCopy[i], i);
        }
        
        // Sort scoreCopy in ascending order
        Arrays.sort(scoreCopy);

        // Assign ranks to athletes
        // Traverse scoreCopy in reverse
        String[] rank = new String[N]; 
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                rank[scoreToIndex.get(scoreCopy[N - i - 1])] = "Gold Medal";
            } else if (i == 1) {
                rank[scoreToIndex.get(scoreCopy[N - i - 1])] = "Silver Medal";
            } else if (i == 2) {
                rank[scoreToIndex.get(scoreCopy[N - i - 1])] = "Bronze Medal";
            } else {
                rank[scoreToIndex.get(scoreCopy[N - i - 1])] = Integer.toString(i + 1);
            }
        }
        
        return rank;
    }
}