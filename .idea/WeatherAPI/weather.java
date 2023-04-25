import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherMapApiCall {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        String cityName = "London";
        String urlString = BASE_URL + "?q=" + cityName + "&appid=" + API_KEY;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                connection.disconnect();

                System.out.println("Weather data: " + content.toString());
            } else {
                System.out.println("Error: API call unsuccessful. HTTP status code: " + status);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}