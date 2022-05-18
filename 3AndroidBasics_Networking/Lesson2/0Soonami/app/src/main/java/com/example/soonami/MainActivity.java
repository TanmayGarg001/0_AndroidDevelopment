package com.example.soonami;
//https://classroom.udacity.com/courses/ud843/lessons/ca00b576-ec4d-4d0f-939e-6d28f55faf7b/concepts/5bd5cad7-ea1f-4dd3-892e-3404102a7931
//https://www.w3schools.com/tags/ref_httpmethods.asp

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    /**
     * URL to query the USGS dataset for earthquake information
     */
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kick off an {@link AsyncTask} to perform the network request
        TsunamiAsyncTask task = new TsunamiAsyncTask();
        task.execute();
    }

    /**
     * Update the screen to display information from the given {@link Event}.
     */
    private void updateUi(Event earthquake) {
        // Display the earthquake title in the UI
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(earthquake.title);

        // Display the earthquake date in the UI
        TextView dateTextView = (TextView) findViewById(R.id.date);
        dateTextView.setText(getDateString(earthquake.time));

        // Display whether or not there was a tsunami alert in the UI
        TextView tsunamiTextView = (TextView) findViewById(R.id.tsunami_alert);
        tsunamiTextView.setText(getTsunamiAlertString(earthquake.tsunamiAlert));
    }

    /**
     * Returns a formatted date and time string for when the earthquake happened.
     */
    private String getDateString(long timeInMilliseconds) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }

    /**
     * Return the display string for whether or not there was a tsunami alert for an earthquake.
     */
    private String getTsunamiAlertString(int tsunamiAlert) {
        switch (tsunamiAlert) {
            case 0:
                return getString(R.string.alert_no);
            case 1:
                return getString(R.string.alert_yes);
            default:
                return getString(R.string.alert_not_available);
        }
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    @SuppressLint("StaticFieldLeak")
    private class TsunamiAsyncTask extends AsyncTask<URL, Void, Event> {

        @Override
        protected Event doInBackground(URL[] urls) {
            // Create URL object
            URL url = createUrl();

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {//https://www.youtube.com/watch?v=qeKSharClIo
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e("MainActivity", "IOException: " + e);
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return extractFeatureFromJson(jsonResponse);
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link TsunamiAsyncTask}).
         */
        @Override
        protected void onPostExecute(Event earthquake) {
            if (earthquake == null) {
                return;
            }

            updateUi(earthquake);
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl() {
            URL url;
            try {
                url = new URL(MainActivity.USGS_REQUEST_URL);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         * After here we are receiving the response from the server and making sense of it for our app
         * This is tracked by using HTTP status codes:
         * 1xx = Informational Responses.
         * 2xx = Successful Responses.
         * 3xx = Redirection Errors.
         * 4xx = Client Errors.
         * 5xx = Server Errors.
         */

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //https://classroom.udacity.com/courses/ud843/lessons/ca00b576-ec4d-4d0f-939e-6d28f55faf7b/concepts/0e0a5506-d569-42dc-b831-889122e10445
                //https://classroom.udacity.com/courses/ud843/lessons/ca00b576-ec4d-4d0f-939e-6d28f55faf7b/concepts/02289623-cbf2-4700-901b-6cd71718d710
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                /*till here we were setting up the http request*/
                urlConnection.connect();//establishes http connection with the server
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }
            } catch (IOException e) {
                if (urlConnection.getResponseCode() != 200) {
                    Log.e("MainActivity", "HTTP response code: " + urlConnection.getResponseCode());
                }
                Log.e("MainActivity", "IOException: " + e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);//Characters: UTF-8 Unicode character encoding.
                BufferedReader reader = new BufferedReader(inputStreamReader);//Read and saves larger chunk of data around the Input stream Reader.
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an {@link Event} object by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private Event extractFeatureFromJson(String earthquakeJSON) {
            if (TextUtils.isEmpty(earthquakeJSON)) {//If the JSON string is empty or null,then return null.
                return null;
            }

            try {
                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
                JSONArray featureArray = baseJsonResponse.getJSONArray("features");

                // If there are results in the features array
                if (featureArray.length() > 0) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstFeature = featureArray.getJSONObject(0);
                    JSONObject properties = firstFeature.getJSONObject("properties");

                    // Extract out the title, time, and tsunami values
                    String title = properties.getString("title");
                    long time = properties.getLong("time");
                    int tsunamiAlert = properties.getInt("tsunami");

                    // Create a new {@link Event} object
                    return new Event(title, time, tsunamiAlert);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }
    }
}