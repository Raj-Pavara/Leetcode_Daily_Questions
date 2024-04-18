/*Island Perimeter 

You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example 1:

Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4
 
Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
*/

public class april_18_24 {

  public static void main(String args[]) {
    int arr[][] = {
      { 0, 1, 0, 0 },
      { 1, 1, 1, 0 },
      { 0, 1, 0, 0 },
      { 1, 1, 0, 0 },
    };
    System.out.println(islandPerimeter(arr));
  }

  public static int islandPerimeter(int[][] arr) {
    int ans = 0;

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] == 1) {
          if (i == 0) {
            ans++;
          }
          if (i == arr.length - 1) {
            ans++;
          }
          if (j == 0) {
            ans++;
          }
          if (j == arr[0].length - 1) {
            ans++;
          }
          if(i!=0){
            if(arr[i-1][j] == 0){
                ans++;
            }
          }
          if(i!=arr.length -1){
            if(arr[i+1][j]==0){
                ans++;
            }
          }
          if(j !=0){
            if(arr[i][j-1] ==0){
                ans++;
            }
          }
          if(j!=arr[0].length -1){
            if(arr[i][j+1] ==0){
                ans++;
            }
          }
        }
      }
    }
    return ans;
  }
}
