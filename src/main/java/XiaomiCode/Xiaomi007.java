package XiaomiCode;


import java.util.Scanner;

public class Xiaomi007 {
    public int getLeastMove(int end,int[] num){
        int ret = 0;
        if(num.length==1){
            ret = 0;
        }else if(end == 0) {
            ret = 0;
        }else {
            ret = getLeastMove(end -1,num ) + getMoveStep(end -1,num,num[end]);
        }
        return ret;
    }
    private int getMoveStep(int end,int[] subNum,int get){
        int count =0;
        for(int i = end;i>=0;i--){
            if(subNum[i]>get){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        String a = "123";
        System.out.println(a.substring(3).length());

        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            // please write your code here
            String[] numStrs = line.split(",");
            // System.out.println("answer");
            int[] n = new int[numStrs.length];
            for(int i=0;i<numStrs.length;i++){
                n[i] = Integer.parseInt(numStrs[i]);
            }
            Xiaomi007 b = new Xiaomi007();
            System.out.println(b.getLeastMove(numStrs.length-1,n));
        }
    }
}

