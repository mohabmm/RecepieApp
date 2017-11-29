
package com.nocom.bakingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private  Boolean mTabletMode = false;


    Bundle bundle;
    public static final String SHARED_PREFS_KEY = "SHARED_PREFS_KEY";

    List<Ingrideint> listItems;
    ArrayList<? extends Steps> listItems2;

    Recipe currentrecipie;

    //private FragmentAdapter fragmentAdapter;
   // private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity);









        Log.d(TAG, "onCreate: Started.");


        final Intent intentThatStartedThisActivity = getIntent();
        final Bundle b = this.getIntent().getExtras();
        if (b != null && intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)&&intentThatStartedThisActivity.hasExtra(Intent.EXTRA_REFERRER)) {
            listItems = new ArrayList<>();
            currentrecipie = b.getParcelable("Movie");
            listItems= b.getParcelableArrayList("Movie");
           // listItems = currentrecipie.getNingriedients();
            listItems2=b.getParcelableArrayList("moha");
          //  Log.i("listitems", listItems.toString());
             bundle = new Bundle();
            bundle.putParcelableArrayList("edttext", (ArrayList<? extends Parcelable>) listItems);
            bundle.putParcelableArrayList("moha",  listItems2);

          //  Bundle widget = new Bundle();
            //bundle.putParcelableArrayList("widget", (ArrayList<? extends Parcelable>) listItems);



            String json = new Gson().toJson(listItems );


            // PreferenceManager.getDefaultSharedPreferences(this).edit().putString("mystr", json).apply();


            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(SHARED_PREFS_KEY, json).commit();


          //  editor.putString(SHARED_PREFS_KEY, json).apply();



            if(findViewById(R.id.container2)!= null){
                mTabletMode = true;
                // note this is the problem always is exceuted even in phone mode
                Toast.makeText(getApplicationContext(),"in tablet",Toast.LENGTH_SHORT).show();
                FragmentIngridientAndSteps detailFragment = new FragmentIngridientAndSteps();
                detailFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
            }

            else {


                mTabletMode=false;
                FragmentIngridientAndSteps detailFragment = new FragmentIngridientAndSteps();
                detailFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
            }

/*            final Intent intentThatStartedsThisActivity = getIntent();
            final Bundle c = this.getIntent().getExtras();
            if (c != null && intentThatStartedsThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                listItems2 = new ArrayList<>();
                currentrecipie = b.getParcelable("Movie");
                listItems2 = b.getParcelableArrayList("Movie");
                Log.i("listitems", listItems2.toString());
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("moha", (ArrayList<? extends Parcelable>) listItems2);


            }


*/


        }


    }

    public  boolean isTablet() {
        return mTabletMode;
    }





    public void replaceFragment() {
        final FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        FragmentDetailedSteps newFragment = new FragmentDetailedSteps();
        newFragment.setArguments(bundle);
        t.add(R.id.container2, newFragment);
        t.addToBackStack(null);
        t.commit();

    }

/*
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate("Frag1", 0);
        } else {
            super.onBackPressed();
        }
    }
    */
}
