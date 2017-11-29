package com.nocom.bakingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainAdapter.ListItemClickListener{
    private static final int MOVIE_LOADER_ID = 2;
    final String BakingWebsite = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private final String LOG_TAG = RecepieLoader.class.getName();
    //  ArrayList<Recipe> mNewsData;
    public RecyclerView mrecyclerview;
    Bundle bundle;
    // the problem i think is here
    ArrayList<Recipe> recepieList = new ArrayList<>();
    MainAdapter mMainAdapter;
    LoaderManager loaderManager;
    TextView emptytext;
    ProgressBar progressBar;
    public LoaderManager.LoaderCallbacks<ArrayList<Recipe>> loaderone = new LoaderManager.LoaderCallbacks<ArrayList<Recipe>>() {
        @Override
        public Loader<ArrayList<Recipe>> onCreateLoader(int id, Bundle args) {
            // Create a new loader for the given URL

            return new RecepieLoader(MainActivity.this, BakingWebsite);
        }

        @Override
        public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> data) {
            progressBar.setVisibility(View.INVISIBLE);
            if (data != null) {
                mMainAdapter.setWeatherData(data);
            }
            else {
                recepieList=data;

                Log.i("moha","e7na gwa al else ");

            }
        }

        @Override
        public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {

        }


    };
    private Toast mToast;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        emptytext = (TextView) findViewById(R.id.empty_view);
        mrecyclerview = (RecyclerView) findViewById(R.id.mrecycle);
        int numm =1;
        GridLayoutManager layoutManager
                = new GridLayoutManager(this, numm);
        // COMPLETED (41) Set the layoutManager on mRecyclerView
        mrecyclerview.setLayoutManager(layoutManager);
        mMainAdapter = new MainAdapter(this,recepieList,getBaseContext());
        mrecyclerview.setAdapter(mMainAdapter);
        loaderManager = getLoaderManager();
        loaderManager.initLoader(MOVIE_LOADER_ID, null, loaderone);
        connect();
    }
    public void connect() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.restartLoader(MOVIE_LOADER_ID, bundle, loaderone);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            emptytext.setText("No Internet Connection ");
            progressBar.setVisibility(View.INVISIBLE);

            // Update empty state with no connection error message
        }

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {


        if (mToast != null) {
            mToast.cancel();
        }


    }
}
