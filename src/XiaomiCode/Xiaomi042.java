package XiaomiCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Xiaomi042 {

    /**
     *本来的思路是求LCS长度，再用长的那个减去LCS长度，最后发现是错误的算法
     * execution
     * intention
     * 这两个应该是5，但是LCS相减算出来是4，故算法有问题
     public static void main(String[] args){
     Xiaomi042 x042 = new Xiaomi042();
     Scanner scan = new Scanner(System.in);
     String line;
     while (scan.hasNextLine()) {
     line = scan.nextLine().trim();
     String[] str = line.split(",");
     int len = max(str[0].length(),str[1].length());
     int[][] memo = new int[str[0].length()][str[1].length()];
     System.out.println(len-x042.getLCSlength(str[0],str[1],str[0].length()-1,str[1].length()-1,memo));
     }
     }
     protected int getLCSlength(String str1,String str2,int index1,int index2,int[][] memory){
     if(index1<0||index2<0){
     return 0;
     }else if(memory[index1][index2]!=0){
     return memory[index1][index2];
     }else if(str1.charAt(index1)==str2.charAt(index2)){
     int ret = getLCSlength(str1,str2,index1-1,index2-1,memory)+1;
     memory[index1][index2] = ret;
     return ret;
     }else{
     int ret = max(getLCSlength(str1,str2,index1,index2-1,memory),getLCSlength(str1,str2,index1-1,index2,memory));
     memory[index1][index2] = ret;
     return ret;
     }
     }

     private static int max(int num1,int num2){
     return num1>num2?num1:num2;
     }
     */

    /**
     * 用正常做法做edit distance
     */
    public static void main(String[] args){
        Xiaomi042 x042 = new Xiaomi042();
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            String[] str = line.split(",");
            int[][] memo = new int[str[0].length()+1][str[1].length()+1];
            System.out.println(x042.editDistance(str[0],str[1],memo));
        }

    }

    //自顶向下
    protected int editDistance(String str1, String str2, int[][] memory) {
        int n = str1.length();
        int m = str2.length();
        if(n==0||m==0){
            return Math.max(n,m);
        }else if (memory[n][m] != 0){
            return memory[n][m];
        }else if (str1.charAt(0)==str2.charAt(0)){
            memory[n][m] = editDistance(str1.substring(1),str2.substring(1),memory);
            return memory[n][m];
        }else{
            int add = editDistance(str1, str2.substring(1), memory)+1;
            int remove = editDistance(str1.substring(1), str2, memory)+1;
            int replace = editDistance(str1.substring(1), str2.substring(1), memory)+1;
            memory[n][m] = Math.min(remove, Math.min(replace, add));
            return memory[n][m];
        }
    }
}

