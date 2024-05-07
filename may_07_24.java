/* Double a Number Represented as a Linked List

You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.

Return the head of the linked list after doubling it.

 

Example 1:


Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
Example 2:


Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998. 
 

Constraints:

The number of nodes in the list is in the range [1, 104]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
 */
public class may_07_24 {
    public static void main(String args[]) {

    }

    public static ListNode doubleIt(ListNode head) {
        int t = fun(head); 
        if (t != 0) {
            ListNode node = new ListNode(t);
            node.next = head;
            return node;
        }
        return head;
    }

    public static int fun(ListNode head) {
        if (head.next == null) {
            int a = head.val * 2 / 10;
            head.val = (head.val * 2) % 10;
            return a;
        }
        int temp = fun(head.next);
        head.val = head.val * 2 + temp;
        int a = head.val / 10;
        head.val = head.val % 10;
        return a;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}