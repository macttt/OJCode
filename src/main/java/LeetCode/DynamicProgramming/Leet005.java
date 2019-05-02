package LeetCode.DynamicProgramming;


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * */

/**增量方法替代遍历式判断是否是回文达到O（n）效果的回文提取*/
/**有一种Manachar方法，可以在O(n)时间内完成最长回文的判定*/
public class Leet005 {
    //遍历输入字符串charAt的位置，判断以该字符起始的多长字符串是否是回文
    public String longestPalindrome(String s) {
        int len = s.length();
        if(len==0){
            return "";
        }
        char[] str = s.toCharArray();
        int begin = 0;
        int end = 0;
        for (int i=len;i>0;i--){
            for(int j=0;j<=len-i;j++){
                if(isPalindrome(str,j,j+i-1)){
                    begin = j;
                    end = j+i-1;
                    return s.substring(begin,end+1);
                }
            }
        }
        return s.substring(begin,end+1);
    }

    //判断是否为回文
    private boolean isPalindrome(String s){
        int j=0;
        int i=s.length()-1;
        for(;i>=j;j++,i--){
            if (s.charAt(i)!=s.charAt(j)) return false;
        }
        return true;
    }

    //用索引判断是否为回文（这种判断方法比较耗时，直接增量返回比这种方法快很多）
    private boolean isPalindrome(String s,int begin,int end){
        for(;end>=begin;begin++,end--){
            if (s.charAt(begin)!=s.charAt(end)) return false;
        }
        return true;
    }

    //将字符串转为Char数组后判断是否回文
    private boolean isPalindrome(char[] s,int begin,int end){
        for(;end>=begin;begin++,end--){
            if (s[begin]!=s[end]) return false;
        }
        return true;
    }



    public static void main(String[] args){
        Leet005 mn = new Leet005();
        String a = mn.longestPalindrome("bababd");
        System.out.println(a);
    }
}
