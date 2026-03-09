import java.io.*;
import java.util.*;
public class Anagrams {
    public static void main(String[] args) {

        HashMap<String, HashSet<String>> D = new HashMap<>();

        try{

            BufferedReader reader = new BufferedReader(new FileReader("joyce1922_ulysses.text"));
            String line;

            while ((line = reader.readLine()) != null){

                String[] words = line.split("\\s+");

                for (String word : words){

                    //clean word (Leave apostrophes)
                    word = word.replaceAll("[^a-zA-Z']", "");
                    word = word.toLowerCase();

                    if (word.length() == 0){
                        continue;
                    }

                    // Create the Signature
                    char[] chars = word.toCharArray();
                    Arrays.sort(chars);
                    String key =  new String(chars);

                    //Insert into dictionary
                    D.putIfAbsent(key, new HashSet<>());
                    D.get(key).add(word);
                }
            }
            reader.close();


        }catch (IOException e){
            System.out.println("Error reading file");
            e.printStackTrace();

        }

//Print anagrams (Lists with more than 1 word)
        for (String key : D.keySet()) {

            HashSet<String> list = D.get(key);

            if (list.size() > 1) {
                System.out.println(key + " : " + list);
            }
        }


        // Write Latex File
        try{
            PrintWriter writer = new PrintWriter("theAnagrams.tex");
            writer.println("\\begin{verbatim}");



            for (String key : D.keySet()) {
                HashSet<String> list = D.get(key);
                if (list.size() > 1) {
                    writer.println(key + " : " + list);

                }
            }
            writer.println("\\end{verbatim}");
            writer.close();
        }catch(Exception e){
            System.out.println("Error writing to the latex file");
        }

    }
}