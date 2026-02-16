/*
 Nathi A.J Gumede
 4533277
*/

import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Shuffling {

    public static Random rand = new Random();

    // Slow shuffling (improved but still a bad method)
    public static int[] slowShuffle(int N) {

        int[] shuffled = new int[N];
        boolean[] isNotPresent = new boolean[N + 1];

        // initially all numbers 1...N are not present
        for (int r = 1; r <= N; r++) {
            isNotPresent[r] = true;
        }

        int i = -1;   // index for shuffled array

        // repeat until the second last element is entered
        while (i < N - 2) {

            // r = 1 + (int)(random() * N)
            int r = 1 + (int)(Math.random() * N);

            // if isNotPresent[r]:
            if (isNotPresent[r]) {
                i+=1;
                shuffled[i] = r;
                isNotPresent[r] = false;
            }
            // else: generate another r by looping again
        }

        // last r does not need to be generated
        // find the only element still marked True
        for (int r = 1; r <= N; r++) {
            if (isNotPresent[r]) {
                shuffled[N - 1] = r;
                break;
            }
        }

        return shuffled;
    }

    // Biased shuffling
    public static int[] biasedShuffle(int N) {

        int[] shuffled = new int[N];

        // initialize array with 1...N
        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1;
        }

        // perform N swaps
        for (int i = 0; i < N; i++) {
            int r = rand.nextInt(N);

            int temp = shuffled[i];
            shuffled[i] = shuffled[r];
            shuffled[r] = temp;
        }

        return shuffled;
    }

    // Unbiased shuffling
    public static int[] unbiasedShuffle(int N) {

        int[] B = new int[N];

        // initialize array with 1...N
        for (int i = 0; i < N; i++) {
            B[i] = i + 1;
        }

        // correct unbiased shuffle
        for (int b = 0; b < N; b++) {
            int r = rand.nextInt(N - b) + b;
            int temp = B[b];
            B[b] = B[r];
            B[r] = temp;
        }

        return B;
    }

    // Count permutations for N = 3
    public static void countPermutations(String name, int trials) {

        Map<String, Integer> counts = new HashMap<>();

        for (int i = 0; i < trials; i++) {

            int[] result;

            if (name.equals("biased")) {
                result = biasedShuffle(3);
            } else {
                result = unbiasedShuffle(3);
            }

            String key = "" + result[0] + result[1] + result[2];

            if (!counts.containsKey(key)) {
                counts.put(key, 1);
            } else {
                counts.put(key, counts.get(key) + 1);
            }
        }

        System.out.println("\nResults for " + name + " shuffle:");
        for (String key : counts.keySet()) {
            System.out.println(key + " : " + counts.get(key));
        }
    }

    // Main method to use shuffling
    public static void main(String[] args) {

        int N = 5;

        System.out.println("Slow Shuffle:");
        printArray(slowShuffle(N));

        System.out.println("\nBiased Shuffle:");
        printArray(biasedShuffle(N));

        System.out.println("\nUnbiased Shuffle:");
        printArray(unbiasedShuffle(N));

        int trials = 60000;
        countPermutations("biased", trials);
        countPermutations("unbiased", trials);
    }

    // utility method to print arrays
    public static void printArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
