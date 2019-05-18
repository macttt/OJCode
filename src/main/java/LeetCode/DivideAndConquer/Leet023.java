package LeetCode.DivideAndConquer;

import DataStructsandAlgorithms.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. Merge k Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 *
 * Example:
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * */
public class Leet023 {
    /**
     * 这道题一开始想的是每放入一个数字循环K次，把最小的拿出来，
     * 然后放到新的链表里，这样时间复杂度就是O(Kn)，有一个缺点就是当K很大的时候，会很慢。
     * 这个时候考虑到K个数取最小值的问题，灵机一动，用堆！这样就可以用nlogk的时间复杂度完成merge。
     * Java中自带了小顶堆，叫优先队列。
     * */

    /**
     * 又有了新的问题，空间复杂度。
     * 理论上来说，堆中pop了一个就可以push进一个，但是问题来了，为了不遍历每一个数组的头结点。
     * 如果只是纯粹地将每个节点放入堆中，那么我无法知道下一次push的时候应该push数组中的哪个链表，例如
     * 1->1->2->3->3
     * 9->10->10->10
     * 15->15->15
     * 这种，显然用普通的方法只能将他们全数存入堆中，那么堆的空间复杂度必然变为O(n),此时将会内存过大。
     *
     * 把序号和节点一并存入堆中可能是较好的选择，pop一个节点，则从那个节点的链表中再拿一个节点出来。
     * 更正！(实际上，放入头节点就可以直接使用next，不用记得index！)
     * */
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                //十分简洁的写法
                return o1.val-o2.val;
            }
        });

        //链表自带知道自己next的属性！
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null) pq.add(lists[i]);
        }
        ListNode heada = new ListNode(0);
        ListNode tail = heada;
        while (pq.size()>0){
            ListNode top = pq.remove();
            tail.next = top;
            tail = tail.next;
            if(top.next!=null){
                pq.add(top.next);
                top = top.next;
            }
        }
        return heada.next;
    }


    /**
     * 第二种O(nlogk)的方法则是分治归并法，
     * 大致思想与归并排序是相同的，之前没有想到这一点是不应该的，
     * 以下是分治归并代码
     * */
    public ListNode mergeKListsByDivide(ListNode[] lists) {
        if(lists.length == 0) return null;
        return recursive(lists, 0, lists.length - 1);
    }

    //分治代码
    private ListNode recursive(ListNode[] lists, int start, int end) {
        if(start == end) return lists[start];
        int mid = start + (end - start) / 2;
        ListNode left = recursive(lists, start, mid);
        ListNode right = recursive(lists, mid + 1, end);
        return merge(left, right);
    }

    //归并代码
    private ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode result = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2, cur = result;
        while(cur1 != null && cur2 != null) {
            if(cur1.val <= cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            }
            else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        if(cur1 == null) {
            cur.next = cur2;
        }
        else {
            cur.next = cur1;
        }
        return result.next;
    }
//    class ListNodeWithIndex implements Comparable<ListNodeWithIndex>{
//        ListNode ln;
//        ListNodeWithIndex next;
//        int index;
//        public ListNodeWithIndex(ListNode init,int input){
//            this.ln = init;
//            this.index = input;
//        }
//
//        public int compareTo(ListNodeWithIndex o){
//            if(ln.val>o.ln.val){
//                return 1;
//            }else if(ln.val == o.ln.val){
//                return 0;
//            }else{
//                return -1;
//            }
//        }
//    }
//    private int compareTo(ListNode n1,ListNode n2){
//        if(n1.val>n2.val){
//            return 1;
//        }else if(n1.val==n2.val){
//            return 0;
//        }else{
//            return -1;
//        }
//    }
}

