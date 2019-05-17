package Utils;

import DataStructsandAlgorithms.ListNode;
import DataStructsandAlgorithms.TreeNode;
import Interface.ListToBePrinted;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 普通工具类
 * 包含各种工具代码片与现成代码
 * 用于复制粘贴
 *
 * */
public class CommonUtils {

    /** 交换数组中的两个数字*/
    public static void swap(int[] n,int index1,int index2){
        int tmp = n[index1];
        n[index1] = n[index2];
        n[index2] = tmp;
    }

    /** 打印数组中的所有数字*/
    public static void printArray(int[] n){
        for(int i=0;i<n.length;i++){
            if(i==0){
                System.out.print(n[i]);
            }else{
                System.out.print(" "+n[i]);
            }
        }
        System.out.println();
    }

    /** 根据数组生成链表,方便测试*/
    public ListNode createListFromArray(int[] input){
        if(input == null||input.length==0) return null;
        ListNode head = new ListNode(input[0]);
        ListNode tmp = head;
        for(int i = 1;i<input.length;i++){
            ListNode now = new ListNode(input[i]);
            tmp.next = now;
            tmp = now;
        }
        return head;
    }

    /** 根据数组生成二叉树，方便测试*/
    public static TreeNode createTreeFromArray(int[] input){
        if(input == null||input.length==0) return null;
        TreeNode root = createSubTree(input,0);
        return root;
    }

    /**
     * 考虑到二叉树无法找到父节点的特性，用递归来完成树的构造
     * 若是之后做双向二叉树节点，再来简化
     * */
    private static TreeNode createSubTree(int[] input, int index){
        if(index>input.length-1||input[index]<-1) return null;
        TreeNode subroot = new TreeNode(input[index]);
        subroot.left = createSubTree(input,index*2+1);
        subroot.right = createSubTree(input,index*2+2);
        return subroot;
    }

    /**打印完整链表，该链表节点类型需要包含ListToBePrinted接口*/
    public static void printList(ListToBePrinted input){
        while(input!=null){
            System.out.print(input+" ");
            input = input.getNext();
        }
    }


    /** 用于查看java源代码，熟悉代码用测试函数*/
    public static void getJavaSourceCode(){
        HashMap map = new HashMap();
        PriorityQueue heap = new PriorityQueue();
        StringBuilder stringBuilder = new StringBuilder("123");
        StringBuffer stringBuffer = new StringBuffer("123");
        String string = new String("123");
        //测试git文件为何不存在
    }

    public static void print(Object a){
        System.out.println(a);
    }


}
