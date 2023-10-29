import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class OpenMeteo {
    public static void main(String[] args) {
        try {
            String city = "Almaty";
            String apiUrl = "https://api.open-meteo.com/v1/forecast?forecast=7&daily=temperature_2m_min,temperature_2m_max,precipitation_sum&timezone=Europe%2FZurich&current_weather=true&city=" + city;

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

            JSONObject currentWeather = json.getJSONObject("current_weather");
            double temperature = currentWeather.getDouble("temperature_2m");
            double precipitation = currentWeather.getDouble("precipitation_sum");

            JSONArray dailyForecasts = json.getJSONArray("daily_forecast");
            for (int i = 0; i < dailyForecasts.length(); i++) {
                JSONObject dayForecast = dailyForecasts.getJSONObject(i);
                double minTemperature = dayForecast.getDouble("temperature_2m_min");
                double maxTemperature = dayForecast.getDouble("temperature_2m_max");
                double dailyPrecipitation = dayForecast.getDouble("precipitation_sum");

                System.out.println("Day " + (i + 1));
                System.out.println("Min Temperature: " + minTemperature);
                System.out.println("Max Temperature: " + maxTemperature);
                System.out.println("Daily Precipitation: " + dailyPrecipitation);
                System.out.println();
            }

            System.out.println("Current Temperature: " + temperature);
            System.out.println("Current Precipitation: " + precipitation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
