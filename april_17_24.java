/*Smallest String Starting From Leaf
 
You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.

Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

As a reminder, any shorter prefix of a string is lexicographically smaller.

For example, "ab" is lexicographically smaller than "aba".
A leaf of a node is a node that has no children.

Example 1:
Input: root = [0,1,2,3,4,3,4]
Output: "dba"

Example 2:
Input: root = [25,1,3,1,3,0,2]
Output: "adz"

Example 3:
Input: root = [2,2,1,null,1,0,null,0]
Output: "abc"
 
Constraints:

The number of nodes in the tree is in the range [1, 8500].
0 <= Node.val <= 25
 
*/
public class april_17_24 {

  public static void main(String args[]) {
    TreeNode treeNode = new TreeNode(0);
    treeNode.left = new TreeNode(1);
    treeNode.right = new TreeNode(2);
    treeNode.left.left = new TreeNode(3);
    treeNode.left.right = new TreeNode(4);
    treeNode.right.left = new TreeNode(3);
    treeNode.right.right = new TreeNode(4);
    // TreeNode treeNode  = new TreeNode(2);
    // treeNode.left = new TreeNode(2);
    // treeNode.right = new TreeNode(1);
    // // treeNode.left.left = new TreeNode(3);
    // treeNode.left.right = new TreeNode(1);
    // treeNode.right.left = new TreeNode(0);
    // // treeNode.right.right = new TreeNode(4);
    // treeNode.left.right.left = new TreeNode(0);
    System.out.println(smallestFromLeaf(treeNode));
  }

  public static String smallestFromLeaf(TreeNode root) {
    return smallStr(root, new StringBuilder()).toString();
  }

  public static StringBuilder smallStr(TreeNode root, StringBuilder sb) {
    if (root == null) {
      return sb;
    }
    sb.append((char) ('a' + root.val));
    if (root.left == null && root.right == null) {
      return sb.reverse();
    }
    StringBuilder sb1 = root.left != null
      ? smallStr(root.left, new StringBuilder(sb))
      : null;
    StringBuilder sb2 = root.right != null
      ? smallStr(root.right, new StringBuilder(sb))
      : null;
    if (sb1 != null && sb2 != null) {
      return sb1.toString().compareTo(sb2.toString()) < 0 ? sb1 : sb2;
    } else {
      return sb1 != null ? sb1 : sb2;
    }
  }
}

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
