import java.util.*;

public class Main {
    static final int N=1<<20;
    static final int USE=950000;
    static final int REPETITIONS=30;

    static KeyValue[] data=new KeyValue[N];

    public static void main(String[] args) {
        generateData();

        double[] loadFactors = {0.75, 0.80, 0.85, 0.90, 0.95};
        System.out.println("Load\tOpenHash(ms)\tChainedHash(ms)");

        for (double alpha:loadFactors){
            int m=(int)(USE/alpha);

            long openTime=timeOpenHash(m);
            long chainTime=timeChainHash(m);

            System.out.println(alpha + "\t" + openTime + "\t\t" + chainTime);
        }
    }

    static void generateData(){
        List<Integer> keys=new ArrayList<>();
        for (int i=0;i<N;i++){
            keys.add(i);
        }
        Collections.shuffle(keys);

        for (int i=0;i<N;i++){
            data[i]=new KeyValue(
                    String.valueOf(keys.get(i)),
                    String.valueOf(i+1)
            );

        }
    }
    static long timeOpenHash(int m){
        long total=0;
        for (int r=0;r<REPETITIONS;r++){
            OpenHash table=new OpenHash(m);

            for (int i=0;i<USE;i++){
                table.insert(data[i].key,data[i].value);
            }

            long start=System.nanoTime();

            for (int i = 0; i < USE; i++)
                table.lookup(data[i].key);

            long end = System.currentTimeMillis();

            total += (end - start);
        }
        return total/REPETITIONS;
    }

    static long timeChainHash(int m){
        long total=0;
        for (int r=0;r<REPETITIONS;r++){
            ChainHash table=new ChainHash(m);

            for (int i=0;i<USE;i++){
                table.insert(data[i].key,data[i].value);
            }

            long start=System.nanoTime();

            for (int i = 0; i < USE; i++)
                table.lookup(data[i].key);

            long end = System.currentTimeMillis();

            total += (end - start);
        }
        return total/REPETITIONS;
    }

}
