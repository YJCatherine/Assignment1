package Assignment3;

//138. Copy List with Random Pointer

/*
 //Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
 */

public class Q2 {
    public Node copyRandomList(Node head) {
        Node temp = head;
        Node prev = null;
        Node NL = null;
        while(temp!=null){
            Node y = new Node(temp.val);
            if(temp==head){
                NL = y;
            }
            if(prev!=null){
                prev.next = y;
            }
            prev = y;
            temp = temp.next;
        }
        temp = NL;
        Node sechead = head;
        while(temp!=null){
            Node tempo = head;
            Node tempy = NL;
            while(tempo!=null && tempy!=null && tempo!=sechead.random){
                tempo = tempo.next;
                tempy = tempy.next;
            }
            sechead = sechead.next;
            temp.random = tempy;
            temp = temp.next;
        }
        return NL;
    }
}
