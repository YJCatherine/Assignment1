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
class Q1 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode begin = list1;
        ListNode end = list1;
        ListNode cur= list2;
        for(int i=0;i<a-1;i++){
            begin=begin.next;
        }
        for(int j=0;j<b+1;j++){
            end=end.next;
        }
        begin.next=list2;
        while(cur.next!=null){
            cur=cur.next;
        }
        cur.next=end;
        return list1;
    }
}
