package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();

    private static int playerId = 1;
    static int currentPlayer = 1;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void play() throws InterruptedException {
        for (Player player : players) {
            new Thread(player).start();

            player.setId(playerId);
            playerId++;
        }

        Thread timeKeeperThread = new Thread(new TimeKeeper(this));
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();
    }

    /**
     * Gets the total number of points of a word.
     * @param word The submitted word.
     * @return The score.
     */
    public int getScore(String word){
        int score = 0;
        Set<Tile> pointsArray = new HashSet<>(this.bag.getTileList());

        for(int index = 0; index < word.length(); index++)
            for(int index2 = 0; index2 < pointsArray.size(); index2++)
                if(word.charAt(index) == this.bag.getTileList().get(index2).getLetter())
                {
                    score += this.bag.getTileList().get(index2).getPoints();
                    break;
                }

        return score;
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayerId() {
        return currentPlayer;
    }

    public int getPlayerNumber() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
