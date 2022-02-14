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
class Q10 {
    public ListNode deleteDuplicates(ListNode head) {

        if(head == null){
            return null;
        }

        while(head != null && head.next != null && head.next.val == head.val){
            while(head.next != null && head.next.val == head.val){
                head = head.next;
            }
            head = head.next;
        }
        if(head == null){
            return head;
        }

        head.next = deleteDuplicates(head.next);
        return head;
    }
}