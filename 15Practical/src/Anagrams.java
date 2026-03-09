import java.nio.*;
import java.util.*;
import java.io.*;

public class Anagrams {

    static  String signature(String word) {
        char[] arr = word.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    static String stripPunctuation(String word) {
        String punctuation="[]0123456789(,.:;_.-?!)";
        int start=0;
        int end=word.length();
        while(start<end && punctuation.indexOf(word.charAt(start))>=0) {
            start++;
        }
        while (end>start && punctuation.indexOf(word.charAt(end-1))>=0) {
            end--;
        }
        return word.substring(start,end);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Anagrams <joyce1922_ulysses.text>");
            System.exit(1);
        }
        String inputFile = args[0];
        System.out.println("Input file: " + inputFile);


    }
}
