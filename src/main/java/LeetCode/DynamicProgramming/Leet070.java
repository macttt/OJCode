package LeetCode.DynamicProgramming;

/**70. Climbing Stairs
 *
 *
 * */
public class Leet070 {
    public int climbStairsWithMemo(int n) {
        int[] num = new int[n+1];
        for(int i=0;i<n+1;i++)
            num[i] = 0;
        return climb_Stairs_withmemo(n,num);
    }
    /**笔记本法*/
    private int climb_Stairs_withmemo(int n,int[] num){
        if(n==0){
            return 0;
        }else if(n==1){
            return 1;
        }else if(n==2){
            return 2;
        }else{
            if(num[n]==0){
                num[n] = climb_Stairs_withmemo(n-2,num)+climb_Stairs_withmemo(n-1,num);
                return num[n];
            }else{
                return num[n];
            }

        }
    }
    /** 动态规划法（人人为我）*/
    public int climbStairsWithDP(int n) {
        if(n==1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1]= 1;
        dp[2]= 2;
        for(int i=3;i<n+1;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

}
