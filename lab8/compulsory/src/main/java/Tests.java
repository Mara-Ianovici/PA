import java.sql.SQLException;

public class Tests {
    public void testCompulsory(){
        try {
            ContinentDAO continents = new ContinentDAO();
//            continents.create("Europe");
            Database.getConnection().commit();

            CountryDAO countries = new CountryDAO();
            int europeId = continents.findByName("Europe");

            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);
            countries.create("Moldova", europeId);

            Database.getConnection().commit();
            System.out.println(countries.getCountriesFromContinent(europeId));
            Database.getConnection().close();

        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public void testCreate()  {
        ContinentDAO continents = new ContinentDAO();
        try{
//            continents.create("Asia");
//            continents.create("Rusia");
            continents.create("America");
        }
        catch(SQLException exception){
            System.err.println(exception.getMessage());
        }
    }
}
