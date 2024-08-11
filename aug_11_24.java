/*1568. Minimum Number of Days to Disconnect Island
You are given an m x n binary grid grid where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.

 

Example 1:


Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]

Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:


Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 30
grid[i][j] is either 0 or 1. */
public class aug_11_24 {
public static void main(String args[]){

}
// Directions for adjacent cells: right, left, down, up
private static final int[][] DIRECTIONS = {
    { 0, 1 },
    { 0, -1 },
    { 1, 0 },
    { -1, 0 },
};

public int minDays(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    // Count initial islands
    int initialIslandCount = countIslands(grid);

    // Already disconnected or no land
    if (initialIslandCount != 1) {
        return 0;
    }

    // Try removing each land cell
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            if (grid[row][col] == 0) continue; // Skip water

            // Temporarily change to water
            grid[row][col] = 0;
            int newIslandCount = countIslands(grid);

            // Check if disconnected
            if (newIslandCount != 1) return 1;

            // Revert change
            grid[row][col] = 1;
        }
    }

    return 2;
}

private int countIslands(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int islandCount = 0;

    // Iterate through all cells
    for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
            // Found new island
            if (!visited[row][col] && grid[row][col] == 1) {
                exploreIsland(grid, row, col, visited);
                islandCount++;
            }
        }
    }
    return islandCount;
}

// Helper method to explore all cells of an island
private void exploreIsland(
    int[][] grid,
    int row,
    int col,
    boolean[][] visited
) {
    visited[row][col] = true;

    // Check all adjacent cells
    for (int[] direction : DIRECTIONS) {
        int newRow = row + direction[0];
        int newCol = col + direction[1];
        // Explore if valid land cell
        if (isValidLandCell(grid, newRow, newCol, visited)) {
            exploreIsland(grid, newRow, newCol, visited);
        }
    }
}

private boolean isValidLandCell(
    int[][] grid,
    int row,
    int col,
    boolean[][] visited
) {
    int rows = grid.length;
    int cols = grid[0].length;
    // Check bounds, land, and not visited
    return (
        row >= 0 &&
        col >= 0 &&
        row < rows &&
        col < cols &&
        grid[row][col] == 1 &&
        !visited[row][col]
    );
}
}