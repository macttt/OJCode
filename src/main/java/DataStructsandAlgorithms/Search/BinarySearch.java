package DataStructsandAlgorithms.Search;

/**
 * 实现简单的二分查找
 * */
public class BinarySearch {
    public static int BinarySearch(int[] input,int num){
        int low = 0,high = input.length-1;
        int mid = (low + high) / 2;
        while (low <= high){
            if (input[mid]==num) return mid;
            if (num > input[mid]){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
            mid = (low + high) /2;
        }
        return -1;
    }

    public static void main(String[] args){
        int[] input = {1,2,3,5,8,9,13,16,19,22,37,41,52,73,90};
        System.out.println(BinarySearch(input,23));
    }
}
