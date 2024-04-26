/*Minimum Falling Path Sum II

Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.
Example 1:

Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
Example 2:

Input: grid = [[7]]
Output: 7
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
-99 <= grid[i][j] <= 99
 */
public class april_26_24 {
    public static void main(String args[]) {
        int grid[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        // int grid[][] = { {7}};
        System.out.println(minFallingPathSum(grid));
    }

    public static int minFallingPathSum(int[][] grid) {
        // Initialize a two-dimensional array to cache the result of each sub-problem
        int[][] memo = new int[grid.length][grid.length];

        // Minimum and Second Minimum Column Index
        int nextMin1C = -1;
        int nextMin2C = -1;

        // Base Case. Fill and save the minimum and second minimum column index
        for (int col = 0; col < grid.length; col++) {
            memo[grid.length - 1][col] = grid[grid.length - 1][col];
            if (nextMin1C == -1 || memo[grid.length - 1][col] <= memo[grid.length - 1][nextMin1C]) {
                nextMin2C = nextMin1C;
                nextMin1C = col;
            } else if (nextMin2C == -1 || memo[grid.length - 1][col] <= memo[grid.length - 1][nextMin2C]) {
                nextMin2C = col;
            }
        }

        // Fill the recursive cases
        for (int row = grid.length - 2; row >= 0; row--) {
            // Minimum and Second Minimum Column Index of the current row
            int min1C = -1;
            int min2C = -1;

            for (int col = 0; col < grid.length; col++) {
                // Select minimum from valid cells of the next row
                if (col != nextMin1C) {
                    memo[row][col] = grid[row][col] + memo[row + 1][nextMin1C];
                } else {
                    memo[row][col] = grid[row][col] + memo[row + 1][nextMin2C];
                }

                // Save minimum and second minimum column index
                if (min1C == -1 || memo[row][col] <= memo[row][min1C]) {
                    min2C = min1C;
                    min1C = col;
                } else if (min2C == -1 || memo[row][col] <= memo[row][min2C]) {
                    min2C = col;
                }
            }

            // Change of row. Update nextMin1C and nextMin2C
            nextMin1C = min1C;
            nextMin2C = min2C;
        }

        // Return the minimum from the first row
        return memo[0][nextMin1C];
    }
}