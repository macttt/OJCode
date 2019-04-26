package LeetCode.DynamicProgramming;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

/**
 *
 * 这里注意Subsequence和Substring的区别，subsequence不需要连续，而substring代表连续子序列
 * 本题的关键在与子序列必须连续*/
public class Leet032 {
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length -1, -1, -1, ')'));
    }
    private static int calc(char[] chars , int i ,  int flag,int end, char cTem){
        int max = 0, sum = 0, currLen = 0,validLen = 0;
        for (;i != end; i += flag) {
            sum += (chars[i] == cTem ? 1 : -1);
            currLen ++;
            if(sum < 0){
                max = max > validLen ? max : validLen;
                sum = 0;
                currLen = 0;
                validLen = 0;
            }else if(sum == 0){
                validLen = currLen;
            }
        }
        return max > validLen ? max : validLen;
    }

    /** 本方法无法求出连续子序列 */
//    public int longestValidParentheses2(String s) {
//        char[] chars = s.toCharArray();
//        int max = 0;
//        Stack a = new Stack();
//        for(int i=0;i<chars.length;i++){
//            if(chars[i]=='('){
//                a.push(chars[i]);
//            }else{
//                if ((!a.empty())&&(char)a.peek()=='('){
//                    a.pop();
//                    max+=2;
//                }else{
//                    a.push(chars[i]);
//                }
//            }
//        }
//        return max;
//    }

    /** DP方法 */
    /** 用时1ms，超过100%，空间39.1MB,超过14.40%*/
    public static int longestValidParenthesesWithDP(String s) {
            char[] chars = s.toCharArray();
            int maxLen = 0;
            int[] dp = new int[chars.length];
            for(int i=0;i<chars.length;i++){
                if(chars[i]=='('){
                    continue;
                }else{
                    if(i==0){
                        continue;
                    }else if(chars[i-1]=='('){
                        dp[i] = i-2>=0?dp[i-2]+2:2;
                    }else{
                        if(i-dp[i-1]-1>0){
                            if(chars[i-dp[i-1]-1]=='('){
                                dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2;
                            }else{
                                continue;
                            }
                        }else if(i-dp[i-1]-1==0){
                            if(chars[i-dp[i-1]-1]=='(') dp[i] = dp[i-1]+2;
                        }
                    }
                    maxLen = dp[i] > maxLen ? dp[i] : maxLen;
                }
            }
            return maxLen;
    }

    public static void main(String[] args){
        System.out.println(longestValidParenthesesWithDP(")()())"));
    }

}
