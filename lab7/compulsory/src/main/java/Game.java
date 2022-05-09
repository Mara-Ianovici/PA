import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();

    private static int playerId = 1;
    static int currentPlayer = 1;
    public static int gameCounter = 3;

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() throws InterruptedException {
        for (Player player : players) {
            new Thread(player).start();

            player.setId(playerId);
            playerId++;
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentPlayerId() {
        if (currentPlayer >= players.size())
            currentPlayer = 0;

        return currentPlayer;
    }
}
