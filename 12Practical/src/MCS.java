import java.util.Random;

public class MCS {
    //Global Counters
    static long countOn3=0;
    static long countOn2A=0;
    static long countOn2B=0;
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


    //Implementing o(n^2) A
    public static int mcsOn2A(int[] X){
        int n=X.length;
        int Max=0;

        for (int i = 0; i < n; i++) {
            int total=0;
            for (int k = 0; k < n; k++) {
                total+=X[k];
                countOn2A++;
                if (total>Max){
                    Max=total;
                }
            }
        }
        return Max;
    }

    //Implementing O(n^2)B

    public static int mcsOn2B(int[] X){
        int n=X.length;
        int[] totalTo=new int[n+1];

        for (int r = 0; r < n; r++) {
            totalTo[r]=totalTo[r-1]+X[r-1];
        }

        int Max=0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j <n; j++) {
                int total=totalTo[j+1]-totalTo[i];
                countOn2B++;
                if (total>Max){
                    Max=total;
                }
            }
        }
        return Max;
    }


}
