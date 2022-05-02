import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        ExtractTool extractTool = new ExtractTool("src/main/resources/concap.csv");
        extractTool.read();
//        double distance = Utils.getCitiesDistance("Bucharest", "Oslo");
//        if(distance == -1.0){
//            System.out.println("The cities were not valid");
//        }
//        else
//            System.out.println("The distance between the 2 cities is " + distance);
    }
}