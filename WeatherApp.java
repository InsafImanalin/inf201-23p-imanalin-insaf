public class WeatherApp {
    public static void main(String[] args) {
        String json = "{ \"location\": \"CityName\", \"temp\": 22.5, \"wind_speed\": 5.8, \"wind_direction\": \"NW\", \"conditions\": \"Clear\", \"humidity\": 60 }";

        WeatherData weatherData = WeatherData.fromJson(json);

        System.out.println("Location: " + weatherData.getLocation());
        System.out.println("Temperature: " + weatherData.getTemp() + " °C");
        System.out.println("Wind Speed: " + weatherData.getWindSpeed() + " m/s");
        System.out.println("Wind Direction: " + weatherData.getWindDirection());
        System.out.println("Conditions: " + weatherData.getConditions());
        System.out.println("Humidity: " + weatherData.getHumidity() + "%");
    }
}