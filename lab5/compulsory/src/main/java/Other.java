public class Other extends Item {
    public Other(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.OTHER);
    }

    public Other(String identifier, String title, String location, Integer year) {
        super(identifier, title, location, year, "", ItemType.OTHER);
    }

    public Other(String identifier, String title, String location, String author) {
        super(identifier, title, location, 0, author, ItemType.OTHER);
    }

    public Other(String identifier, String title, String location) {
        super(identifier, title, location, 0, "", ItemType.OTHER);
    }
}
