public class Book extends Item {
    public Book(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.BOOK);
    }

    public Book(String identifier, String title, String location, Integer year) {
        super(identifier, title, location, year, "", ItemType.BOOK);
    }

    public Book(String identifier, String title, String location, String author) {
        super(identifier, title, location, 0, author, ItemType.BOOK);
    }

    public Book(String identifier, String title, String location) {
        super(identifier, title, location, 0, "", ItemType.BOOK);
    }
}
