import java.lang.Math.*;
import java.io.*;
import java.text.*;

public class timeMethods {
    public static int N = 30000;

    public static void main(String args[]) {

        DecimalFormat twoD = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0, runTime2 = 0;
        double totalTime = 0.0;
        double time;

        int n = N;
        int repetition, repetitions = 30;
        runTime = 0;

        RecordNode[] arr = new RecordNode[N];
        int index = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("ulysses.numbered"));
            String line;
            while ((line = br.readLine()) != null && index < N) {
                String[] parts = line.split(" ", 2);

                int key = Integer.parseInt(parts[0]);
                String data = "";

                if (parts.length > 1) {
                    data = parts[1];
                }

                arr[index] = new RecordNode(key, data);
                index++;
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Error");
            return;
        }

        sortArray(arr, n);

        int[] searchkeys = new int[30];
        for (int i = 0; i < 30; i++) {
            searchkeys[i] = (int) (Math.random() * 32654) + 1;
        }

        for (repetition = 0; repetition < repetitions; repetition++) {
            start = System.currentTimeMillis();
            for (int i = 0; i < 30; i++) {
                linearsearch(arr, n, searchkeys[i]);
            }
            finish = System.currentTimeMillis();

            time = (double) (finish - start);
            runTimeLinear += time;
            runTime2Linear += (time * time);
        }

        for (repetition = 0; repetition < repetitions; repetition++) {
            start = System.currentTimeMillis();

            for (int i = 0; i < 30; i++) {
                binarysearch(arr, 0, n - 1, searchkeys[i]);
            }
            finish = System.currentTimeMillis();

            time = (double) (finish - start);
            runTimeBinary += time;
            runTime2Binary += (time * time);
        }

        double aveRuntimeLinear = runTimeLinear / repetitions;
        double stdDeviationLinear = Math.sqrt(runTime2Linear - repetitions * aveRuntimeLinear * aveRuntimeLinear) / (repetitions - 1);

        double aveRuntimeBinary = runTimeBinary / repetitions;
        double stdDeviationBinary = Math.sqrt(runTime2Binary - repetitions * aveRuntimeBinary * aveRuntimeBinary) / (repetitions - 1);

        System.out.println("\n\nLINEAR SEARCH STATISTICS");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTimeLinear / 1000 + "s.");
        System.out.println("Total time²  =           " + runTime2Linear);
        System.out.println("Average time =           " + fiveD.format(aveRuntimeLinear / 1000) + "s. ± " + fourD.format(stdDeviationLinear) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviationLinear));
        System.out.println("Keys per run  =          30");
        System.out.println("Repetitions   =          " + repetitions);
        System.out.println("________________________________________________");

        System.out.println("\n\nBINARY SEARCH STATISTICS");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTimeBinary / 1000 + "s.");
        System.out.println("Total time²  =           " + runTime2Binary);
        System.out.println("Average time =           " + fiveD.format(aveRuntimeBinary / 1000) + "s. ± " + fourD.format(stdDeviationBinary) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviationBinary));
        System.out.println("Keys per run  =          30");
        System.out.println("Repetitions   =          " + repetitions);
        System.out.println("________________________________________________");

        System.out.println("\n\nFINAL RESULTS (4 numbers):");
        System.out.println("Linear Search Average: " + fiveD.format(aveRuntimeLinear / 1000) + " seconds");
        System.out.println("Linear Search Std Dev: " + fourD.format(stdDeviationLinear) + " milliseconds");
        System.out.println("Binary Search Average: " + fiveD.format(aveRuntimeBinary / 1000) + " seconds");
        System.out.println("Binary Search Std Dev: " + fourD.format(stdDeviationBinary) + " milliseconds");
    }

    public static RecordNote linearsearch(RecordNote[] arr,int n,int key){
        for (int i = 0; i < n; i++) {
            if (arr[i].key==key) {
                return arr[i];
            }
        }
        return null;
    }

    public static RecordNode binarysearch(RecordNode[] arr,int left, int right,int key){
        if(right>=left){
            int mid=left+(right-left)/2;
            if(arr[mid].key==key){
                return arr[mid];
            }
            if (arr[mid].key>key){
                return binarysearch(arr,left,mid-1,key);
            }
            return binarysearch(arr,mid+1,right,key);
        }
        return  null;
    }

    public static void sortArray(RecordNode[] arr,int n){
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1; j++) {
                if(arr[j].key>arr[j+1].key){
                    RecordNode temp= arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}