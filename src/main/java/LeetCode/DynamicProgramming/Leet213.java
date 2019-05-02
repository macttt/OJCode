package LeetCode.DynamicProgramming;

/**
 * You are a professional robber planning to robBefore houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can robBefore tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot robBefore house 1 (money = 2) and then robBefore house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then robBefore house 3 (money = 3).
 *              Total amount you can robBefore = 1 + 3 = 4.
 * */
public class Leet213 {
    //思路错了，这道题目用递归思想更好解决，数组nums[0,n-1] ,nums[1,n]之中最大的dp数就是问题的解
    //总体来说时间复杂度是O(2n),因为要算两组数组的rob1解。
    public static int robBefore(int[] nums) {
        int curMax = 0;
        int prevMax = 0;
        int tmp;
        boolean flg = false;
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        for(int i=0;i<nums.length;i++){
            if(i==2&&nums[0]+nums[2]>nums[1]){
                flg = true;
            }
            if(i==nums.length-1){
                curMax = flg?
                        Math.max(prevMax+nums[i]-nums[0],curMax):Math.max(prevMax+nums[i],curMax);
                return curMax;
            }else {
                tmp = curMax;
                curMax = Math.max(prevMax+nums[i],curMax);
                prevMax = tmp;
            }
        }
        return curMax;
    }

    private static int robber(int nums[],int start,int end){
        int curMax=0;
        int prevMax =0;
        int tmp;
        for(int i=start;i<=end;i++){
            tmp = curMax;
            curMax = prevMax + nums[i] > curMax ? prevMax + nums[i] : curMax;
            prevMax = tmp;
        }
        return curMax;
    }

    public static int rob(int[] nums){
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return nums[0]>nums[1]?nums[0]:nums[1];
        }
        /**
         * 最后一个限制条件代表了两种可能（这里考验读题的能力）：
         * 1.抢了第一间房，则最后一间房不可能被抢，那么nums[]数组取0到nums.length-2(倒数第二间)；
         * 2.抢了最后一间房，则第一间房不可能被抢，那么nums[]数组取1到nums.length-1（最后一间）;
         * */
        int start0 = robber(nums,0,nums.length-2);
        int start1 = robber(nums,1,nums.length-1);
        return start0>start1?start0:start1;
    }

    public static void main(String[] args){
        int[] a1= {1,1,1,2};
        int[] a2 = {2,3,2};
        System.out.println(rob(a1));
        System.out.println(rob(a2));
    }
}
