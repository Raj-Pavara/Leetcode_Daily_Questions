/*1380. Lucky Numbers in a Matrix

Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

Example 1:

Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 2:

Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.

Example 3:

Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
 
Constraints:

m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 105.
All elements in the matrix are distinct.
 */

import java.util.ArrayList;
import java.util.List;

public class july_19_24 {
    public static void main(String args[]) {

    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        int N = matrix.length, M = matrix[0].length;

        List<Integer> rowMin = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int rMin = Integer.MAX_VALUE;
            for (int j = 0; j < M; j++) {
                rMin = Math.min(rMin, matrix[i][j]);
            }
            rowMin.add(rMin);
        }

        List<Integer> colMax = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int cMax = Integer.MIN_VALUE;
            for (int j = 0; j < N; j++) {
                cMax = Math.max(cMax, matrix[j][i]);
            }
            colMax.add(cMax);
        }

        List<Integer> luckyNumbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == rowMin.get(i) && matrix[i][j] == colMax.get(j)) {
                    luckyNumbers.add(matrix[i][j]);
                }
            }
        }

        return luckyNumbers;
    }
}
