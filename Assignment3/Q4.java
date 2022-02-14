package Assignment3;

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
        if (recurTimes == 0) { // 找到链表中点
            ListNode curr = cardinal ? node.next : node;
            if (cardinal) {
                node.next = null; // 去掉链表中点的next指向（链表长度为基数）
            }
            return curr;
        }

        ListNode nextNode = node.next;
        ListNode result = this.recurMerge(nextNode, recurTimes - 1, cardinal);

        if (nextNode == result) {
            node.next = null; // 去掉链表中点的next指向（链表长度为偶数）
        }

        ListNode originNext = node.next;
        node.next = result;
        ListNode originResultNext = result.next;
        result.next = originNext;

        return originResultNext;
    }
}
