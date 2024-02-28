/* Find Bottom Left Tree Value


Given the root of a binary tree, return the leftmost value in the last row of the tree.

 

Example 1:


Input: root = [2,1,3]
Output: 1
Example 2:


Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */

import java.util.LinkedList;
import java.util.Queue;

public class feb_28_24 {
    public static void main(String[] args) {


        TreeNode root = new TreeNode(1);
        root.left =  new TreeNode(2);
        root.right =  new TreeNode(3);

        System.out.println(findBottomLeftValue(root));
        
    }

    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int ans = root.val;
        System.out.println(q);
        while (!q.isEmpty()) {
            TreeNode temp = q.remove();
            if(temp.right != null){ q.add(temp.right);  ans = temp.right.val;}
            if(temp.left != null) {q.add(temp.left); ans = temp.left.val;}
        }
        return ans;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 