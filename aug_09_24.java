/*840. Magic Squares In Grid
A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given a row x col grid of integers, how many 3 x 3 contiguous magic square subgrids are there?

Note: while a magic square can only contain numbers from 1 to 9, grid may contain numbers up to 15.

Example 1:

Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:

while this one is not:

In total, there is only one magic square inside the given grid.

Example 2:

Input: grid = [[8]]
Output: 0
 
Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 10
0 <= grid[i][j] <= 15
 */
public class aug_09_24 {
    public static void main(String args[]) {
        // int grid[][] = { { 4, 3, 8, 4 }, { 9, 5, 1, 9 }, { 2, 7, 6, 2 } };
        System.out.println(numMagicSquaresInside(new int[][]{{8}}));
    }

    public static int numMagicSquaresInside(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[0].length - 2; j++) {
                if (isValidSquareMatrix(grid, i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static boolean isValidSquareMatrix(int grid[][], int row, int col) {
        boolean visited[] = new boolean[9];
        int cs = 0, sum = 0;
        for (int i = row; i < row + 3; i++) {
            cs = 0;
            for (int j = col; j < col + 3; j++) {
                cs += grid[i][j];
                if (visited[grid[i][j] - 1] || grid[i][j] > 9) {
                    return false;
                } else {
                    visited[grid[i][j] - 1] = true;
                }
            }
            if (i == row) {
                sum = cs;
            } else if (sum != cs) {
                return false;
            }
        }

        for (int i = col; i < col + 3; i++) {
            cs = 0;
            for (int j = row; j < row + 3; j++) {
                cs += grid[j][i];
            }
            if (sum != cs) {
                return false;
            }
        }

        if (grid[row][col] + grid[row+2][col+2] != grid[row][col + 2] + grid[row + 2][col]) {
            return false;
        }
        return true;
    }
}