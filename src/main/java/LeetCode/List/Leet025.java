package LeetCode.List;

import DataStructsandAlgorithms.ListNode;
import Utils.CommonUtils;

/**
 * 25. Reverse Nodes in k-Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 * */
public class Leet025 {

    /**
     * 这道题目考验的是对于链表的操作以及对边界条件的判断水平
     * 如果是再来一次的话，需要能够在15分钟之内完成代码的编写
     * 自己写的代码有些复杂了，总共50行左右，用到了超过3个临时变量
     * 优点是时间复杂度O(2n)与空间复杂度O(1)均为超过100%分部的代码。
     * */
    public ListNode reverseKGroup(ListNode head, int k) {
        //边界条件
        if (head == null||head.next == null||k<=1) return head;
        ListNode tmpheadhead = new ListNode(0);
        tmpheadhead.next = head;
        ListNode tmpKLast = head,tmpKhead=head;
        ListNode ret = head;
        int cnt = k-1;
        boolean isFirst = true;
        while(tmpKLast!=null){
            if(cnt!=0){
                tmpKLast = tmpKLast.next;
                cnt--;
            }else{
                if(isFirst){
                    reverse(tmpKhead,tmpKLast,k);
                    tmpheadhead.next = tmpKLast;
                    ret = tmpheadhead.next;
                    isFirst = false;
                    tmpheadhead = tmpKhead;
                }else{
                    reverse(tmpKhead,tmpKLast,k);
                    tmpheadhead.next = tmpKLast;
                    tmpheadhead = tmpKhead;
                }
                tmpKhead = tmpKhead.next;
                tmpKLast = tmpKhead;
                cnt = k-1;
            }
        }
        return ret;
    }

    //之前想着用栈解决，发现还不如直接翻转链表头尾置换
    //这里用了三个变量来进行链表倒转
    private ListNode reverse(ListNode first,ListNode last,int k){
        ListNode lastNext = last.next;
        ListNode tmpFirst = first;
        ListNode tmpMiddle = first.next;
        ListNode tmpLast = tmpMiddle.next;
        while (k>1){
            tmpMiddle.next = tmpFirst;
            tmpFirst = tmpMiddle;
            tmpMiddle = tmpLast;
            if(tmpLast!=null) tmpLast = tmpLast.next;
            k--;
        }
        first.next = lastNext;
        return first;
    }

    /**
     * 网上搜索到的使用递归的解法
     * 从空间复杂度上看确实会高一些
     * */
    public ListNode reverseKGroupFromWeb(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }

    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode tmp = head;
        for(int i=2;i<3;i++){
           tmp.next = new ListNode(i);
           tmp = tmp.next;
        }
        Leet025 leet025 = new Leet025();
        ListNode a = leet025.reverseKGroup(head,2);
        CommonUtils.printList(a);
    }
}
