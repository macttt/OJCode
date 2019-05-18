package LeetCode.ArrayAndStack;

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
