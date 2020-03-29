package LeetCode.Greedy;

/**
 * 55. Jump Game
 Medium

 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 Example 1:

 Input: [2,3,1,1,4]
 Output: true
 Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 Example 2:

 Input: [3,2,1,0,4]
 Output: false
 Explanation: You will always arrive at index 3 no matter what. Its maximum
 jump length is 0, which makes it impossible to reach the last index.
 */
public class Leet055 {
        public boolean canJump(int[] nums) {
            int i=0;
            if(nums.length==1) return true;
            while(i+nums[i]<nums.length-1){
                if(nums[i]==0) return false;
                for(int j=i+1;j<=i+nums[i];j++){
                    if((j-i+nums[j])>=nums[i]){
                        i = j;
                        break;
                    }
                }
            }
            return true;
        }
    public static void main(String[] args){
        Leet055 leet055 = new Leet055();
        int[] nums = {3,2,1,0,4};
        System.out.print(leet055.canJump(nums));
    }
}
