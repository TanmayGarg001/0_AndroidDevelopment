package com.example.earthquake;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private QueryUtils() {
    }

    private static URL createURL(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException malformedURLException) {
            Log.e(QueryUtils.class.getName(), "Problem building the URL", malformedURLException);
        }
        return url;
    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(QueryUtils.class.getName(), "Error response code");
            }
        } catch (IOException ioException) {
            Log.e(QueryUtils.class.getName(), "Problem in retrieving the earthquake JSON results.", ioException);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return builder.toString();
    }

    private static List<EarthQuake> extractFeatureFromJson(String earthquakeJSON) {
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }
        List<EarthQuake> earthQuakes = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");
            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                JSONObject properties = currentEarthquake.getJSONObject("properties");
                double magnitude = properties.getDouble("mag");
                String location = properties.getString("place");
                long time = properties.getLong("time");
                String url = properties.getString("url");
                EarthQuake earthquake = new EarthQuake(magnitude, location, time, url);
                earthQuakes.add(earthquake);
            }
        } catch (JSONException jsonException) {
            Log.e(QueryUtils.class.getName(), "Problem parsing the earthquake JSON results ", jsonException);
        }
        return earthQuakes;
    }

    public static List<EarthQuake> fetchEarthquakeData(String requestUrl) {
        URL url = createURL(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHTTPRequest(url);
        } catch (IOException e) {
            Log.e("QueryUtils", "Problem making the HTTP request.", e);
        }
        List<EarthQuake> earthquakes = extractFeatureFromJson(jsonResponse);
        return earthquakes;
    }
}
