package LeetCode.ArrayAndStack;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * Explain: 5&6 get an 2x5 rectangle which has 10 units of area.
 *
 * */
public class Leet084 {

    /**
     * 题解：这道题目的核心是：
     *  对每一个柱状图的柱子，求得它所能够覆盖的最大面积，最后取其中最大值。
     *  例，对于数组 heights: [6, 2, 5, 4, 5, 1, 6],取每个柱子可覆盖的最大面积maxArea[i]
     *  maxArea[0]:6x1=6;
     *  maxArea[1]:2x5=10;
     *  maxArea[3]:5x1=5;
     *  maxArea[4]:4x3=12;
     *  maxArea[5]:5x1=5;
     *  maxArea[6]:6x1=6;
     * 这道题目可以转化为求这个maxArea数组中的最大值，若已经得出这个结论，则可以在O(n)等级的时间复杂度求出答案
     * */

    /**
     * 看到用栈的提醒，尝试一次栈解决问题
     * 思路有些错了，所以最后的答案不可避免地会错
     * 再怎么修改细节也无法通过测试用例
     * desperated
     * */
    public int largestRectangleArea1st(int[] heights) {
        Stack stack = new Stack();
        int maxArea = 0;
        int minNum = 0;
        int count =0;
        for(int i=0;i<heights.length;i++){
            if(i==0||count==0){
                stack.push(heights[i]);
                minNum = heights[0];
                count++;
                if(minNum>maxArea) maxArea = minNum;
            }else{
                if(heights[i]<minNum){
                    minNum=heights[i];
                }
                count++;
                if(heights[i]>=maxArea&&(minNum*count)<=heights[i]){
                    stack.empty();
                    stack.push(heights[i]);
                    count=1;
                    minNum = heights[i];
                    maxArea = heights[i];
                    continue;
                }else{
                    if(minNum*count>=maxArea){
                        stack.push(heights[i]);
                        maxArea = minNum*count;
                    }else{
                        stack.empty();
                        count = 0;
                    }
                }
            }

        }
        return maxArea;
    }

    /**
     * 网上的代码
     * */
    public static int largestRectangleAreaFromWeb(int[] heights){
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < heights.length)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || heights[s.peek()] <= heights[i])
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = heights[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;
    }

    /**
     * 原理
     * */
    public static int largestRectangleArea(int[] heights){
        int maxArea = 0;
        Stack<Integer> histogram = new Stack();
        int i = 0;
        int n = heights.length;
        int nowHeight;
        while(i<=n){
            //最后加一个高度为0的柱图，将还在栈中的数字pop出来
            nowHeight = (i==n)?0:heights[i];
            //判断是否能进栈
            if(histogram.isEmpty()||nowHeight>heights[histogram.peek()]){
                //注意，这里推入栈的是柱状图的下标索引i
                histogram.push(i++);
            }else{
                //当前栈顶索引为被pop出来的索引的左边界，而i则是右边界
                int curHeight = heights[histogram.pop()];
                int maxAreaOfCurrentIndex = histogram.isEmpty()?curHeight*i:curHeight*(i-histogram.peek()-1);
                maxArea = Math.max(maxArea,maxAreaOfCurrentIndex);
            }

        }
        return maxArea;
    }

    public static void main(String[] args){
        int[] input = {6, 2, 5, 4, 5, 1, 6};
        Leet084 out = new Leet084();
        System.out.println(out.largestRectangleArea(input));
    }
}
