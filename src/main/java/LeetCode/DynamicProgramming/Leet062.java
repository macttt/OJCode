package LeetCode.DynamicProgramming;


/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * */
public class Leet062 {
    /**
     * 设置初始状态为1，因为终点到该点路径的走法为1条
     * */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i =m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1){
                    //初始状态为1
                    dp[i][j] = 1;
                } else if(j==n-1){
                    //初始状态为1
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }
        /**
         * 最终起点的路径数为（终点到起点右边一点的路径和）+（终点到起点下边一点的路径和）
         * dp[0][0] = dp[0+1][0] + dp[0][0+1];
         * */
        return dp[0][0];
    }
}
