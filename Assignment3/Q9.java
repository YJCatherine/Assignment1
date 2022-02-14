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
class Q9 {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        while (head != null) {
            int ele = head.val;
            while (!stack.isEmpty() && list.get(stack.peek()) < ele) {
                list.set(stack.pop(), ele);
            }

            stack.push(index);
            list.add(ele);
            index++;
            head = head.next;
        }

        while (!stack.isEmpty()) {
            list.set(stack.pop(), 0);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
