package LeetCode.ReservoirSampling;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 *
 * 示例:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 *
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 *
 * 网上搜索，这种是由“水塘抽样”算法解决的统计随机算法
 *
 * */
public class Leet398 {
    //尝试了hashmap法和数组法，结果是数组法更快，但是消耗空间略多
    //但是这两种方法都不算是最优方法！
    HashMap map = new HashMap();
    public Leet398(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])==null){
                ArrayList al = new ArrayList();
                al.add(i);
                map.put(nums[i],al);
            }else{
                ArrayList tmp = (ArrayList) map.get(nums[i]);
                tmp.add(i);
                map.put(nums[i],tmp);
            }
        }
    }

    public int pick(int target) {
        ArrayList tar = (ArrayList)map.get(target);
        return (int)tar.get((int)(Math.random()*(tar.size()))) ;
    }

    public static void main(String[] args){
        Leet398 le = new Leet398(new int[]{1,2,3,3,3});
        System.out.println(le.pick(3));

    }

    /**
     * 线上算法，水塘抽样，在O(n)时间内完成抽样，即读完数据就可以完成抽样
     * 不需要再一次遍历所有数据，并且空间复杂度低。
     * 水塘抽样算法：
     * */
    int[] numbers;
    public Leet398(int[] nums,int s){
        this.numbers = nums;
    }

    //该pick函数使用小数除法，效率不及random取0的方法
    public int pick(int a,int b){
        int index =0;
        int count =0;
        Random ran = new Random();
        for(int i=0;i<numbers.length;i++){
            if(a==numbers[i]){
                count++;
                if(Math.random()<1.0/count){
                    index = i;
                }
            }
        }
        return index;
    }

    //取0与取其他数皆可，目的是体现概率
    public int pick(int target,String b){
        int index =0;
        int count =0;
        Random ran = new Random();
        for(int i=0;i<numbers.length;i++){
            if(target==numbers[i]){
                count++;
                if(ran.nextInt(count)==0){
                    index = i;
                }
            }
        }
        return index;
    }

}
