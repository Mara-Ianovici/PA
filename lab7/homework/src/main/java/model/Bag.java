package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {
    private final List<Tile> tileList = new ArrayList<>();

    public Bag() {
        addTiles();
    }

    /**
     * Adds a certain number of tiles with a letter
     * @param letter the letter to be added
     * @param numberOfTiles number of tiles to be added
     * @param points number of points of the letter
     */
    private void addToBag(char letter, int numberOfTiles, int points) {
        for (int counter = 0; counter < numberOfTiles; counter++) {
            this.tileList.add(new Tile(letter, points));
        }
    }

    /**
     * Adds all the tiles necessary for the game.
     */
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

    public void addTile(Tile tile){
        tileList.add(tile);
    }

    /**
     * Extracts a number of random tiles from the bag.
     * @param howMany the number of tiles to be extracted.
     * @return The list composed of random tiles.
     */
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

    public List<Tile> getTileList() {
        return tileList;
    }
}
