import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    static Set<String> wordList;

    Dictionary(){
        wordList = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/wordlist.txt"))){
            String line;
            while ((line = bufferedReader.readLine()) != null)
                wordList.add(line.toUpperCase());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isWord(String word) {
        return wordList.contains(word.toUpperCase());
    }

}
