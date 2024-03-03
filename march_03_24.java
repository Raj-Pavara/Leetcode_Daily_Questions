/*Remove Nth Node From End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

Follow up: Could you do this in one pass?
 */

 
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
public class march_03_24 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
         head.next = new ListNode(2);
         head.next.next = new ListNode(3);
         removeNthFromEnd(head, 2);
         System.out.println(head.val +" "+head.next.val+"");
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
      if(head.next == null && n ==1){
          return null;
      }
      ListNode temp = head;
      int size = 0;
      while(temp!=null){
          size++;
          temp = temp.next;
      }
      if(size == n){head = head.next;
      return head;}
      temp = head;
      for(int i =0 ;i<size - n -1;i++){
          temp = temp.next;
      }
       temp.next = temp.next.next;
       return head;
        }
}