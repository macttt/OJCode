package LeetCode.Hash;


import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 注* 最长子串为连续字符串，子序列为不连续的序列
 * */
public class Leet003 {
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int currentLen = 0;
        char[] str = s.toCharArray();
        HashMap map = new HashMap();
        for(int i=0;i<s.length();i++){
            if(map.get(str[i])==null){
                map.put(str[i],i);
                currentLen++;
            }else{
                currentLen = 1;
            }
            if(currentLen>maxLen) maxLen = currentLen;
        }
        return maxLen;
    }
}
