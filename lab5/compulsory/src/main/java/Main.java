import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Catalog myCatalog = new Catalog();

        Item i1 = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth");
        Item i2 = new Article("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", 2021, "James Gosling & others");

        myCatalog.addItem(i1);
        myCatalog.addItem(i2);
//        myCatalog.addItem(i1);

//        myCatalog.save();

        Catalog altCatalog = new Catalog();
//        altCatalog.load("target/out.json");

//        System.out.println(myCatalog);
        System.out.println(altCatalog);
    }
}
