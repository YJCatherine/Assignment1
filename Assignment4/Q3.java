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
class Q3 {
    public ListNode reverseEvenLengthGroups(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        int len = list.size();
        for(int i = 0, n = 1 ; i < len ; n++){
            int j = Math.min(len - 1, i + n - 1);
            if((j - i + 1) % 2 == 0){
                reverse(list, i, j);
            }
            i = j + 1;
        }

        for(int i = 0 ; i < len-1 ; i++){
            list.get(i).next = list.get(i+1);
        }
        list.get(len-1).next = null;
        return list.get(0);
    }

    void reverse(List<ListNode> list, int start, int end){
        while(start < end){
            ListNode tmp = list.get(start);
            list.set(start++, list.get(end));
            list.set(end--, tmp);
        }
    }
}