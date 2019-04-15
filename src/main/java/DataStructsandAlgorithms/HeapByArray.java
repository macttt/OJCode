package DataStructsandAlgorithms;


import Utils.CommonUtil;

import java.util.Arrays;


/**
 *
 * 大顶堆
 * 数组型堆的数据结构*/
public class HeapByArray {
    public static void main(String[] args){
        int[] anArray = {5,3,1,6,1,3,6,8,6,4,3,4,9,10,24,13,11,14};
        HeapByArray heap = new HeapByArray(anArray.length+1);
        for(int i=0;i<anArray.length;i++) heap.insert(anArray[i]);
        heap.printHeapArray();
        System.out.println(heap.popMax());
        heap.printHeapArray();
        int[] tobeSorted = Arrays.copyOf(anArray, anArray.length);
        heapSort(tobeSorted);
//        LinkedList test = new LinkedList();
        int[] Allcnt = new int[10000000];
        Double d;
        for(int i=0;i<10000000;i++){
            d = Math.random()*100000000;
            Allcnt[i] = d.intValue();
//            System.out.println(Allcnt[i]);
        }
        long start = System.currentTimeMillis();
        heapSort(Allcnt);
        long end = System.currentTimeMillis();
        System.out.println("花费时间："+(end-start));
//        int[] a = test.toArray();
//        HeapByArray instance = new HeapByArray();
//        instance.copyArray(Allcnt);
//        instance.printHeapArray();
//        System.out.println("个数："+instance.heapArray[0]+"，第一个:"+instance.heapArray[1]+",2:"+instance.heapArray[2]+",3:"+instance.heapArray[3]);
    }

    private int heapSize;
    private int[] heapArray;
    public HeapByArray(){
        this.heapArray = new int[10];
        Arrays.fill(heapArray,-1);
        this.heapArray[1] = 1;
        this.heapSize = 10;
        heapArray[0] = 1;
    }

    public HeapByArray(int size){
        this.heapSize = size;
        this.heapArray = new int[heapSize];
    }

    public HeapByArray(int rootValue,int size){
        if(size<=0){
            this.heapSize = 10;
            this.heapArray = new int[10];
        }else{
            this.heapSize = 10;
            this.heapArray = new int[heapSize];
        }
        heapArray[1] = rootValue;
        heapArray[0]++;
    }


    //让copy其他数组进堆，然后进行堆化或者排序
    public HeapByArray copyArray(int[] source){
        if(heapArray.length<=source.length){
            heapArray = new int[source.length+1];
        }
        heapSize = source.length+1;
        for(int i=0;i<source.length;i++) heapArray[i+1] = source[i];
        heapArray[0] = source.length;
        this.maxHeapfy();
        return this;
    }

    //把已有的数组堆化
    public void maxHeapfy(){
        long start = System.currentTimeMillis();
        for(int i=heapArray[0]/2;i>0;i--){
            buildInClassHeap(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("总花费时间："+(end-start));
    }

    //适应标准化接口的内部函数，按照下标造堆
    //把当前节点作为根节点造堆，时间复杂度为O(n)
    /** 这里的下滤可以看作让当前待堆化根节点空出来，看看下面有没有要上来的，直接顶上来*/
    private void buildInClassHeap(int index){
        int child,parent,X;
        parent = index;
        X = heapArray[parent];
        for (parent=index;parent*2<=heapArray[0];parent = child){
            child = parent*2;
            if(child!=heapArray[0]&&heapArray[child]<heapArray[child+1]) child++;
            if(X>=heapArray[child]){
                break;
            }else{
                heapArray[parent] = heapArray[child];
            }
        }
        heapArray[parent] = X;
    }

    //堆化的静态函数
    public static void maxHeapfy(int[] input){
        for(int i=(input.length-1)/2;i>=0;i--){
            buildStaticHeap(input,i);
        }
    }

    //有
    public static void maxHeapfy(int[] input,int end){
        for(int i=(end-1)/2;i>=0;i--){
            buildStaticHeap(input,i,end);
        }
    }

    //在start与end范围内进行堆化
    private static void buildStaticHeap(int[] heap,int start,int end){
        int child,parent,X;
        parent = start;
        X = heap[parent];
        for (parent=start;parent*2+1<=end;parent = child){
            child = parent*2+1;
            if(child!=end&&heap[child]<heap[child+1]) child++;
            if(X>=heap[child]){
                break;
            }else{
                heap[parent] = heap[child];
            }
        }
        heap[parent] = X;
    }

    private static void buildStaticHeap(int[] heap,int index){
        int child,parent,X;
        parent = index;
        X = heap[parent];
        for (parent=index;parent*2+1<=heap.length-1;parent = child){
            child = parent*2+1;
            if(child!=heap.length-1&&heap[child]<heap[child+1]) child++;
            if(X>=heap[child]){
                break;
            }else{
                heap[parent] = heap[child];
            }
        }
        heap[parent] = X;
    }

    //判断是否为空堆
    public boolean isEmpty(){
        return this.heapArray[0]==0;
    }

    //判断是否已满
    public boolean isFull(){
        return this.heapArray[0]==heapArray.length-1;
    }

    //pop出堆顶节点（大顶堆为最大节点）
    public int popMax(){
        if(this.isEmpty()){
            System.out.println("Heap is empty!");
            return -1;
        }
        int tmp = this.heapArray[1];
        heapArray[1] = heapArray[heapArray[0]];
        heapArray[heapArray[0]] = -1;
        heapArray[0]--;
        int X = heapArray[1];
        int child,parent;
        for(parent=1;parent*2<=heapArray[0];parent = child){
            child = parent*2;
            if(child!=heapArray[0]&&heapArray[child]<heapArray[child+1]) child++;
            if(X<heapArray[child]){
                heapArray[parent] = heapArray[child];
            }else{
                break;
            }
        }
        heapArray[parent] = X;
        return tmp;
    }

    //向堆中插入数据
    //时间复杂度为O(logn)
    public void insert(int num){
        if(this.isFull()){
            System.out.println("Heap is full!");
            return;
        }
        this.heapArray[0]++;
        this.heapArray[heapArray[0]] = num;
        int index = heapArray[0];
        int parent = index/2;
        while(heapArray[parent]<num&&parent>0){
            heapArray[index] = heapArray[parent];
            index = parent;
            parent/=2;
        }
        heapArray[index] = num;
    }


    //打印堆内节点
    public void printHeapArray(){
        int[] toBePrinted = Arrays.copyOfRange(this.heapArray,1,heapArray[0]+1);
        CommonUtil.printArray(toBePrinted);
    }

    /**堆排序（将数组堆化后，让最大/小的堆顶元素与最后的节点交换，再堆化剩余数组，重复操作） */
    /**每排出一个数字进行一次类似于deleteMax（pop）的操作,时间复杂度为O（logn）*/
    /**建堆操作耗时O（n） */
    public static void heapSort(int[] toBeSorted){
        maxHeapfy(toBeSorted);
        int tmp;
        int len = toBeSorted.length;
        for(int i=0;i<toBeSorted.length;i++){
            tmp = toBeSorted[0];
            toBeSorted[0] = toBeSorted[len-1-i];
            toBeSorted[len-1-i] = tmp;
//            maxHeapfy(toBeSorted,toBeSorted.length-1-i-1);
            int X = toBeSorted[0];
            int child,parent;
            for(parent=0;parent*2+1<=toBeSorted.length-1-i-1;parent = child){
                child = parent*2+1;
                if(child!=toBeSorted.length-1&&toBeSorted[child]<toBeSorted[child+1]) child++;
                if(X<toBeSorted[child]){
                    toBeSorted[parent] = toBeSorted[child];
                }else{
                    break;
                }
            }
            toBeSorted[parent] = X;
        }
    }
}
