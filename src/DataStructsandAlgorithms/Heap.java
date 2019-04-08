package DataStructsandAlgorithms;


import java.util.LinkedList;
import java.util.Queue;

/** 数据结构：堆&堆排序（二叉树实现方式）(这里实现小顶堆)*/
public class Heap {
    public int value=0;
    public Heap leftNode;
    public Heap rightNode;

    //构造函数
    public Heap(int value){
        this.value = value;
        leftNode = null;
        rightNode = null;
    }

    //堆化函数（二叉树实现方式)(递归、自顶向下)
    public static Heap buildHeap(Heap root){
        if(root.leftNode==null&&root.rightNode!=null){
            if(root.rightNode.value<root.value){
                int tmp = root.value;
                root.value = root.rightNode.value;
                root.rightNode.value = tmp;
            }
        }else if(root.leftNode!=null&&root.rightNode==null){
            if(root.leftNode.value<root.value){
                int tmp = root.value;
                root.value = root.leftNode.value;
                root.leftNode.value = tmp;
            }
        }else{
            if(root.leftNode.value<=root.rightNode.value&&root.leftNode.value<root.value){
                int tmp = root.value;
                root.value = root.leftNode.value;
                root.leftNode.value = tmp;
            }else{
                if(root.rightNode.value<root.value){
                    int tmp = root.value;
                    root.value = root.rightNode.value;
                    root.rightNode.value = tmp;
                }
            }
        }
        if(root.leftNode!=null) root.leftNode = buildHeap(root.leftNode);
        if(root.rightNode!=null) root.rightNode = buildHeap(root.rightNode);
        return root;
    }

    public Heap insertNode(int insert){
        Heap toBeInsertNode = this.findToBeInsertNode(this);
        Heap newNode = new Heap(insert);
        if(toBeInsertNode.leftNode!=null){
            toBeInsertNode.rightNode = newNode;
        }else{
            toBeInsertNode.leftNode = newNode;
        }
        return this;
    }

    public Heap findToBeInsertNode(Heap root){
        LinkedList<Heap> heapQueue = new LinkedList();
        Heap nowNode = root;
        while(nowNode.rightNode!=null&& nowNode.leftNode!=null){
            heapQueue.add(nowNode.leftNode);
            heapQueue.add(nowNode.rightNode);
            heapQueue.removeFirst();
            nowNode = heapQueue.getFirst();
        }
        if(nowNode.leftNode==null||nowNode.rightNode==null){
            return nowNode;
        }else {
            heapQueue.removeFirst();
            return heapQueue.getFirst();
        }
    }

    //pop出堆顶最小的元素，重做堆
    public int deleteMin(Heap root){
        return 0;
    }
}
