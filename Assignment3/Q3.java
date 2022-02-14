package Assignment3;

// 23. Merge k Sorted Lists
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

public class Q3 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a,b)->(a.val - b.val));

        for (ListNode head : lists){
            if (head != null)
                pq.add(head);
        }

        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null){
                pq.add(node.next);
            }
            p=p.next;

        }
        return dummy.next;
    }
}
