import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Catalog catalog1 = new Catalog();

        Item i1 = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth");
        Item i2 = new Article("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", 2021, "James Gosling & others");

        catalog1.addItem(i1);
        catalog1.addItem(i2);
//        myCatalog.addItem(i1);

        catalog1.save();

//        Catalog catalog2 = new Catalog();
//        catalog2.load("target/out.json");

        System.out.println(catalog1);
    }
}
