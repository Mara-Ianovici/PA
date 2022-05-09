import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private final String name;
    private final int score;
    private Game game;
    private boolean running;
    private int id;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.running = false;
    }

    private boolean submitWord() throws InterruptedException {
        List<Tile> extracted = game.getBag().extractTiles(7);

        if (extracted.isEmpty()) {
            return false;
        }

        int randomLength = ThreadLocalRandom.current().nextInt(2, 7);

        StringBuilder word = new StringBuilder();

        for (int index = 0; index < randomLength; index++)
            word.append(extracted.get(index).getLetter());

        game.getBoard().addWord(this, word.toString());

        sleep(50);

        return true;
    }

    @Override
    public void run() {
        System.out.println("ce " + game.getCurrentPlayerId() + " " + this.id);


        synchronized (game) {
            while (true) {
                if (game.getCurrentPlayerId() == this.id) {
                    try {
                        System.out.println("Thread " + this.id + " is now working.");
                        submitWord();
                        Game.currentPlayer++;

                        if (Game.currentPlayer >= 4)
                            Game.currentPlayer = 1;

                        game.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("Thread " + this.id + " is waiting.");
                        game.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
}