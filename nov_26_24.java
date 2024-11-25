/*773. Sliding Puzzle

On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Example 1:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.

Example 2:

Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.

Example 3:

Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
 
Constraints:

board.length == 2
board[i].length == 3
0 <= board[i][j] <= 5
Each value board[i][j] is unique.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class nov_26_24 {
    public static void main(String args[]) {

    }

    public int slidingPuzzle(int[][] board) {
        // Directions for possible swaps based on '0' position
        int[][] dir = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };
        String target = "123450";
        Set<String> vis = new HashSet<>(); // Track visited configurations
        Queue<String> q = new LinkedList<>();
        String start = "";

        // Convert 2D board to a single string
        for (int[] row : board) {
            for (int col : row) {
                start += col;
            }
        }

        q.offer(start);
        vis.add(start);
        int step = 0;

        // Perform BFS
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String current = q.poll();

                // Check if target is reached
                if (current.equals(target))
                    return step;

                int zero = current.indexOf('0'); // Find position of '0'

                // Generate next moves
                for (int move : dir[zero]) {
                    StringBuilder next = new StringBuilder(current);
                    char temp = next.charAt(zero);
                    next.setCharAt(zero, next.charAt(move));
                    next.setCharAt(move, temp);

                    if (!vis.contains(next.toString())) { // Add unvisited states to the queue
                        vis.add(next.toString());
                        q.offer(next.toString());
                    }
                }
            }
            step++;
        }
        return -1; // Return -1 if target is unreachable
    }
}