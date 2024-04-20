/*Find All Groups of Farmland
You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.

To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.

land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].

Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.

Example 1:

Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
Example 2:

Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
Example 3:

Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.
 

Constraints:

m == land.length
n == land[i].length
1 <= m, n <= 300
land consists of only 0's and 1's.
Groups of farmland are rectangular in shape.
 */

import java.util.ArrayList;

public class april_20_24 {

  public static void main(String args[]) {
    int arr[][] = { { 1, 0, 0 }, { 0, 1, 1 }, { 0, 1, 1 } };
    findFarmland(arr);
  }

  public static int[][] findFarmland(int[][] land) {

    boolean marked[][] = new boolean[land.length][land[0].length];

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < land.length; i++) {
        for (int j = 0; j < land[0].length; j++) {
            if(!marked[i][j] && land[i][j] == 1){
                ArrayList<Integer> temp = new ArrayList<>();
                int I = i;
                while (I<land.length) {
                    if(land[I][j] == 1){
                        I++;
                    }
                    else{
                        break;
                    }
                }
                int J = j;
                while (J<land[0].length) {
                    if(land[i][J] == 1){
                        J++;
                    }
                    else{
                        break;
                    }
                }
                for (int k = i; k < I; k++) {
                     for (int k2 = j; k2 < J; k2++) {
                        marked[k][k2] = true;
                     }
                }

                temp.add(i);
                temp.add(j);
                temp.add(I-1);
                temp.add(J-1);
                ans.add(temp);
                
            }
        }
    }

    int arrans[][] = new int[ans.size()][4];

    for (int i = 0; i < ans.size(); i++) {
        for (int j = 0; j < 4; j++) {
            arrans[i][j] = ans.get(i).get(j);
        }
    }
    return arrans;

  }
}
