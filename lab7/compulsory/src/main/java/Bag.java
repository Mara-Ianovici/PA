import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {
    private final List<Tile> tileList = new ArrayList<>();

    public Bag() {
        addTiles();
    }

    private void addToBag(char characterToAdd, int numberOfTiles, int numberOfPoints) {
        for (int counter = 0; counter < numberOfTiles; counter++) {
            this.tileList.add(new Tile(characterToAdd, numberOfPoints));
        }
    }

    public void addTiles() {
        for (char character = 'A'; character <= 'Z'; character++)
            switch (character) {
                case 'A', 'O' -> addToBag(character, 8, 1);
                case 'B', 'C', 'M', 'P' -> addToBag(character, 2, 3);
                case 'D' -> addToBag(character, 4, 2);
                case 'E' -> addToBag(character, 12, 1);
                case 'F', 'H', 'V', 'W', 'Y' -> addToBag(character, 2, 4);
                case 'G' -> addToBag(character, 3, 2);
                case 'I' -> addToBag(character, 9, 1);
                case 'J', 'X' -> addToBag(character, 1, 8);
                case 'K' -> addToBag(character, 1, 5);
                case 'L', 'S', 'U' -> addToBag(character, 4, 1);
                case 'N', 'R', 'T' -> addToBag(character, 6, 1);
                case 'Q', 'Z' -> addToBag(character, 1, 10);
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
