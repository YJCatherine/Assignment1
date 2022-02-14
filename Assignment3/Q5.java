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
class Q5 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode A_end = mid(head);
        ListNode B_start = A_end.next;
        A_end.next = null;
        B_start = reverse(B_start);
        boolean res = compare(head, B_start);
        A_end.next = reverse(B_start);
        return res;
    }

    ListNode mid(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;
    }

    ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    boolean compare(ListNode A, ListNode B) {
        while (B != null) {
            if (A.val != B.val) return false;
            A = A.next;
            B = B.next;
        }
        return true;
    }
}


