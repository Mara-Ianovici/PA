import java.util.ArrayList;
import java.util.List;

public class Board {
    List<String> wordList = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {

        wordList.add(word);
        System.out.println(player.getName() + ": " + word);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int index = 0; index < wordList.size(); index ++)
            stringBuilder.append(wordList.get(index));

        return stringBuilder.toString();
    }
}
