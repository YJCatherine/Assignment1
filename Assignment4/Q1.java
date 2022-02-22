package Assignment4;

* public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Q1 {
    public ListNode mergeInBetween(ListNode l1, int a, int b, ListNode l2) {

        // p points to ath-1 element of list 1 (l1)
        // q points to bth+1 element of list 1 (l1)
        // r points to last element of list 2 (l2)

        ListNode p = l1;

        for(int i=0; i<a-1; i++)
            p = p.next;

        ListNode q = p;
        for(int i=a; i<=b+1; i++)
            q = q.next;

        ListNode r = l2;
        while(r.next != null)
            r = r.next;

        p.next = l2;
        r.next = q;

        return l1;
    }
}
