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

    //Implementation  of O(n)
    public static int mcsOn0(int[] X){
        int Max=0;
        int MaxFinal=0;

        for (int r = 0; r < X.length; r++) {
            MaxFinal=Math.max(MaxFinal+X[r],0);
            countOn0++;
            Max=Math.max(Max,MaxFinal);
        }
        return Max;
    }

    //Main method
    public static void main(String[] args) {
        int[] ages={1,2,3,4,5,6,7,8,9,10};

        for(int n:ages){
            countOn3=0;
            countOn2A=0;
            countOn2B=0;
            countOn0=0;

            int[] X=generateArray(n);
            mcsOn3(X);
            mcsOn2A(X);
            mcsOn2B(X);
            mcsOn0(X);

            System.out.println("n= "+n);
            System.out.println("O(n^3) count= "+countOn3);
            System.out.println("O(n^2)A count= "+countOn2A);
            System.out.println("O(n^2)B count= "+countOn2B);
            System.out.println("O(n^0)A count= "+countOn0);
            System.out.println("-------------------------------");
        }
    }
    public static int[] generateArray(int n){
        Random rand=new Random();
        int[] X=new int[n];

        for (int r = 0; r < n; r++) {
            int value=rand.nextInt(n)+1;
            if (rand.nextBoolean()){
                value*=-1;
            }
            X[r]=value;
        }
        return X;
    }
}
