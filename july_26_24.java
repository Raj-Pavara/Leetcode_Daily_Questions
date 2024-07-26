/*1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

Example 1:

Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.

Example 2:

Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.

Constraints:

2 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti, distanceThreshold <= 10^4
All pairs (fromi, toi) are distinct.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class july_26_24 {
    public static void main(String args[]) {

    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // Adjacency list to store the graph
        List<int[]>[] adjacencyList = new List[n];
        // Matrix to store shortest path distances from each city
        int[][] shortestPathMatrix = new int[n][n];

        // Initialize adjacency list and shortest path matrix
        for (int i = 0; i < n; i++) {
            Arrays.fill(shortestPathMatrix[i], Integer.MAX_VALUE); // Set all distances to infinity
            shortestPathMatrix[i][i] = 0; // Distance to itself is zero
            adjacencyList[i] = new ArrayList<>();
        }

        // Populate the adjacency list with edges
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int weight = edge[2];
            adjacencyList[start].add(new int[] { end, weight });
            adjacencyList[end].add(new int[] { start, weight }); // For undirected graph
        }

        // Compute shortest paths from each city using Dijkstra's algorithm
        for (int i = 0; i < n; i++) {
            dijkstra(n, adjacencyList, shortestPathMatrix[i], i);
        }

        // Find the city with the fewest number of reachable cities within the distance
        // threshold
        return getCityWithFewestReachable(
                n,
                shortestPathMatrix,
                distanceThreshold);
    }

    // Dijkstra's algorithm to find shortest paths from a source city
    void dijkstra(
            int n,
            List<int[]>[] adjacencyList,
            int[] shortestPathDistances,
            int source) {
        // Priority queue to process nodes with the smallest distance first
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        priorityQueue.add(new int[] { source, 0 });
        Arrays.fill(shortestPathDistances, Integer.MAX_VALUE); // Set all distances to infinity
        shortestPathDistances[source] = 0; // Distance to source itself is zero

        // Process nodes in priority order
        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.remove();
            int currentCity = current[0];
            int currentDistance = current[1];
            if (currentDistance > shortestPathDistances[currentCity]) {
                continue;
            }

            // Update distances to neighboring cities
            for (int[] neighbor : adjacencyList[currentCity]) {
                int neighborCity = neighbor[0];
                int edgeWeight = neighbor[1];
                if (shortestPathDistances[neighborCity] > currentDistance + edgeWeight) {
                    shortestPathDistances[neighborCity] = currentDistance +
                            edgeWeight;
                    priorityQueue.add(
                            new int[] {
                                    neighborCity,
                                    shortestPathDistances[neighborCity],
                            });
                }
            }
        }
    }

    // Determine the city with the fewest number of reachable cities within the
    // distance threshold
    int getCityWithFewestReachable(
            int n,
            int[][] shortestPathMatrix,
            int distanceThreshold) {
        int cityWithFewestReachable = -1;
        int fewestReachableCount = n;

        // Count number of cities reachable within the distance threshold for each city
        for (int i = 0; i < n; i++) {
            int reachableCount = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                } // Skip self
                if (shortestPathMatrix[i][j] <= distanceThreshold) {
                    reachableCount++;
                }
            }
            // Update the city with the fewest reachable cities
            if (reachableCount <= fewestReachableCount) {
                fewestReachableCount = reachableCount;
                cityWithFewestReachable = i;
            }
        }
        return cityWithFewestReachable;
    }
}