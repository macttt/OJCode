package LeetCode.DynamicProgramming;


/**
 * 198. House Robber
 * Easy
 *
 * 2382
 *
 * 72
 *
 * Favorite
 *
 * Share
 * You are a professional robber planning to robBefore houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them
 * is that adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can robBefore tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then robBefore house 3 (money = 3).
 *              Total amount you can robBefore = 1 + 3 = 4.
 * Example 2:
 *
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), robBefore house 3 (money = 9) and robBefore house 5 (money = 1).
 *              Total amount you can robBefore = 2 + 9 + 1 = 12.
 * */
public class Leet198 {
    /**
     * 典型的01背包问题，在问题中加了状态转移限制
     * 状态转移方程：
     * 判断dp[i-2]+nums[i]>dp[i-1]（当前数加上，前第二个数的dp最大值是否大于前一个数的dp最大值）
     * 若成立，则取 dp[i] = dp[i-2]+nums[i]
     * 否则，取 dp[i] = dp[i-1]
     * */
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return  nums[0]>nums[1]?nums[0]:nums[1];
        }
        for(int i=0;i<nums.length;i++){
            if(i==0){
                dp[0] = nums[0];
            }else if(i==1){
                dp[1] = nums[0]>nums[1]?nums[0]:nums[1];
            }else{
                dp[i] = dp[i-2]+nums[i]>dp[i-1]?dp[i-2]+nums[i]:dp[i-1];

            }
        }
        return dp[nums.length-1];
    }

    //看网上的更小空间复杂度
    //一共用到3个int变量，并且兼顾了length=1、2的情况
    //LeetCode上空间使用量居然比上一种方法更多！？？？？？？？
    public static int rob2(int[] nums){
        int curMax = 0;
        int prevMax = 0;
        int tmp = 0;
        for(int i=0;i<nums.length;i++){
            tmp = curMax;
            curMax = Math.max(prevMax+nums[i],curMax);
            prevMax = tmp;
        }
        return curMax;
    }

    public static void main(String[] args){
        int[] a1= {1,2,3,1};
        int[] a2 = {2,7,9,3,1};
        System.out.println(rob2(a1));
        System.out.println(rob2(a2));
    }
}
