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
class Q9 {
    public int numComponents(ListNode head, int[] G) {
        int[] set = new int[10000];
        for(int e:G){
            set[e] = 1;
        }

        ListNode ptr = head;
        int res = 0;
        while(ptr!=null){
            ListNode temp = ptr;
            while(ptr!=null&&set[ptr.val]==1){
                ptr = ptr.next;
            }
            if(ptr!=temp){
                res++;
            }else{
                ptr=ptr.next;
            }

        }

        return res;
    }
}