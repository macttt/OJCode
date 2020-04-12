package LeetCode.ArrayAndStack;

import java.util.*;

/**
 * 1306. Jump Game III
 * Medium
 * <p>
 * 240
 * <p>
 * 7
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 * <p>
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 */
public class Leet1306JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        Set<Integer> canReachNode = new HashSet();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        Integer node = start, firstDest, secondDest;
        int len = arr.length;
        while (!queue.isEmpty()) {
            node = queue.poll();
            firstDest = node - arr[node];
            secondDest = node + arr[node];
            if (firstDest >= 0 && firstDest < len && !canReachNode.contains(firstDest)) {
                if (arr[firstDest] == 0) return true;
                queue.add(firstDest);
                canReachNode.add(firstDest);
            }
            if (secondDest >= 0 && secondDest < len && !canReachNode.contains(secondDest)) {
                if (arr[secondDest] == 0) return true;
                queue.add(secondDest);
                canReachNode.add(secondDest);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3, 0, 2, 1, 2};
        Leet1306JumpGameIII leet = new Leet1306JumpGameIII();
        System.out.println(leet.canReach(arr, 2));
    }
}
