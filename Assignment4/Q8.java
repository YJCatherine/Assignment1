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
class Q8 {
    public ListNode[] splitListToParts(ListNode head, int k) {

        int len = 0;
        ListNode head1 = head;
        while(head1 != null){
            len++;
            head1 = head1.next;
        }

        int n = len / k,surplus = len % k;
        ListNode[] ans = new ListNode[k];

        int index = 0,cnt = 0;
        while( index < k){
            int klen = n;
            if(index < surplus){
                klen++;
            }
            if(klen <= 0 ){
                break;
            }
            ans[index] = head;
            ListNode tmp = ans[index];
            for(int i = 1;i < klen;i++){
                tmp = tmp.next;
            }
            head = tmp.next;
            tmp.next = null;
            index++;
        }
        return ans;
    }
}