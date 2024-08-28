/*1905. Count Sub Islands

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

Example 1:

Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.

Example 2:

Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1. 
*/
public class aug_28_24 {
    public static void main(String args[]) {

    }

    int[] delr = { 1, -1, 0, 0 };
    int[] delc = { 0, 0, 1, -1 };

    private void dfs(int[][] grid1, int[][] grid2, int i, int j, boolean[] flag) {
        if (grid1[i][j] == 0) {
            flag[0] = false;
            return;
        }

        grid2[i][j] = 0;
        int row = grid1.length;
        int col = grid1[0].length;
        for (int k = 0; k < 4; k++) {
            int r = i + delr[k];
            int c = j + delc[k];
            if (r < row && r >= 0 && c < col && c >= 0 && grid2[r][c] == 1) {
                dfs(grid1, grid2, r, c, flag);
            }
        }
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int row = grid1.length;
        int col = grid1[0].length;

        // sub-Iland count...
        int sub = 0;
        for (int i = 0; i < row; i++) {
            boolean[] flag = { true };
            for (int j = 0; j < col; j++) {
                if (grid2[i][j] == 1) {
                    dfs(grid1, grid2, i, j, flag);
                    if (flag[0]) {
                        sub++;
                    }
                    flag[0] = true;
                }
            }
        }
        return sub;
    }
}