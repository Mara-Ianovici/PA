import java.sql.SQLException;

public class Tests {
    public void testCompulsory(){
        try {
            ContinentDAO continents = new ContinentDAO();
//            continents.create("Europe");
            Database.getConnection().commit();

            CountryDAO countries = new CountryDAO();
            int europeId = continents.findByName("Europe");

//            countries.create(1, "Romania", europeId);
//            countries.create(2, "Ukraine", europeId);

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
