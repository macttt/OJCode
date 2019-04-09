package DataStructsandAlgorithms;


import java.util.Arrays;

/**
 *
 *
 *
 * 大顶堆
 * 数组型堆的数据结构*/
public class HeapByArray {
    private int heapSize;
    private int[] heapArray;
    public HeapByArray(){
        this.heapArray = new int[10];
        Arrays.fill(heapArray,-1);
        this.heapArray[1] = 1;
        this.heapSize = 0;
        heapArray[0] = 1;
    }

    public HeapByArray(int size){
        this.heapSize = 0;
        this.heapArray = new int[heapSize];
    }

    public HeapByArray(int rootValue,int size){
        if(size<=0){
            this.heapSize = 1;
            this.heapArray = new int[10];
        }else{
            this.heapSize = 1;
            this.heapArray = new int[heapSize];
        }
        heapArray[1] = rootValue;
    }

    //让copy其他数组进堆，然后进行堆化或者排序
    public HeapByArray copyArray(int[] source){
        if(heapArray.length<=source.length){
            heapArray = new int[source.length+1];
        }


        return this;
    }

    //让数组成堆
    public void buildHeap(){
        buildInClassHeap(1);
    }

    private void buildInClassHeap(int index){
        if(index*2<heapArray.length-1&&heapArray[index*2]>=0) buildInClassHeap(index*2);
        if(index*2+1<heapArray.length-1&&heapArray[index*2+1]>=0) buildInClassHeap(index*2+1);
        if(heapArray[index]>heapArray[index/2]){
            int tmp = heapArray[index/2];
            heapArray[index/2] = heapArray[index];
            heapArray[index] = tmp;
        }
    }
}
