package Assignment3;

/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Q6 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode quick = head;
        ListNode parent = slow;

        while (n-- != 0) quick = quick.next;

        while (quick != null) {
            parent = slow;
            slow = slow.next;
            quick = quick.next;
        }

        if (slow == head) head = head.next;
        else {
            parent.next = slow.next;
        }

        return head;
    }
}