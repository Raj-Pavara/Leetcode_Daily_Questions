/*Remove Zero Sum Consecutive Nodes from Linked List
 
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

 

(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:

Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.
Example 2:

Input: head = [1,2,3,-3,4]
Output: [1,2,4]
Example 3:

Input: head = [1,2,3,-3,-2]
Output: [1]
 

Constraints:

The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class march_12_24 {

  public static void main(String args[]) {
     
  }

  public static ListNode removeZeroSumSublists(ListNode head) {
    ListNode dummy = new ListNode(0, head);
    int prefix = 0;
    Map<Integer, ListNode> prefixToNode = new HashMap<>();
    prefixToNode.put(0, dummy);

    for (; head != null; head = head.next) {
      prefix += head.val;
      prefixToNode.put(prefix, head);
    }

    prefix = 0;

    for (head = dummy; head != null; head = head.next) {
      prefix += head.val;
      head.next = prefixToNode.get(prefix).next;
    }

    return dummy.next;
            
  }
}

class ListNode {

  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
