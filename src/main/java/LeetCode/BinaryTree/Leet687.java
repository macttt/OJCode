package LeetCode.BinaryTree;

import DataStructsandAlgorithms.TreeNode;

/**
 * Given a binary tree, find the length of the longest path
 * where each node in the path has the same value.
 * This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output: 2
 *
 * Example 2:
 *
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 2
 * */
public class Leet687 {
    public int longestUnivaluePath(TreeNode root) {
        return getLongestUnivaluePath(root)[0];
    }

    //这个方法时间复杂度21%,空间99.6%,需要优化时间复杂度
    /**
     * 实际上只需要用一个变量来保存就行了
     * 每一个节点的left+right就是该节点连接的path所能达到的最大path
     * 所以直接用left+right更新就可以了，也不需要返回数组来保存两个数据
     * */

    private int[] getLongestUnivaluePath(TreeNode root){
        //max[0] maxoftree,max[1] maxofcurrent
        int[] max = new int[2];
        int left=0,right=0;
        if(root==null) return new int[2];

        int[] maxleft = getLongestUnivaluePath(root.left);
        int[] maxright = getLongestUnivaluePath(root.right);
        max[0] = Math.max(maxleft[0],maxright[0]);
        if(root.left==null&&root.right==null) return new int[2];
        if(root.left!=null) {
            if(root.val == root.left.val){
                left = 1 + maxleft[1];
            }else{
                left = 0;
            }
        }
        if(root.right!=null){
            if(root.val==root.right.val){
                right = 1 + maxright[1];
            }else{
                right = 0;
            }
        }
        if((left>0&&right>0)&&left+right>max[0]) max[0] = left+right;
        max[1] = left>right?left:right;
        max[0] = Math.max(max[0],max[1]);
        return max;
    }

    public static void main(String[] args){
        //[1,4,5,4,4,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(2);
//        root.right.left = new TreeNode(5);
        Leet687 leet687 = new Leet687();
        System.out.println(leet687.longestUnivaluePath(root));
    }
}
