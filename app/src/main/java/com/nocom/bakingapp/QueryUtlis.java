package com.nocom.bakingapp;

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
import java.nio.charset.Charset;
import java.util.ArrayList;


public final class QueryUtlis {

     static final String LOG_TAG = QueryUtlis.class.getSimpleName();

    private QueryUtlis() {
    }
    public static ArrayList<Recipe> featchrecipedata(String requestUrl) throws JSONException {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        ArrayList<Recipe> recipes = extractFeatureFromJson(jsonResponse);//,jsonResponse2);

        return recipes;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {

                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Recepie JSON results.", e);


        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    private static ArrayList<Recipe> extractFeatureFromJson(String recipejson) throws JSONException {
        if (TextUtils.isEmpty(recipejson)) {
            return null;
        }
        ArrayList<Recipe> list = new ArrayList<>(); //this arraylist contains the main 4 parst of my list
        try {
            JSONArray baseJsonResponse = new JSONArray(recipejson);
            int quantity = 0;
            String ingri = null;
            String measure = null;
            JSONObject mainobject = new JSONObject();
            for (int i = 0; i < baseJsonResponse.length(); i++) {
                mainobject = baseJsonResponse.getJSONObject(i);
                String name = mainobject.getString("name");
                int id = mainobject.getInt("id");
                String image = mainobject.getString("image");
                JSONArray ingriedients = mainobject.getJSONArray("ingredients");

                ArrayList<Ingrideint> ingr = new ArrayList<Ingrideint>();
                //  JSONArray jArray = (JSONArray)jsonObject;


                for (int j = 0; j < ingriedients.length(); j++) {
                    JSONObject firstrecipe = ingriedients.getJSONObject(j);
                    quantity = firstrecipe.getInt("quantity");// the quantity needed
                    measure = firstrecipe.getString("measure");// measure of the ingredients part
                    ingri = firstrecipe.getString("ingredient"); //ingredients types
                    Ingrideint ingrdient = new Ingrideint(quantity,measure,ingri);
                    ingr.add(ingrdient);
                    Log.i("quantity", String.valueOf(quantity));
                    Log.i("measure", measure);
                    Log.i("ingridients",ingri);

                   // Log.i("ingredieyfyqueryutilis", ingredient);
                }
                JSONArray steps = mainobject.getJSONArray("steps");
                ArrayList<Steps> asteps = new ArrayList<Steps>();
              //  JSONArray jArray = (JSONArray)jsonObject;
                for (int k = 0; k < steps.length(); k++) {
                    JSONObject firststep = steps.getJSONObject(k);
                    String shortDescription = firststep.getString("shortDescription"); // descripsion short from the steps
                    String descripsion = firststep.getString("description"); // full descripsion of the rcipe
                    String url = firststep.getString("videoURL");
                    int nid = firststep.getInt("id");

                    Log.i("short description", shortDescription);
                    Log.i(" description", descripsion);
                    Log.i(" url", url);
                    Steps step = new Steps(shortDescription,descripsion,url,nid);
                    asteps.add(step);
                }
                Recipe recipe = new Recipe(image,id,name,ingr, asteps);
                recipe.setNingriedients(ingr);
                recipe.setMeasure(measure);
                recipe.setName(name);
                recipe.setNsteps(asteps);



                list.add(recipe);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the Recepie JSON results", e);
        }

        return list;
    }

}