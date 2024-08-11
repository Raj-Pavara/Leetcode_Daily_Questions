/*959. Regions Cut By Slashes
An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'. 

Example 1:

Input: grid = [" /","/ "]
Output: 2

Example 2:

Input: grid = [" /","  "]
Output: 1

Example 3:

Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 
Constraints:

n == grid.length == grid[i].length
1 <= n <= 30
grid[i][j] is either '/', '\', or ' '. 
*/

import java.util.LinkedList;
import java.util.Queue;

public class aug_10_24 {
    public static void main(String args[]) {
        String arr[] = { " /", "  " };
        System.out.println(regionsBySlashes(arr));
    }

    // Directions for traversal: right, left, down, up
    private static final int[][] DIRECTIONS = {
        { 0, 1 },
        { 0, -1 },
        { 1, 0 },
        { -1, 0 },
    };

    public static int regionsBySlashes(String[] grid) {
        int gridSize = grid.length;
        // Create a 3x3 matrix for each cell in the original grid
        int[][] expandedGrid = new int[gridSize * 3][gridSize * 3];

        // Populate the expanded grid based on the original grid
        // 1 represents a barrier in the expanded grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int baseRow = i * 3;
                int baseCol = j * 3;
                // Check the character in the original grid
                if (grid[i].charAt(j) == '\\') {
                    // Mark diagonal for backslash
                    expandedGrid[baseRow][baseCol] = 1;
                    expandedGrid[baseRow + 1][baseCol + 1] = 1;
                    expandedGrid[baseRow + 2][baseCol + 2] = 1;
                } else if (grid[i].charAt(j) == '/') {
                    // Mark diagonal for forward slash
                    expandedGrid[baseRow][baseCol + 2] = 1;
                    expandedGrid[baseRow + 1][baseCol + 1] = 1;
                    expandedGrid[baseRow + 2][baseCol] = 1;
                }
            }
        }

        int regionCount = 0;
        // Count regions using flood fill
        for (int i = 0; i < gridSize * 3; i++) {
            for (int j = 0; j < gridSize * 3; j++) {
                // If we find an unvisited cell (0), it's a new region
                if (expandedGrid[i][j] == 0) {
                    // Fill that region
                    floodFill(expandedGrid, i, j);
                    regionCount++;
                }
            }
        }
        return regionCount;
    }

    // Flood fill algorithm to mark all cells in a region
    private static void floodFill(int[][] expandedGrid, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        expandedGrid[row][col] = 1;
        queue.add(new int[] { row, col });

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            // Check all four directions from the current cell
            for (int[] direction : DIRECTIONS) {
                int newRow = direction[0] + currentCell[0];
                int newCol = direction[1] + currentCell[1];
                // If the new cell is valid and unvisited, mark it and add to queue
                if (isValidCell(expandedGrid, newRow, newCol)) {
                    expandedGrid[newRow][newCol] = 1;
                    queue.add(new int[] { newRow, newCol });
                }
            }
        }
    }

    // Check if a cell is within bounds and unvisited
    private static boolean isValidCell(int[][] expandedGrid, int row, int col) {
        int n = expandedGrid.length;
        return (
            row >= 0 &&
            col >= 0 &&
            row < n &&
            col < n &&
            expandedGrid[row][col] == 0
        );
    }

}