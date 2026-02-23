import java.lang.Math.*;
import java.io.*;
import java.text.*;

public class timeMethods{
    public static int N =30000;
    public static void main(String args[]){

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

        RecordNode[] arr=new RecordNode[N];
        int index=0;

        try{
            BufferedReader br=new BufferedReader(new FileReader("ulysses.numbered"));
            String line;
            while ((line= br.readLine()) !=null && index<N){
                String[] parts=line.split(" ",2);

                int key=Integer.parseInt(parts[0]);
                String data="";

                if (parts.length>1){
                    data=parts[1];
                }

                arr[index]=new RecordNode(key,data);
                index++;
            }
            br.close();

        }catch (Exception e){
            System.out.println("Error");
            return;
        }

        sortArray(arr,n);

        int[] searchkeys=new int[30];
        for (int i = 0; i < 30; i++) {
            searchkeys[i]=(int)(Math.random()*32654)+1;
        }

        for(repetition = 0; repetition < repetitions; repetition++) {
            start = System.currentTimeMillis();
            for(int i=0;i<30;i++){
                linearsearch (arr,n,searchkeys[i]);
            }
            finish = System.currentTimeMillis();

            time = (double)(finish - start);
            runTime += time;
            runTime2 += (time*time);

            for(repetition = 0; repetition < repetitions; repetition++) {
                start = System.currentTimeMillis();

                for(int i=0;i<30;i++){
                    binarysearch(arr,0,n-1,searchkeys[i]);
                }
                finish = System.currentTimeMillis();
            }

            finish = System.currentTimeMillis();

            time = (double)(finish - start);
            runTime += time;
            runTime2 += (time*time);
        }

        double aveRuntime = runTime/repetitions;
        double stdDeviation =
                Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

        System.out.printf("\n\n\fStatistics\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTime/1000 + "s.");
        System.out.println("Total time\u00b2  =           " + runTime2);
        System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000)
                + '\u00B5' + "s. ");

        System.out.println("Repetitions  =             " + repetitions);
        System.out.println("________________________________________________");
        System.out.println();
        System.out.println(); }	}

static void oneofyourMethods(int n,
                             yourMethodParameter1,
                             yourMethodParameter2, . . . ) {
// The declarations and body of your method / s   
// The final statement of this code.} }