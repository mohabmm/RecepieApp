package com.nocom.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext());
    }

    class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context context;
        private ArrayList<Ingrideint> ingrdient;
      //  private ArrayList<Steps> steps;

        public MyWidgetRemoteViewsFactory(Context context) {
            this.context = context;

            ingrdient = new ArrayList<>();
          //  steps = new ArrayList<>();


        }


        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = preferences.getString(DetailActivity.SHARED_PREFS_KEY, "");
            if (!json.equals("")) {
                Gson gson = new Gson();
                ingrdient = gson.fromJson(json, new TypeToken<ArrayList<Ingrideint>>() {
                }.getType());



                }
            }


        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (ingrdient != null) {
                return ingrdient.size();
            }
                 else return 0;

        }


        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.qw);
            rv.setTextViewText(R.id.a7a, ingrdient.get(position).getNname());
            rv.setTextViewText(R.id.a7a2, String.valueOf(ingrdient.get(position).getNquantity()));
            rv.setTextViewText(R.id.text1, ingrdient.get(position).getNmeasure());



            Bundle b = new Bundle();
            b.putParcelableArrayList("Movie",ingrdient);
          //  b.putParcelableArrayList("moha",steps);
            Intent startChildActivityIntent = new Intent(context, DetailActivity.class);
            startChildActivityIntent.putExtras(b);
            startChildActivityIntent.putExtra(Intent.EXTRA_TEXT,ingrdient);
          //  startChildActivityIntent.putExtra(Intent.EXTRA_REFERRER, steps);




            Bundle extras = new Bundle();
            extras.putInt(CollectionWidget.EXTRA_ITEM,position);
            Intent fillintenet = new Intent();
            fillintenet.putExtras(extras);

            rv.setOnClickFillInIntent(R.id.df,fillintenet);

            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}