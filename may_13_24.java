/*Score After Flipping Matrix

You are given an m x n binary matrix grid.

A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).

Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score after making any number of moves (including zero moves).

 

Example 1:


Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
Example 2:

Input: grid = [[0]]
Output: 1
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid[i][j] is either 0 or 1.
 */
public class may_13_24 {
    public static void main(String args[]) {
        // int grid[][] = { { 0, 0, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 0 } };
        int grid[][] = {{0}};
        System.out.println(matrixScore(grid));
    }

    public static int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Set first column
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                // Flip row
                for (int j = 0; j < n; j++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        // Optimize columns except first column
        for (int j = 1; j < n; j++) {
            int countZero = 0;
            // Count zeros
            for(int i = 0; i < m; i++) {
                if(grid[i][j] == 0) {
                    countZero++;
                }
            }
            // Flip the column if there are more zeros for better score
            if(countZero > m-countZero) {
                for(int i = 0; i < m; i++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }

        // Calculate the final score considering bit positions
        int score = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Left shift bit by place value of column to find column contribution
                int columnScore = grid[i][j] << (n - j - 1);
                // Add contribution to score
                score += columnScore;
            }
        }

        // return final result
        return score;
    }
}