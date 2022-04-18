package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private final String name;
    private int score;
    private final Game game;
    private int id;
    private List<Tile> remainingTiles;

    public Player(String name, Game game) {
        this.name = name;
        this.score = 0;
        this.remainingTiles = new ArrayList<>();
        this.game = game;
    }

    /**
     * Checks the validity of a word.
     * (If it is in the dictionary and uses only the givel letters)
     * @param word the word to check
     * @param tileArray the tile array that is left
     */
    private boolean checkWord(String word, List<Tile> tileArray){

        StringBuilder wordCopy = new StringBuilder(word);
        int isContinue = 1;

        while(wordCopy.length() > 0 && isContinue == 1)
        {
            isContinue = 0;

            for(int index2 = 0; index2 < tileArray.size(); index2++)
                if(wordCopy.charAt(0) == tileArray.get(index2).getLetter())
                {
                    tileArray.remove(index2);
                    wordCopy.deleteCharAt(0);

                    isContinue = 1;
                    break;
                }
        }

        if(wordCopy.length() > 0)
            return false;
        else
        {
            if(game.getDictionary().isWord(word))
            {
                for (Tile tile : tileArray) game.getBag().addTile(tile);

                remainingTiles = tileArray;
                return true;
            }
            else{
                System.out.println("This is not a valid word. No points will be added.");
                return false;
            }
        }

    }

    /**
     * It extracts letters from the bag and receives an input from the user.
     * @return true if the word could be submitted, false otherwise.
     */
    private boolean submitWord() throws InterruptedException{

        if(game.getBag().getTileList().size() < 7){
            return false;
        }

        List<Tile> extracted = game.getBag().extractTiles(7 - remainingTiles.size());
        StringBuilder letters = new StringBuilder();

        if (extracted.isEmpty()) {
            return false;
        }

        for (int index = 0; index < (7 - remainingTiles.size()); index++)
            letters.append(extracted.get(index).getLetter());

        for (Tile remainingTile : remainingTiles) letters.append(remainingTile.getLetter());

        System.out.println("You need to make a word with the following letters: " + letters);

        Scanner scanner= new Scanner(System.in);
        String word= scanner.nextLine();

        if(!checkWord(word, extracted))
            System.out.println("You did not use the letters given. No points were given.");
        else{
            game.getBoard().addWord(this, word);
            score += game.getScore(word);
        }

        return true;
    }

    @Override
    public void run() {
        synchronized (game) {
            while (game.getBag().getTileList().size() >= 7 - remainingTiles.size()) {

                if (game.getCurrentPlayerId() == this.id) {
                    try {
                        if(!submitWord())
                            System.out.println("The word couldn't be submitted.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Game.currentPlayer++;

                    if (Game.currentPlayer >= game.getPlayerNumber() + 1)
                        Game.currentPlayer = 1;

                    if(game.getBag().getTileList().size() < 7)
                    {
                        System.out.println("Game is over");

                        int maxScoreId = 0;
                        int maxScore = game.getPlayers().get(0).getScore();

                        for(int index = 1; index < game.getPlayers().size(); index++)
                            if(game.getPlayers().get(index).getScore() > maxScore)
                            {
                                maxScoreId = game.getPlayers().get(index).getId();
                                maxScore = game.getPlayers().get(index).getScore();
                            }

                        System.out.println("Player " + maxScoreId + " won!");
                        break;
                    }
                    game.notifyAll();
                }
                else
                {
                    try {
                        game.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public int getId() {
        return id;
    }
}
