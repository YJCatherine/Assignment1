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
public class Q4 {
    public void reorderList(ListNode head) {
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }


        int recurTimes = len % 2 == 0 ? len / 2 : (len - 1) / 2;

        boolean cardinal = len % 2 != 0;

        this.recurMerge(head, recurTimes, cardinal);
    }

    private ListNode recurMerge(ListNode node, int recurTimes, boolean cardinal) {
        if (recurTimes == 0) { 
            ListNode curr = cardinal ? node.next : node;
            if (cardinal) {
                node.next = null; 
            }
            return curr;
        }

        ListNode nextNode = node.next;
        ListNode result = this.recurMerge(nextNode, recurTimes - 1, cardinal);

        if (nextNode == result) {
            node.next = null; 
        }

        ListNode originNext = node.next;
        node.next = result;
        ListNode originResultNext = result.next;
        result.next = originNext;

        return originResultNext;
    }
}
