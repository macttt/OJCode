package Utils;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 普通工具类
 * 包含各种工具代码片与现成代码
 * 用于复制粘贴
 *
 * */
public class CommonUtil {

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

    /** 用于查看java源代码，熟悉代码用测试函数*/
    public static void lookSource(){

        HashMap map = new HashMap();
        PriorityQueue heap = new PriorityQueue();

    }
}
