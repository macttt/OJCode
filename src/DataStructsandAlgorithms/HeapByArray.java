package DataStructsandAlgorithms;


/** 数组型堆的数据结构*/
public class HeapByArray {
    private int heapSize;
    private int[] heapArray;
    public HeapByArray(){
        this.heapSize = 0;
        this.heapArray = new int[heapSize];
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

    public HeapByArray copyArray(int[] source){
        if(heapArray.length<=source.length){
            heapArray = new int[source.length+1];
        }


        return this;
    }

    public void buildHeap(){

    }
}
