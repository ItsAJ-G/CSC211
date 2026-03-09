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


}
