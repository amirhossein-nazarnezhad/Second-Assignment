import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "b2ec747d11fe425aae0164234230103";
    // TODO: Write main function
    public static void main(String[] args) {

        System.out.println("please inter city:");

        Scanner input = new Scanner(System.in);


        String city = input.next();


        System.out.println(getTemperature(getWeatherData(city)));
        System.out.println(getHumidity(getWeatherData(city)));
        System.out.println(getWind_dir(getWeatherData(city)));
        System.out.println(getWind_kph(getWeatherData(city)));



    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */

    //=================================================================================
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //============================================================================

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson)
    {
        JSONObject jsonObject = new JSONObject(weatherJson);

        double answer = jsonObject.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    //=================================================================================

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson)
    {
        JSONObject jsonObject = new JSONObject(weatherJson);

        int answer =  jsonObject.getJSONObject("current").getInt( "humidity");
        return answer;

    }

    //================================================================================

    public static String getWind_dir(String weatherJson)
    {

        JSONObject jsonObject = new JSONObject(weatherJson);
        String dir = jsonObject.getJSONObject("current").getString( "wind_dir");
        return dir;
    }

    //==================================================================================

    public static double getWind_kph(String weatherJson)
    {
        JSONObject jsonObject = new JSONObject(weatherJson);
        double speed = jsonObject.getJSONObject("current").getDouble( "wind_kph");
        return speed;
    }


}
