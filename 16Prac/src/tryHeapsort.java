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

}
