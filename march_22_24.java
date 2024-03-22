/* Palindrome Linked List

Given the head of a singly linked list, return true if it is a 
palindrome or false otherwise.

Example 1:

Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false
 

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9
 

Follow up: Could you do it in O(n) time and O(1) space?
 */

import java.util.ArrayList;

public class march_22_24{
public static void main(String args[]){

}
public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ArrayList<Integer> al = new ArrayList<>();
        
        while (slow != null) {
            al.add(slow.val);
            slow = slow.next;
        }

        for (int i = 0; i < al.size()/2; i++) {
            if(al.get(i) != al.get(al.size() - 1 - i)){
                return false;
            }
        }
        return true;       
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 