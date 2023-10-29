import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class WeatherData {
    private String location;
    private double temp;
    @SerializedName("wind_speed")
    private double windSpeed;
    @SerializedName("wind_direction")
    private String windDirection;
    private String conditions;
    private int humidity;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public static WeatherData fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, WeatherData.class);
    }
}
