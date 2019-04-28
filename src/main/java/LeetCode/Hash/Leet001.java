package LeetCode.Hash;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * */
public class Leet001 {

    /** 尝试提交，但是失败，题目输入[3,3]时，哈希表失效。
     * 尝试使用另一种方法，避免重复hash key的输入*/
    public static int[] twoSumWithNoReplicant(int[] nums, int target) {
        int[] answer = new int[2];
        HashMap map = new HashMap();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i]+","+i,i);
        }
        for(int i=0;i<nums.length;i++){

            if(map.get(target-nums[i])!=null&&(int)map.get(target-nums[i])!=(int)map.get(nums[i])){
                answer[0] = (int)map.get(nums[i]);
                answer[1] = (int)map.get(target-nums[i]);
                break;
            }
        }
        return answer;
    }

    /**
     * 思考尝试使用一次遍历得到解
     * 流程：
     * 当输入一个数字是，在hashmap中put这个数字的目标差值，
     * 例如当前target是10，数字是4，输入数组为[4,5,5,7]
     * 则进行 hashmap.put(10-4,0);这里的0代表数字4的index，
     * 这样就可以避免两个数字的hash冲突问题了,
     * 之后再从读取的数字中进行hashmap.get(nums[index]),若不为空，则返回[hashmap.get(nums[index]),index]
     * */
    public static int[] twoSum(int[] nums, int target) {
        HashMap map = new HashMap();
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])!=null){
                //网上看到的return代码，能够提高1ms的执行时间
                return new int[]{(int)map.get(nums[i]),i};
            }
            map.put(target-nums[i],i);
        }
        return null;
    }

    public static void main(String[] args){
        int[] a= {3,2,4};
        twoSum(a,6);
    }
}
