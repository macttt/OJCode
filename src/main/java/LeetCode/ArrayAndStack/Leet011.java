package LeetCode.ArrayAndStack;
/*
* 11. Container With Most Water
* Given n non-negative integers a1, a2, ..., an , 
* where each represents a point at coordinate (i, ai). 
* n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
* Find two lines, which together with x-axis forms a container, such that the container contains the most water.
* Note: You may not slant the container and n is at least 2.
* The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
* In this case, the max area of water (blue section) the container can contain is 49.
*
* Example:
* 
* Input: [1,8,6,2,5,4,8,3,7]
* Output: 49
*/
public class Leet011 {
    /**
     * 这道题目想了10分钟只想出来暴力解法，不应该。
     * 看了solution才知道怎么做，脑子不大好用了。
     * */
    public int maxArea(int[] height) {
        int l=0,r=height.length-1,maxarea = 0;
        while(l<r){
            maxarea = Math.max(maxarea,Math.min(height[l],height[r])*(r-l));
            if(height[l]>height[r]){
                r--;
            }else {
                l++;
            }
        }
        return maxarea;
    }
}
