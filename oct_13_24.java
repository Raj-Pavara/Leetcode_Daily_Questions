/*632. Smallest Range Covering Elements from K Lists

You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

Example 1:

Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Example 2:

Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]
 
Constraints:

nums.length == k
1 <= k <= 3500
1 <= nums[i].length <= 50
-105 <= nums[i][j] <= 105
nums[i] is sorted in non-decreasing order.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class oct_13_24 {
    public static void main(String args[]) {

    }

    private boolean chk(int a, int b, int c, int d) {
        // if new numbers are answer
        if (b - a < d - c) {
            return true;
        } else if (b - a == d - c) {
            if (a < c) {
                return true;
            }
        }
        // new number doesn't have minimum difference uptill now
        return false;
    }

    public int[] smallestRange(List<List<Integer>> a) {
        int ansx = -100000, ansy = 100000;
        int total_list = a.size();

        List<int[]> vp = new ArrayList<>();
        for (int i = 0; i < total_list; i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                vp.add(new int[] { a.get(i).get(j), i });
            }
        }

        vp.sort(Comparator.comparingInt(x -> x[0]));

        Map<Integer, Integer> um = new HashMap<>();
        int l = 0, r = 0;
        int n = vp.size();
        while (r < n) {
            int num = vp.get(r)[0];
            int list = vp.get(r)[1];

            um.put(list, um.getOrDefault(list, 0) + 1);

            while (um.size() >= total_list) {
                if (chk(vp.get(l)[0], vp.get(r)[0], ansx, ansy)) {
                    ansx = vp.get(l)[0];
                    ansy = vp.get(r)[0];
                }

                int old_list = vp.get(l)[1];
                um.put(old_list, um.get(old_list) - 1);
                if (um.get(old_list) == 0) {
                    um.remove(old_list);
                }
                l++;
            }

            r++;
        }

        return new int[] { ansx, ansy };
    }
}