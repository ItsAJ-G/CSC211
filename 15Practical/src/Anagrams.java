import java.nio.*;
import java.util.*;
import java.io.*;

public class Anagrams {

    static  String signature(String word){
        char[] arr = word.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }


}
