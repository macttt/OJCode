package LeetCode.BinaryTree;


import DataStructsandAlgorithms.TreeNode;
import static Utils.CommonUtil.*;


/**
 * House Robber III
 *The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * */


//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
//}

public class Leet337 {
    /**
     * 这种方法虽然很笨拙，但是内存不会溢出
     * 这道题目其实也是动态规划题
     * */
    public int rob(TreeNode root) {
        return robMax(root);
    }

    private int robMax(TreeNode root){
        if(root==null){
            return 0;
        }
        int robLast = robMax(root.left) + robMax(root.right);
        int robNow = root.val + ((root.left==null)?0:robMax(root.left.left) + robMax(root.left.right))
                + ((root.right==null)?0:robMax(root.right.left) + robMax(root.right.right));
        return robLast > robNow ? robLast : robNow;
    }

    //网上的最优化解，超过100%的代码
    public int rob2(TreeNode root) {
        int[] res = robSub2(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * 这个方法可以递归地递进获取树的动态规划状态
     * */
    private int[] robSub2(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub2(root.left);
        int[] right = robSub2(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }

    public int robMemo(TreeNode root) {
        int height = hight(root);
        int[] tree = new int[(1<<height)-1];
        return robMaxMemo(root,tree,0);
    }

    //这种备忘录式的方法，内存爆了，无法通过，废弃
    private int robMaxMemo(TreeNode root, int[] tree, int index){
        if(root==null){
            return 0;
        }
        if(tree[index]>0){
            return tree[index];
        }

        int robLast = robMaxMemo(root.left,tree,index*2+1) + robMaxMemo(root.right,tree,index*2+2);
        int robNow = root.val + ((root.left==null)?0: robMaxMemo(root.left.left,tree,(index*2+1)*2+1) + robMaxMemo(root.left.right,tree,(index*2+1)*2+2))
                + ((root.right==null)?0: robMaxMemo(root.right.left,tree,(index*2+2)*2+1) + robMaxMemo(root.right.right,tree,(index*2+2)*2+2));
        tree[index] = robLast > robNow ? robLast : robNow;

        return tree[index];
    }



    //求树的高度
    public int hight(TreeNode node){
        if(node==null){
            return 0;
        }else{
            int i=hight(node.left);
            int j=hight(node.right);
            return (i<j)?(j+1):(i+1);
        }
    }

    //求树的总结点个数
    private int sumNode(TreeNode node){
        if(node==null){
            return 0;
        }else{
            int a=sumNode(node.left);
            int b=sumNode(node.right);
            return 1+a+b;
        }
    }

    public static void main(String[] args){
        Leet337 lt337 = new Leet337();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        print(lt337.rob(root));
    }
}
