/*2096. Step-By-Step Directions From a Binary Tree Node to Another

You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

Example 1:

Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Example 2:

Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 
Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue 
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class july_16_24 {
    public static void main(String args[]) {

    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        // Map to store parent nodes
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        // Find the start node and populate parent map
        TreeNode startNode = findStartNode(root, startValue);
        populateParentMap(root, parentMap);

        // Perform BFS to find the path
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(startNode);
        Set<TreeNode> visitedNodes = new HashSet<>();
        // Key: next node, Value: <current node, direction>
        Map<TreeNode, Pair<TreeNode, String>> pathTracker = new HashMap<>();
        visitedNodes.add(startNode);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // If destination is reached, return the path
            if (currentNode.val == destValue) {
                return backtrackPath(currentNode, pathTracker);
            }

            // Check and add parent node
            if (parentMap.containsKey(currentNode.val)) {
                TreeNode parentNode = parentMap.get(currentNode.val);
                if (!visitedNodes.contains(parentNode)) {
                    queue.add(parentNode);
                    pathTracker.put(parentNode, new Pair());
                    visitedNodes.add(parentNode);
                }
            }

            // Check and add left child
            if (
                currentNode.left != null &&
                !visitedNodes.contains(currentNode.left)
            ) {
                queue.add(currentNode.left);
                pathTracker.put(currentNode.left, new Pair());
                visitedNodes.add(currentNode.left);
            }

            // Check and add right child
            if (
                currentNode.right != null &&
                !visitedNodes.contains(currentNode.right)
            ) {
                queue.add(currentNode.right);
                pathTracker.put(currentNode.right, new Pair());
                visitedNodes.add(currentNode.right);
            }
        }

        // This line should never be reached if the tree is valid
        return "";
    }

    private String backtrackPath(
        TreeNode node,
        Map<TreeNode, Pair<TreeNode, String>> pathTracker
    ) {
        StringBuilder path = new StringBuilder();

        // Construct the path
        while (pathTracker.containsKey(node)) {
            // Add the directions in reverse order and move on to the previous node
            path.append(pathTracker.get(node).getValue());
            node = pathTracker.get(node).getKey();
        }

        // Reverse the path
        path.reverse();

        return path.toString();
    }

    private void populateParentMap(
        TreeNode node,
        Map<Integer, TreeNode> parentMap
    ) {
        if (node == null) return;

        // Add children to the map and recurse further
        if (node.left != null) {
            parentMap.put(node.left.val, node);
            populateParentMap(node.left, parentMap);
        }

        if (node.right != null) {
            parentMap.put(node.right.val, node);
            populateParentMap(node.right, parentMap);
        }
    }

    private TreeNode findStartNode(TreeNode node, int startValue) {
        if (node == null) return null;

        if (node.val == startValue) return node;

        TreeNode leftResult = findStartNode(node.left, startValue);

        // If left subtree returns a node, it must be StartNode. Return it
        // Otherwise, return whatever is returned by right subtree.
        if (leftResult != null) return leftResult;
        return findStartNode(node.right, startValue);
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