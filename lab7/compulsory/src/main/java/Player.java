import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Runnable{
    private final String name;
    private Game game;
    private boolean running;

    public Player(String name) { this.name = name; }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);

        if (extracted.isEmpty()) {
            return false;
        }

        int randomLength = ThreadLocalRandom.current().nextInt(2, 7);

        StringBuilder word = new StringBuilder();

        for(int index = 0 ; index < randomLength; index ++)
            word.append(extracted.get(index).getLetter());

        game.getBoard().addWord(this, word.toString());

        try{
            Thread.sleep(50);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        return true;
    }

    @Override
    public void run(){
        submitWord();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }
}
