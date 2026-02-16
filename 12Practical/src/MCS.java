import java.util.Random;

public class MCS {
    //Global Counters
    static long countOn3=0;
    static long countOn2=0;
    static long countOn1=0;
    static long countOn0=0;

    //Implementation of O(n^3)
    public static int mcsOn3(int[] X){
        int n=X.length;
        int Max=0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int total=0;
                for (int k = 0; k < n; k++) {
                    total+=X[k];
                    countOn3++;
                }
                if (total>Max){
                    Max=total;
                }
            }
        }
        return Max;
    }


}
