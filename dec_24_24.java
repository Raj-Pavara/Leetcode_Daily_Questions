
/*3203. Find Minimum Diameter After Merging Two Trees

There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

You must connect one node from the first tree with another node from the second tree with an edge.

Return the minimum possible diameter of the resulting tree.

The diameter of a tree is the length of the longest path between any two nodes in the tree.

Example 1:

Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]

Output: 3

Explanation:

We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

Example 2:

Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]

Output: 5

Explanation:

We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.

Constraints:

1 <= n, m <= 105
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.
 */
import java.util.ArrayList;

public class dec_24_24 {
    public static void main(String args[]) {

    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        // Step 1: Construct adjacency lists for tree1 and tree2
        int n1 = edges1.length + 1; // Number of nodes in tree1
        ArrayList<ArrayList<Integer>> tree1 = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            tree1.add(new ArrayList<>());
        }
        for (int[] edge : edges1) {
            int u = edge[0];
            int v = edge[1];
            tree1.get(u).add(v);
            tree1.get(v).add(u);
        }

        int n2 = edges2.length + 1; // Number of nodes in tree2
        ArrayList<ArrayList<Integer>> tree2 = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            tree2.add(new ArrayList<>());
        }
        for (int[] edge : edges2) {
            int u = edge[0];
            int v = edge[1];
            tree2.get(u).add(v);
            tree2.get(v).add(u);
        }

        // Step 2: Calculate diameters of tree1 and tree2
        int diameter1 = findDiameter(tree1, n1);
        int diameter2 = findDiameter(tree2, n2);

        // Step 3: Calculate the merged tree's diameter
        int totalDiameter = (int) Math.ceil(diameter1 / 2.0) + (int) Math.ceil(diameter2 / 2.0) + 1;

        // Step 4: Return the maximum of the two diameters and the merged diameter
        return Math.max(Math.max(diameter1, diameter2), totalDiameter);
    }

    // Method to find the diameter of a tree
    int findDiameter(ArrayList<ArrayList<Integer>> tree, int n) {
        // First DFS to find the farthest node from any node (e.g., node 0)
        boolean[] visited1 = new boolean[n];
        int[] res1 = new int[2]; // Stores farthest node and its distance
        dfs(0, 0, tree, visited1, res1);

        // Second DFS from the farthest node found
        int farthestNode = res1[0];
        boolean[] visited2 = new boolean[n];
        int[] res2 = new int[2]; // Stores farthest distance
        dfs(farthestNode, 0, tree, visited2, res2);

        // Return the farthest distance (diameter)
        return res2[1];
    }

    // Recursive DFS to find the farthest node and distance
    void dfs(int node, int distance, ArrayList<ArrayList<Integer>> tree, boolean[] visited, int[] res) {
        visited[node] = true;

        // Update the farthest distance and node if necessary
        if (distance > res[1]) {
            res[0] = node;
            res[1] = distance;
        }

        // Traverse all unvisited neighbors
        for (int neighbor : tree.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, distance + 1, tree, visited, res);
            }
        }
    }
}