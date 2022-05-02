import utils.ExtractTool;
import utils.Utils;

public class Main {
    public static void main(String[] args) {
        ExtractTool extractTool = new ExtractTool("src/main/resources/concap.csv");
        extractTool.read();

        Utils.printCitiesDistance(0, 1);
    }
}