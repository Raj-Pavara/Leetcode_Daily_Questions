/*Reorder List
 
You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:

Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:

Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */
public class march_23_24{
public static void main(String args[]){

}

public static void reorderList(ListNode head) {
    if(head == null || head.next == null) return;

ListNode slow = head, fast = head;
while(fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}


ListNode prev = null, next = null;
while(slow != null) {
    next = slow.next;
    slow.next = prev;
    prev = slow;
    slow = next;
}

ListNode firstHf = head;
ListNode secondHf = prev;

while(secondHf.next != null) {
    next = firstHf.next;
    prev = secondHf.next;

    firstHf.next = secondHf;
    secondHf.next = next;

    firstHf = next;
    secondHf = prev;
}
}

}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 