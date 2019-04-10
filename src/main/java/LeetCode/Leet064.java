package LeetCode;

/**64. Minimum Path Sum
 *Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 *
 * */
public class Leet064 {
    /**Dp方法（人人为我1）*/
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        if(row==1){
            for(int i=column-2;i>=0;i--){
                grid[0][i] += grid[0][i+1];
            }
            return grid[0][0];
        }else{
            for(int i=row-1;i>=0;i--){
                for(int j=column-1;j>=0;j--){
                    if(i==row-1&&j==column-1){
                        grid[i][j] = grid[i][j];
                    }else if(j==column-1){
                        grid[i][j] += grid[i+1][j];
                    }else if(i==row-1){
                        grid[i][j] += grid[i][j+1];
                    }else {
                        grid[i][j] += min(grid[i+1][j],grid[i][j+1]);
                    }
                }
            }
            return grid[0][0];
        }
    }

    private int min(int a,int b){
        return a<b?a:b;
    }

    /**
     * 递归方法DP
     *
     * */
    public int minPathSumWithRecursion(int[][] grid) {

        return 1;
    }
}
