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
            System.out.println(x042.editDistanceBottomUp(str[0],str[1]));
//            System.out.println(x042.editDistance(str[0],str[1],memo));
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

    //自底向上,外圈多一层，让进入当前状态的形式有三个
    protected int editDistanceBottomUp(String str1,String str2){
        int n = str1.length();
        int m = str2.length();
        int[][] edit = new int[n+1][m+1];
        for(int i=n;i>=0;i--){
            for(int j=m;j>=0;j--){
                if(i==n){
                    edit[i][j] = m-j;
                }else if(j==m){
                    edit[i][j] = n-i;
                }else if(str1.charAt(i)==str2.charAt(j)){
                    edit[i][j] = edit[i+1][j+1];
                }else{
                    edit[i][j] = Math.min(edit[i+1][j],Math.min(edit[i][j+1],edit[i+1][j+1]))+1;
                }
            }
        }
        return edit[0][0];
    }

    public static int editDistanceBottomUp_effective(String s1, String s2){
        int[] strg = new int[s2.length() + 1];
        for(int i = s1.length();i >= 0;i--){
            int save = 0;
            for(int j = s2.length();j >= 0;j--){
                if(i == s1.length()){
                    strg[j] = s2.length() - j;
                    continue;
                }
                if(j == s2.length()){
                    save = strg[j];
                    strg[j] = s1.length() - i;
                    continue;
                }
                if(s1.charAt(i) == s2.charAt(j)){
                    int temp = save;
                    save = strg[j];
                    strg[j] = temp;
                }else{
                    int insert = strg[j + 1];
                    int delete = strg[j];
                    int replace = save;
                    save = strg[j];
                    strg[j] = Math.min(insert, Math.min(delete, replace)) + 1;
                }
            }
        }
        return strg[0];
    }
}

