package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<String> wordList = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {
        wordList.add(word);
        System.out.println(player.getName() + ": " + word);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : wordList) stringBuilder.append(s);

        return stringBuilder.toString();
    }
}
