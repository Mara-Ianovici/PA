package app;

import model.Game;
import model.Player;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.addPlayer(new Player("Player 1", game));
        game.addPlayer(new Player("Player 2", game));
        game.addPlayer(new Player("Player 3", game));

        game.play();
    }
}
