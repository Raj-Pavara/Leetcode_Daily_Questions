/*2196. Create Binary Tree From Descriptions
You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

 

Example 1:


Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.
Example 2:


Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.
 

Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class july_15_24 {
    public static void main(String args[]) {

    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        // Sets to track unique children and parents
        Set<Integer> children = new HashSet<>(), parents = new HashSet<>();
        // Map to store parent to children relationships
        Map<Integer, List<int[]>> parentToChildren = new HashMap<>();

        // Build graph from parent to child, and add nodes to HashSets
        for (int[] d : descriptions) {
            int parent = d[0], child = d[1], isLeft = d[2];
            parents.add(parent);
            parents.add(child);
            children.add(child);
            parentToChildren
                    .computeIfAbsent(parent, l -> new ArrayList<>())
                    .add(new int[] { child, isLeft });
        }

        // Find the root node by checking which node is in parents but not in children
        parents.removeAll(children);
        TreeNode root = new TreeNode(parents.iterator().next());

        // Starting from root, use BFS to construct binary tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();
            // Iterate over children of current parent
            for (int[] childInfo : parentToChildren.getOrDefault(
                    parent.val,
                    Collections.emptyList())) {
                int childValue = childInfo[0], isLeft = childInfo[1];
                TreeNode child = new TreeNode(childValue);
                queue.offer(child);
                // Attach child node to its parent based on isLeft flag
                if (isLeft == 1) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        }

        return root;
    }
}