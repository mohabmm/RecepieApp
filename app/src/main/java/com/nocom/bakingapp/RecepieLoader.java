package com.nocom.bakingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Looper;

import org.json.JSONException;

import java.util.ArrayList;

public  class RecepieLoader extends AsyncTaskLoader<ArrayList<Recipe>> {
    private static final String LOG_TAG = RecepieLoader.class.getName();
    ArrayList<Recipe> mNewsData;
    private   String murl;

    public RecepieLoader(Context context, String url) {
        super(context);
        murl = url;
    }
    @Override
    protected void onStartLoading() {

        if (mNewsData != null) {
            deliverResult(mNewsData);
        } else {
            forceLoad();

        }
    }
    @Override
    public ArrayList<Recipe> loadInBackground() {

        if (Looper.myLooper()==null)
            Looper.prepare();
        // Perform the network request, parse the response, and extract a list of movies.
        ArrayList<Recipe> movies = null;
        try {
            movies = QueryUtlis.featchrecipedata(murl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
