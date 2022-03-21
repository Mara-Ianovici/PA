import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Item> itemList;

    public Catalog() {
        this.itemList = new ArrayList<>();
    }

    public void addItem(Item e) {
        itemList.add(e);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemList)
            sb.append(item.getIdentifier() + " " + item.getTitle() + " " + item.getLocation() + " " +
                        item.getYear() + " " + item.getAuthor() + " " + item.getType() + "\n");
        return sb.toString();
    }
}
