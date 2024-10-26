/*2458. Height of Binary Tree After Subtree Removal Queries

You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.

You have to perform m independent queries on the tree where in the ith query you do the following:

Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

Note:

The queries are independent, so the tree returns to its initial state after each query.
The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.
 
Example 1:

Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
Output: [2]
Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
The height of the tree is 2 (The path 1 -> 3 -> 2).

Example 2:

Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
Output: [3,2,3,2]
Explanation: We have the following queries:
- Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
- Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
- Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
- Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).
 
Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
m == queries.length
1 <= m <= min(n, 104)
1 <= queries[i] <= n
queries[i] != root.val
 */
public class oct_26_24 {
    public static void main(String args[]) {

    }

    // Arrays to store information about the tree
    private int[] heights; // Stores heights of leaf nodes in order of traversal
    private int[] d; // Stores depth/height of each node by its value
    private int[] l; // Stores left boundary index for each node value
    private int[] r; // Stores right boundary index for each node value
    private int lenLeaves; // Counts number of leaf nodes encountered

    public int[] treeQueries(TreeNode root, int[] queries) {
        // Initialize arrays with sufficient size
        heights = new int[50000]; // For storing leaf heights
        d = new int[100001]; // For storing node depths
        l = new int[100001]; // For storing left boundaries
        r = new int[100001]; // For storing right boundaries
        lenLeaves = 0; // Initialize leaf counter

        // DFS to process the tree and fill arrays
        search(root, 0);

        int n = lenLeaves;
        // Arrays to store maximum heights from left and right
        int[] maxl = new int[n]; // Prefix maximums
        int[] maxr = new int[n]; // Suffix maximums

        // Build prefix maximum array (left to right)
        // At each position i, stores max height of all leaves to the left
        for (int i = 1; i < n; i++) {
            maxl[i] = Math.max(maxl[i - 1], heights[i - 1]);
        }

        // Build suffix maximum array (right to left)
        // At each position i, stores max height of all leaves to the right
        for (int i = n - 2; i >= 0; i--) {
            maxr[i] = Math.max(maxr[i + 1], heights[i + 1]);
        }

        // Process each query
        int[] ret = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            // Find maximum height outside the range of current node
            int maxxl = maxl[l[query]]; // Max height to the left
            int maxxr = maxr[r[query]]; // Max height to the right
            // Result is maximum of left max, right max, and parent height
            ret[i] = Math.max(Math.max(maxxl, maxxr), d[query] - 1);
        }

        return ret;
    }

    // DFS to process the tree
    private void search(TreeNode p, int h) {
        d[p.val] = h; // Store current node's height

        // If current node is a leaf
        if (p.left == null && p.right == null) {
            heights[lenLeaves] = h; // Store leaf height
            l[p.val] = r[p.val] = lenLeaves; // Both boundaries point to same index
            lenLeaves++; // Increment leaf counter
            return;
        }

        l[p.val] = lenLeaves; // Store left boundary

        // Recursively process left and right subtrees
        if (p.left != null) {
            search(p.left, h + 1);
        }
        if (p.right != null) {
            search(p.right, h + 1);
        }

        r[p.val] = lenLeaves - 1; // Store right boundary
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}