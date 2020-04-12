package CodeTest;

import java.util.ArrayList;

public class BitwiseNotOperation {
    public static void main(String args[]) {
        int hashCode = 1;
        Object o = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            hashCode = ~(~hashCode);
            hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
            System.out.println(hashCode);
        }
    }
}
