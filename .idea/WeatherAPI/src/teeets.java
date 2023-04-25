import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class teeets {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";

    public static void main(String[] args) {
        double latitude = 51.5074;
        double longitude = -0.1278;
        String parameters = "hourly=temperature_2m,apparent_temperature";
        String urlString = BASE_URL + "?latitude=" + latitude + "&longitude=" + longitude + "&" + parameters;

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

                JSONObject jsonObject = new JSONObject(content.toString());

                JSONArray hourly = jsonObject.getJSONArray("temperature_2m");

                System.out.println("GET THIS SHIT" + " " + hourly);

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