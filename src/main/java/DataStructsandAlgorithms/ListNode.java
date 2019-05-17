package DataStructsandAlgorithms;

import Interface.ListToBePrinted;

import java.util.List;

/**
 * 链表节点类型，
 * */
public class ListNode implements ListToBePrinted {
    public int val;
    public ListNode next;
    public ListNode(int x){
        val = x;
    }
    public ListNode getNext(){
        return this.next;
    }
    public String toString(){
        return String.valueOf(val);
    }
}
