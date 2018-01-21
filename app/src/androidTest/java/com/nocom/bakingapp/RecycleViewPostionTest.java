package com.nocom.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Moha on 11/30/2017.
 */


@RunWith(AndroidJUnit4.class)
public class RecycleViewPostionTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test

   public  void  gotoSpeceficPostion(){



       onView(withId(R.id.mrecycle))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));


    }
}
