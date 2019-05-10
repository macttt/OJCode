package LeetCode.BinaryTree;

import DataStructsandAlgorithms.TreeNode;
import Utils.CommonUtils;

import java.util.Stack;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 *Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia:
 *   “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 *   that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 *
 * Note:
 *
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
 *
 * */
public class Leet236 {

    /**
     * 第一个方法的思路：直接遍历二叉树至底层，然后从底层开始判断是否是CA，
     * 由于是从底层开始判断的，所以得出的第一个CA答案就是LCA，很容易明白
     * 但是没想到OJ的用时只有5%，空间倒是还行，超过了90%的提交
     * 开始尝试自顶向下的遍历
     * */
    private TreeNode ans;
    private static boolean hasLCA;

    public TreeNode lowestCommonAncestorFirst(TreeNode root, TreeNode p, TreeNode q) {
        hasLCA = false;
        ans = null;
        traversalTree(root,p,q);
        return ans;
    }

    private void traversalTree(TreeNode root, TreeNode p, TreeNode q){
        if(root==null) return;
        traversalTree(root.left,p,q);
        traversalTree(root.right,p,q);
        if(containsTreeNode(root,p)&&containsTreeNode(root,q)&&hasLCA==false){
            hasLCA = true;
            ans = root;
        }
    }

    private boolean containsTreeNode(TreeNode root,TreeNode a){
        if(root==null) return false;
        boolean ret = false;
        if(root.val == a.val){
            ret = true;
        }
        return  ret||containsTreeNode(root.left,a)||containsTreeNode(root.right,a);
    }

    /**
     * 想到了第二种思路:
     * 求得两个数在数组中映射的数字，如根节点的左子树记index= 0*2+1 = 1，右子树记index = 0*2+2 = 2,
     * 再根据这两个index，就可以很快求得他们的LCA的index，再根据这个index查询出LCA的值
     * 这样的时间复杂度为O(n)（搜索p、q两个节点的index用时）+ O(n)(搜索LCA节点的用时)
     * 最多用时O(2n)
     * 这种思路莫名其妙地没法AC，气得要死，只好换成网上的最优算法
     * 放在自己机器上跑，发现爆栈了，问题时上面的解决方法应该需求更多栈
     *
     * */
    private int indexP = 0;
    private int indexQ = 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        setIndex(root,p,q,0);
        int indexLCA = 0;
//        System.out.println("p:"+indexP+",q:"+indexQ);
        while(indexP!=0&&indexQ!=0){
            if (indexP == indexQ) break;
            if(indexP > indexQ){
                if(indexP%2==0){
                    indexP = (indexP - 2)/2;
                }else{
                    indexP = (indexP - 1)/2;
                }
            } else{
                if(indexQ%2==0){
                    indexQ = (indexQ - 2)/2;
                }else{
                    indexQ = (indexQ - 1)/2;
                }
            }
        }
        indexLCA = indexP > indexQ ? indexQ : indexP;
//        System.out.println("LCA:"+indexLCA);
        TreeNode tmp = root;
        //此时indexLCA就是公共祖先的索引位置
        Stack<Integer> stack = new Stack<>();
        while(indexLCA>0){
            if(indexLCA%2==0){
                stack.push(1);
                indexLCA = (indexLCA - 2)/2;
            }else {
                stack.push(0);
                indexLCA = (indexLCA - 1)/2;
            }
        }
        int lr;
        while (!stack.isEmpty()){
            lr = stack.pop();
            if(lr==0){
                tmp = tmp.left;
            }else{
                tmp = tmp.right;
            }
        }
        return tmp;
    }

    //设置p、q两个节点在相应的数组结构树中的对应索引index
    private void setIndex(TreeNode root,TreeNode p,TreeNode q,int index){
        if (root==null) return;
        if(root.val == p.val) indexP = index;
        if(root.val == q.val) indexQ = index;
        setIndex(root.left,p,q,index*2+1);
        setIndex(root.right,p,q,index*2+2);
    }



    public static void main(String[] args){
        Leet236 leet236 = new Leet236();
        int[] num = {-1,0,-9999,1,-9999,2,-9999,3};
        TreeNode root = CommonUtils.createTreeFromArray(num);
        TreeNode root2 = new TreeNode(0);
        TreeNode tmp = root2;
        tmp.left = new TreeNode(1);
        tmp = tmp.left;
        tmp.left = new TreeNode(2);
        tmp = tmp.left;
        tmp.left = new TreeNode(3);
        tmp = tmp.left;
        tmp.left = new TreeNode(4);
        tmp = tmp.left;
        tmp.left = new TreeNode(5);
        tmp = tmp.left;

        System.out.println(leet236.lowestCommonAncestorFirst(root,new TreeNode(0),new TreeNode(1)).val);
        System.out.println(leet236.lowestCommonAncestorFirst(root,new TreeNode(-1),new TreeNode(1)).val);
        System.out.println(leet236.lowestCommonAncestor(root2,new TreeNode(5),new TreeNode(1)).val);
        System.out.println(leet236.lowestCommonAncestor(root2,new TreeNode(3),new TreeNode(5)).val);
    }
}
