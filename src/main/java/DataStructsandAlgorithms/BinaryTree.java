package DataStructsandAlgorithms;

import org.apache.poi.ss.formula.functions.T;

/**
 * 数据结构：二叉树
 * 这个类中完成二叉树的构造、遍历等操作
 * 复习二叉树的性质与算法
 * */
public class BinaryTree {
    /**
     * 树有两种形式的存储
     * 一种是数组存储，另一种是二叉树节点存储
     * 两种方式各有优缺点
     * 数组存储方便查询，查询时间复杂度O(1),不方便扩容、增加节点等操作
     * 二叉树节点式存储，容易构造，直观体现数据结构，不易于查找，同时难以维护
     * */
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x){val=x;}
}
