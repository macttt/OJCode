package LeetCode.ListNode;
/**
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 *
 * */
public class Leet024 {

    //自己想的循环的办法，代码比较难懂，但是却可以100%时间，100%空间
    //不得不说链表有时候确实要直观粗暴一点比较好
    public ListNode swapPairs(ListNode head) {
        ListNode tmphead = new ListNode(0);
        tmphead.next = head;
        ListNode ans = tmphead;
        ListNode tmp;
        while(tmphead.next!=null){
            if(tmphead.next.next==null) break;
            tmp = tmphead.next;
            tmphead.next = tmp.next;
            tmp.next = tmphead.next.next;
            tmphead.next.next = tmp;
            if(tmphead.next.next == head){
                ans.next = tmphead.next;
            }
            tmphead = tmp;
        }
        return ans.next;
    }

    /**
     * 更简单的submission,使用递归，非常非常简单的代码，耳目一新
     * */
    public ListNode swapPairsFromWeb(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        //当前节点对的第二节点的下一个是下一个翻转的头结点，递归处理
        head.next = swapPairsFromWeb(head.next.next);
        //n是当前链表节点对的第一个节点
        n.next = head;
        return n;
    }

    /**
     * 既然是链表转置，当然还可以用栈解决，在之后的25题尝试先用栈进行转置
     * */


    class ListNode{
        int val;
        ListNode next;
        public ListNode(int x){val=x;}
    }
}

