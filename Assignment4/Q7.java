package Assignment4;


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Q7 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode superior = dummyHead;

        for ( int i = 1; i < m; i ++ ) {
            superior = superior.next;
        }
        ListNode prev = null;
        ListNode cur = superior.next;

        for ( int i = 0; i <= n-m; i ++ ) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        superior.next.next = cur;
        superior.next = prev;
        return dummyHead.next;
    }
}