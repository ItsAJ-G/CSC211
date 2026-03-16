import java.io.*;
import java.util.*;

public class tryHeapsort {

    static String[] heap;
    static int heapSize;

    static int parent(int i) {return (i - 1) / 2;}
    static int left(int i) {return 2*i+1;}
    static int right(int i) {return 2*i+2;}

    static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void shiftdown(String[] arr, int i, int size) {
        int smallest = i;
        int l = left(i);
        int r = right(i);

        if (l < size && arr[l].compareTo(arr[smallest]) < 0) smallest = l;
        if (r < size && arr[r].compareTo(arr[smallest]) < 0) smallest = r;

        if (smallest != i) {
            swap(arr, i, smallest);
            shiftdown(arr, smallest, size);
        }
    }

    static void buildHeapBottomUp(String[] arr) {
        int n = arr.length;
        for (int i = n/2 - 1; i >= 0; i--) {
            shiftdown(arr, i, n);
        }
    }

    static void shiftup(String[] arr, int i) {
        while (i > 0 && arr[parent(i)].compareTo(arr[i]) > 0) {
            swap(arr, i, parent(i));
            i = parent(i);
        }
    }

    static void insertTopDown(String[] arr, int i) {
        shiftup(arr, i);
    }

    static void buildHeapTopDown(String[] arr) {
        for (int i = 1; i < arr.length; i++) {
            insertTopDown(arr, i);
        }
    }

    static String[] heapSort(String[] arr) {
        int n = arr.length;
        String[] sorted = new String[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = arr[0];
            arr[0] = arr[n - 1 - i];
            shiftdown(arr, 0, n - 1 - i);
        }
        return sorted;
    }

    static String[] loadWords(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            for (String word : line.trim().split("\\s+")) {
                word = word.toLowerCase().replaceAll("[^a-z]", "");
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }

        br.close();
        return words.toArray(new String[0]);
    }

    static void printFirst(String[] arr, int n){
        for (int i = 0; i < Math.min(n, arr.length); i++) {
            System.out.println(" " + arr[i]);
        }
    }

    static void printRange(String[] arr, int start, int end){
        for(int i = start; i < end && i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) throws IOException {

        String[] small = {"mercedes","porshe","bmw","audi","nissan","mazda","buggati","ferrari","mustang"};
        System.out.println("=====Small Array Test======");

        String[] testBottomUp = small.clone();
        buildHeapBottomUp(testBottomUp);
        String[] sorted = heapSort(testBottomUp);
        System.out.println("Bottom up sorted:");
        printFirst(sorted, sorted.length);

        String[] testTopDown = small.clone();
        buildHeapTopDown(testTopDown);
        String[] sortedTopDown = heapSort(testTopDown);
        System.out.println("Top Down sorted:");
        printFirst(sortedTopDown, sortedTopDown.length);

        String[] words = loadWords("joyce1922_ulysses.text");
        System.out.println("\njoyce1922_ulysses words loaded: " + words.length);

        String[] bottomUpwords = words.clone();
        long StartTimeBottomUp = System.nanoTime();
        buildHeapBottomUp(bottomUpwords);
        String[] sortedBottomUp = heapSort(bottomUpwords);
        long TimeBottomUp = System.nanoTime() - StartTimeBottomUp;

        String[] topdownwords = words.clone();
        long StartTimeTopDown = System.nanoTime();
        buildHeapTopDown(topdownwords);
        String[] sortedTopdown = heapSort(topdownwords);
        long TimeTopDown = System.nanoTime() - StartTimeTopDown;

        System.out.println("\n=====Timing Results=====");
        System.out.printf("Top-Down build + sort:  %,d ns (%.3f s)%n", TimeTopDown, TimeTopDown / 1e9);
        System.out.printf("Bottom-Up build + sort: %,d ns (%.3f s)%n", TimeBottomUp, TimeBottomUp / 1e9);
        System.out.printf("Difference:             %,d ns%n", Math.abs(TimeBottomUp - TimeTopDown));

        System.out.println("\nWords 1000–1010:");
        printRange(sortedBottomUp, 1000, 1010);
    }
}