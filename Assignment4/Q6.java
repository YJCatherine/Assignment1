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
class Q6 {
    private ListNode head;
    public Solution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int res = head.val;
        ListNode no = head.next;
        int i = 2;
        Random random = new Random();
        while(no!=null){
            if(random.nextInt(i) == 0){
                res = no.val;
            }
            i++;
            no = no.next;
        }
        return res;

    }
}
