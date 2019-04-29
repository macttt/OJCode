package DataStructsandAlgorithms;

import static Utils.CommonUtil.swap;

public class QuickSort {
    //快排
    /**注意 快排的数字交换并不是对换，而是当前数字与下一个数字交换！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * 这样就可以让pivot，standard数字轻松地存入数组中
     *
     *
     *
     * */
    public static void main(String[] args){
        int[] n = {6,4,2,1,8,6,9,5,7,0,4,3};
//        int[] n = {0,0,1,1,2,1,1};
//        n = quickSortByMeFirstTime(0,n.length-1,n);
        long start = System.currentTimeMillis();
        quickSortFromWeb(0,n.length-1,n);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        printNumbers(n);
    }

    //自己写的快排
    //又臭又长 bug还多
    public static int[] quickSortByMeFirstTime(int start, int end, int[] n){
        int idx1=start;
        int idx2=end-1;
        int standard = n[end];
        int tmp,std;
        boolean exchangeflg = false;
        if(start>=end){
            return n;
        }
        if(start+1 == end){
            if(n[start]>n[end]){
                swap(n,start,end);
            }
            return n;
        }
        while(idx1<idx2){
            if(n[idx1]<standard){
                idx1++;
            }
            if(n[idx2]>=standard){
                idx2--;
            }
            if(n[idx1]>=standard&&n[idx2]<=standard){
                tmp = n[idx1];
                n[idx1] = n[idx2];
                n[idx2] = tmp;
                idx1++;
                idx2--;
                exchangeflg = true;
            }
        }
        if(exchangeflg){
            if(n[idx1]<=standard){
                tmp = n[idx1+1];
                n[idx1+1] = n[end];
                n[end] = tmp;
                std = idx1+1;
            }else{
                tmp = n[idx1-1];
                n[idx1-1] = n[end];
                n[end] = tmp;
                std = idx1-1;
            }
            if(std-1>start){
                n = quickSortByMeFirstTime(start,std-1,n);
            }
            if(std+1<end){
                n = quickSortByMeFirstTime(std+1,end,n);
            }
        }else{
            //没有发生交换时，
            if(idx1==end-1){
                n = quickSortByMeFirstTime(start,end-1,n);
            }else if(idx2 == start){
                tmp = n[start];
                n[start] = n[end];
                n[end] = tmp;
                n = quickSortByMeFirstTime(start+1,end,n);
            }else{
                return n;
            }
        }
        return n;
    }

    /** 看浙大mooc教学之后写的快排 */
    public static void quickSort(int start,int end,int[] n){
        int pivot = n[end];
        while (start<end){
            while(++start<pivot&&start<end);
            while(--end>pivot&&start<end);
            if(start<end){
                swap(n,start,end);
            }else{
                break;
            }
        }
        n[start] = pivot;
    }

    public static void quickSortFromWeb(int low, int high,int a[]) {
        int i, j, index;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index)
                j--;
            if (i < j){
                a[i++] = a[j];// 用比基准小的记录替换低位记录
                printNumbers(a);
            }

            while (i < j && a[i] < index)
                i++;
            if (i < j){
                // 用比基准大的记录替换高位记录
                a[j--] = a[i];
                printNumbers(a);
            }


        }
        a[i] = index;// 将基准数值替换回 a[i]
        quickSortFromWeb( low, i - 1,a); // 对低子表进行递归排序
        quickSortFromWeb( i + 1, high,a); // 对高子表进行递归排序

    }

    public static void printNumbers(int[] n){
        for(int i=0;i<n.length;i++){
            if(i==0){
                System.out.print(n[i]);
            }else{
                System.out.print(" "+n[i]);
            }
        }
        System.out.println();
    }
}
