package LeetCode.DynamicProgramming;


/**
 *  给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * */

/**
 * 状态转移方程见代码
 * 实现原理类似于老题Edit Distance中，DP数组在外层加了一层，满足了一共3种状态迁移的过程
 * */
public class Leet010 {
    public static boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        /**dp[0][0]表示s="",p=""*/
        dp[0][0] = true;
        /**i=0时，s="",检查s是否符合p("#*"),实际操作过程中，这一步可以省略*/
        for(int j=2;j<pLen+1;j+=2){
            if (pChar[j-1] == '*' && dp[0][j-2]) {
                dp[0][j] = true;
            }
        }

        //状态转移开始
        for(int i=1;i<sLen+1;i++){
            for(int j=1;j<pLen+1;j++){
                if(sChar[i-1]==pChar[j-1]||pChar[j-1] =='.'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(pChar[j-1]=='*'){
                    if(pChar[j-2]==sChar[i-1]||pChar[j-2]=='.'){
                        /**
                         * 当出现#*的情况，并且#==sChar[i]时，会有三种可能
                         * 1.虽然字符相同，但是*不需要计数，例如s="aab",p="aaa*b"，
                         * 此时dp[i][j]=dp[i][j-2];
                         * 2.字符相同，这个*只记一个数，当前j不计数，例如s="aaab",p="aaa*b"
                         * 此时dp[i][j]=dp[i][j-1];
                         * 3.*记两个及以上的数时，取其正上方的状态即可
                         * 此时dp[i][j]=dp[i-1][j]
                         *
                         * 因此有 dp[i][j] = dp[i][j-2]||dp[i][j-1]||dp[i-1][j];
                         * 三者取其可能值
                         * */
                        dp[i][j] = dp[i][j-2]||dp[i][j-1]||dp[i-1][j];
                    }
                    /**这里代表了这个'*'号为0，不需要这个字符的情况，例如s="abc",p="ad*bc"*/
                    else{
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    public static void main(String[] args){
        System.out.println(isMatch("",".*"));
    }
}
