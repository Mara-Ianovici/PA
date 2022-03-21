public class Main {
    public static void main(String[] args) {
        Catalog myCatalog = new Catalog();

        Item i1 = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth");
        Item i2 = new Article("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", 2021, "James Gosling & others");

        myCatalog.addItem(i1);
        myCatalog.addItem(i2);

        System.out.println(myCatalog);
    }
}
