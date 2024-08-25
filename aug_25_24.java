/*145. Binary Tree Postorder Traversal
Given the root of a binary tree, return the postorder traversal of its nodes' values.

Example 1:

Input: root = [1,null,2,3]
Output: [3,2,1]

Example 2:

Input: root = []
Output: []

Example 3:

Input: root = [1]
Output: [1]
 
Constraints:

The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 

Follow up: Recursive solution is trivial, could you do it iteratively? */

import java.util.ArrayList;
import java.util.List;

public class aug_25_24 {
    public static void main(String args[]) {

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        fun(root, result);
        return result;

    }

    private void fun(TreeNode root, List<Integer> result) {
        if (root != null) {
            fun(root.left, result);
            fun(root.right, result);
            result.add(root.val);
        }
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