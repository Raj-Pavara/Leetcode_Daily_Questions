/*624. Maximum Distance in Arrays

You are given m arrays, where each array is sorted in ascending order.

You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.

Return the maximum distance.

Example 1:

Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Example 2:

Input: arrays = [[1],[1]]
Output: 0
 
Constraints:

m == arrays.length
2 <= m <= 105
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays. 
*/

import java.util.List;

public class aug_16_24 {
    public static void main(String args[]) {

    }

    public int maxDistance(List<List<Integer>> arrays) {
        List<Integer> currentList;
        int minIdx1, minIdx2, maxIdx1, maxIdx2;
        minIdx1 = (arrays.get(0).get(0) < arrays.get(1).get(0)) ? 0 : 1;
        minIdx2 = minIdx1 == 0 ? 1 : 0;
        maxIdx1 = arrays.get(0).get(arrays.get(0).size() - 1) < arrays.get(1).get(arrays.get(1).size() - 1) ? 1 : 0;
        maxIdx2 = maxIdx1 == 0 ? 1 : 0;
        for (int i = 2; i < arrays.size(); i++) {
            int cf = arrays.get(i).get(0);
            if (cf < arrays.get(minIdx1).get(0)) {
                minIdx2 = minIdx1;
                minIdx1 = i;
            } else if (cf < arrays.get(minIdx2).get(0)) {
                minIdx2 = i;
            }
            int cl = arrays.get(i).get(arrays.get(i).size() - 1);
            if (cl > arrays.get(maxIdx1).get(arrays.get(maxIdx1).size() - 1)) {
                maxIdx2 = maxIdx1;
                maxIdx1 = i;
            } else if (cl > arrays.get(maxIdx2).get(arrays.get(maxIdx2).size() - 1)) {
                maxIdx2 = i;
            }
        }
        int ans = 0;
        if (minIdx1 == maxIdx1) {
            ans = arrays.get(maxIdx2).get(arrays.get(maxIdx2).size() - 1) - arrays.get(minIdx1).get(0);
            if (ans < arrays.get(maxIdx1).get(arrays.get(maxIdx1).size() - 1) - arrays.get(minIdx2).get(0)) {
                ans = arrays.get(maxIdx1).get(arrays.get(maxIdx1).size() - 1) - arrays.get(minIdx2).get(0);
            }
        } else {
            return arrays.get(maxIdx1).get(arrays.get(maxIdx1).size() - 1) - arrays.get(minIdx1).get(0);
        }
        return ans;
    }
}