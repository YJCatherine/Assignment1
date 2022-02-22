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
class Q10 {
    public int pairSum(ListNode head) {
        int num[]=new int[100005];
        int count=0;
        while(head!=null){
            num[count]=head.val;
            head=head.next;
            count++;
        }

        int ans=0;
        for(int i=0;i<count/2;i++){
            ans=Math.max(ans,num[i]+num[count-i-1]);
        }
        return ans;
    }
}