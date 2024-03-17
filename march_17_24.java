/*Insert Interval
 
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class march_17_24{
public static void main(String args[]){

}
public static int[][] insert(int[][] intervals, int[] newInterval) {

    // Step 1: Convert array to list
        List<int[]> intervalsList = new ArrayList<>(Arrays.asList(intervals));
        // Add new interval to the list
        intervalsList.add(newInterval);
        // Sort intervals based on start value
        Collections.sort(intervalsList, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Merge intervals
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervalsList) {
            if (ansList.size() == 0 || ansList.get(ansList.size() - 1)[1] < interval[0]) {
                ansList.add(interval);
            } else if (ansList.get(ansList.size() - 1)[1] >= interval[0]) {
                ansList.get(ansList.size() - 1)[1] = Math.max(ansList.get(ansList.size() - 1)[1], interval[1]);
            }
        }

        // Step 3: Convert list back to array
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    
    }
}