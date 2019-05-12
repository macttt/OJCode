package LeetCode.List;

import DataStructsandAlgorithms.ListNode;

/**
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Example:
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 * */
public class Leet002 {

    /**
     * 这道题目主要考验思考问题时是否考虑全面、代码的鲁棒性等方面
     * 非常简单的一道大数相加问题，我提交了8次才AC，需要反思。
     * 有时候编程确实需要thinking it easy,但是不能因为这一点去忽略了其他必要性逻辑。
     * 另外这个代码时间复杂度只有95%,空间只有60%，可以加强两点：
     * 1.求余操作可以用一个是否进位的flg来替代；
     * 2.两数之和申请的变量可以省去。
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int digit = 1;
        int d1,d2,twoSum;
        twoSum = 0;
        ListNode ret,tmp;
        ret = new ListNode(0);
        tmp = new ListNode(0);
        while(l1!=null||l2!=null){
            d1 = l1==null?0:l1.val;
            d2 = l2==null?0:l2.val;
            twoSum = d1 + d2 + (twoSum/10);
            if(digit==1){
                ret = new ListNode((d1+d2)%10);
                tmp = ret;
                digit++;
            }else{
                tmp.next = new ListNode(twoSum%10);
                tmp = tmp.next;
            }
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
        }
        if(twoSum>9){
            tmp.next = new ListNode(1);
        }
        return ret;
    }
}
