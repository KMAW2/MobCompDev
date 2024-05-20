package ru.mirea.ivanovea.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import ru.mirea.ivanovea.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkinfo = null;
                if (connectivityManager != null) {
                    networkinfo = connectivityManager.getActiveNetworkInfo();
                }

                if (networkinfo != null && networkinfo.isConnected()) {
                    new DownloadPageTask().execute("https://ipinfo.io/json");
                } else {
                    Toast.makeText(MainActivity.this, "Нет интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textView.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(MainActivity.class.getSimpleName(), result);

            try {
                JSONObject responseJson = new JSONObject(result);
                String city = responseJson.optString("city");
                String region = responseJson.optString("region");
                String ip = responseJson.optString("ip");
                String country = responseJson.optString("country");
                String timezone = responseJson.optString("timezone");
                String latitude = responseJson.optString("loc").split(",")[0]; // Получение широты из "loc"
                String longitude = responseJson.optString("loc").split(",")[1]; // Получение долготы из "loc"

                // Обновление текстовых полей с данными о местоположении
                binding.citytextView.setText("Город: " + city);
                binding.regiontextView.setText("Регион: " + region);
                binding.iptextView.setText("IP: " + ip);
                binding.countrytextView.setText("Страна: " + country);
                binding.timezonetextView.setText("Часовой пояс: " + timezone);

                // Формирование URL для запроса к сервису погоды
                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true";
                new DownloadWeatherTask().execute(weatherUrl);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadWeatherInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(MainActivity.class.getSimpleName(), result);

            try {
                JSONObject weatherJson = new JSONObject(result);
                Log.d(MainActivity.class.getSimpleName(), "weatherJson: " + weatherJson);

                String temperature = weatherJson.getJSONObject("current_weather").getString("temperature");
                String wind = weatherJson.getJSONObject("current_weather").getString("windspeed");
                String weather = weatherJson.getJSONObject("current_weather").getString("weathercode");

                binding.temperaruretextView.setText("Температура: " + temperature);
                binding.windtextView.setText("Ветер (скорость): " + wind);
                binding.weathertextView.setText("Погода (код): " + weather);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                byte[] buffer = new byte[1024];
                while ((read = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage() + ". Error Code: " + responseCode;
            }
            connection.disconnect();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private String downloadWeatherInfo(String weatherUrl) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(weatherUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                byte[] buffer = new byte[1024];
                while ((read = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, read);
                }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage() + ". Error Code: " + responseCode;
            }
            connection.disconnect();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}
