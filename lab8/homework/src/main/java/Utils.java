public class Utils {
    public static double calculateDistance(double latitude1, double latitude2, double longitude1, double longitude2) {
        longitude1 = Math.toRadians(longitude1);
        longitude2 = Math.toRadians(longitude2);
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);

        double doubleLongitude = longitude2 - longitude1;
        double doubleLatitude = latitude2 - latitude1;
        double answer = Math.pow(Math.sin(doubleLatitude / 2), 2) + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.pow(Math.sin(doubleLongitude / 2), 2);

        answer = 2 * Math.asin(Math.sqrt(answer));

        double value = 6371;

        return (answer * value);
    }

//    public static double getCitiesDistance(String cityName1, String cityName2){
//        City cityDAO = new City();
//
//        if(cityDAO.findByName(cityName1) == null || cityDAO.findByName(cityName2) == null)
//            return -1.0;
//
//        return calculateDistance(cityDAO.getLatitude(cityName1), cityDAO.getLatitude(cityName2), cityDAO.getLongitude(cityName1), cityDAO.getLongitude(cityName2));
//    }
}
