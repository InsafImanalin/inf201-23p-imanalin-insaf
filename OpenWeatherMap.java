import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class OpenWeatherMap {
    public static void main(String[] args) {
        try {
            String apiKey = "bfbb74cf6980cafc18c9e12449e82400";
            String city = "Almaty";

            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());

            String location = json.getString("name");
            double temp = json.getJSONObject("main").getDouble("temp");
            double windSpeed = json.getJSONObject("wind").getDouble("speed");
            String windDirection = json.getJSONObject("wind").has("deg") ? json.getJSONObject("wind").getDouble("deg") + " degrees" : "N/A";
            String conditions = json.getJSONArray("weather").getJSONObject(0).getString("description");
            int humidity = json.getJSONObject("main").getInt("humidity");

            standard_data_format weatherData = new standard_data_format();
            weatherData.setLocation(location);
            weatherData.setTemp(temp);
            weatherData.setWindSpeed(windSpeed);
            weatherData.setWindDirection(windDirection);
            weatherData.setConditions(conditions);
            weatherData.setHumidity(humidity);

            System.out.println("Location: " + weatherData.getLocation());
            System.out.println("Temperature: " + weatherData.getTemp() + " °C");
            System.out.println("Wind Speed: " + weatherData.getWindSpeed() + " m/s");
            System.out.println("Wind Direction: " + weatherData.getWindDirection());
            System.out.println("Conditions: " + weatherData.getConditions());
            System.out.println("Humidity: " + weatherData.getHumidity() + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
