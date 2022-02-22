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
class Q4 {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return new int[]{-1, -1};
        int min = Integer.MAX_VALUE, start = -1, end = -1, index = 1;
        ListNode parent = head;
        ListNode cur = parent.next;
        while (cur.next != null) {
            index++;
            if ((cur.val > parent.val && cur.val > cur.next.val) || (cur.val < parent.val && cur.val < cur.next.val)) {
                if (start == -1) start = index;
                else if (end == -1) {
                    end = index;
                    min = end - start;
                } else {
                    min = Math.min(index - end, min);
                    end = index;
                }
            }
            parent = cur;
            cur = cur.next;
        }
        if (end == -1) return new int[]{-1, -1};
        return new int[]{min, end - start};
    }
}