package LeetCode.DynamicProgramming;


/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * */
public class Leet063 {

    /**
     * 同062的思想，有障碍时可以将该点的到达路径数设置为0，这样它之后的点就无法从这一点到达，即+0
     * 这里可以在原有的数组上进行修改，可以节省空间复杂度
     * */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        int[][] dp = new int[row][column];
        if(obstacleGrid[row-1][column-1]==1){
            return 0;
        }
        for(int i=row-1;i>=0;i--){
            for(int j=column-1;j>=0;j--){
                if(obstacleGrid[i][j]==1){
                    dp[i][j] = 0;
                }else if(i==row-1&&j==column-1){
                    dp[i][j] = 1;
                }else if(i==row-1){
                    dp[i][j] = dp[i][j+1];
                }else if(j==column-1){
                    dp[i][j] = dp[i+1][j];
                }else{
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }

    /**
     * 原有的数组上进行修改
     * */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid){
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(i==n-1&&j==m-1&&obstacleGrid[i][j]==1){
                    return 0;
                }else{
                    if(obstacleGrid[i][j]==1){
                        obstacleGrid[i][j] = 0;
                    }else if(i==n-1&&j==m-1){
                        obstacleGrid[i][j] = 1;
                    }else if(i==n-1){
                        obstacleGrid[i][j] = obstacleGrid[i][j+1];
                    }else if(j==m-1){
                        obstacleGrid[i][j] = obstacleGrid[i+1][j];
                    }else{
                        obstacleGrid[i][j] = obstacleGrid[i+1][j] + obstacleGrid[i][j+1];
                    }
                }
            }
        }
        return obstacleGrid[0][0];
    }
}
