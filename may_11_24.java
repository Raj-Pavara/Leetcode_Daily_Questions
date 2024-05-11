/* Minimum Cost to Hire K Workers

There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

Every worker in the paid group must be paid at least their minimum wage expectation.
In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], k = 2
Output: 105.00000
Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
Output: 30.66667
Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 
Constraints:

n == quality.length == wage.length
1 <= k <= n <= 104
1 <= quality[i], wage[i] <= 104

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class may_11_24 {
    public static void main(String args[]) {

    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double minCost = Double.MAX_VALUE;
        double qualityTillNow = 0;

        List<Worker> workers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            workers.add(new Worker(wage[i] / (double) quality[i], quality[i]));
        }

        // Sorting workers by their wage-quality ratio
        Collections.sort(workers);

        // Max-heap for the qualities using a comparator
        PriorityQueue<Integer> highQualityWorkers = new PriorityQueue<>(Comparator.reverseOrder());

        for (Worker worker : workers) {
            double ratio = worker.ratio;
            int qua = worker.quality;

            qualityTillNow += qua;
            highQualityWorkers.add(qua);

            if (highQualityWorkers.size() > k) {
                qualityTillNow -= highQualityWorkers.poll();
            }

            if (highQualityWorkers.size() == k) {
                minCost = Math.min(minCost, qualityTillNow * ratio);
            }
        }

        return minCost;
    }

    // Helper class to store the ratio and quality and implement comparable for
    // sorting
    private class Worker implements Comparable<Worker> {
        double ratio;
        int quality;

        Worker(double ratio, int quality) {
            this.ratio = ratio;
            this.quality = quality;
        }

        @Override
        public int compareTo(Worker other) {
            return Double.compare(this.ratio, other.ratio);
        }
    }

}