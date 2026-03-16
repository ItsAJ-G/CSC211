import java.io.*;
import java.util.*;

public class tryHeapsort {

    static String[] heap;
    static int heapSize;

    //Heap Helpers
    static int parent(int i) {return (i - 1) / 2;}
    static int left(int i) {return 2*i+1;}
    static int right(int i) {return 2*i+2;}

    static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Min-Heap: Shift down

    static void shiftdown(String[] arr, int i, int size) {
        int smallest = i;
        int l=left(i);
        int r=right(i);

        if (l<size && arr[l].compareTo(arr[smallest]) < 0) {smallest=l;}
        if (r<size && arr[r].compareTo(arr[smallest]) < 0) {smallest=r;}

        if (smallest!=i) {
            swap(arr, i, smallest);
            shiftdown(arr, smallest, size);
        }
    }

    //(a)Bottom-up Heap build

    static void buildHeapBottomUp(String[] arr) {
        int n = arr.length;

        for (int i=n/2-1;i>=0;i--) {
            shiftdown(arr, i, n);
        }
    }

    //(b)Top-Down Heap build

    static void shiftup(String[] arr, int i) {
        while (i>0 && arr[parent(i)].compareTo(arr[i])>0) {
            swap(arr, i, parent(i));
            i=parent(i);
        }
    }

    static void insertTopDown(String[] arr, int i) {
        shiftup(arr, i);
    }

    static void buildHeapTopDown(String[] arr) {
        for (int i=1;i<arr.length;i++) {
            insertTopDown(arr, i);
        }
    }
}
