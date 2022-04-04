import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {
    private final List<Tile> tileList = new ArrayList<>();

    public Bag() {
        addTiles();
    }

    public void addTiles(){
        for (char letter = 'a'; letter <= 'z'; letter++) {
            {
                int randomValue = ThreadLocalRandom.current().nextInt(1, 11);
                Tile tile = new Tile(letter, randomValue);

                for (int index = 0; index < 10; index++) {
                    tileList.add(tile);
                }
            }
        }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();

        for (int index = 0; index < howMany; index++) {
            if (tileList.isEmpty()) {
                break;
            }

            int randomTile = ThreadLocalRandom.current().nextInt(0, tileList.size());
            extracted.add(tileList.get(randomTile));
            tileList.remove(tileList.get(randomTile));
        }

        return extracted;
    }

}
