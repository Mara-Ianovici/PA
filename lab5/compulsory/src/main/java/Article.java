public class Article extends Item {
    public Article(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.ARTICLE);
    }

    public Article(String identifier, String title, String location, Integer year) {
        super(identifier, title, location, year, "", ItemType.ARTICLE);
    }

    public Article(String identifier, String title, String location, String author) {
        super(identifier, title, location, 0, author, ItemType.ARTICLE);
    }

    public Article(String identifier, String title, String location) {
        super(identifier, title, location, 0, "", ItemType.ARTICLE);
    }
}
