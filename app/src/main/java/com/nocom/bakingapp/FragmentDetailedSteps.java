package com.nocom.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.squareup.picasso.Picasso.with;

/**
 * Created by Moha on 11/18/2017.
 */

public class FragmentDetailedSteps extends Fragment implements ExoPlayer.EventListener {


    String url;
    ImageView imageView;
    int postion;
    TextView descText;
    TextView shortDesc;
    SimpleExoPlayer mExoPlayer;
    SimpleExoPlayerView mPlayerView;
    Button previouse;
    Button next;
    Boolean mTablet;
    private long playbackpostion;
    private int currentwindow;
    private boolean playwhenready ;




//   long playerPosition = mExoPlayer.getCurrentPosition();



    ArrayList<? extends Steps> listitems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_steps, container, false);









     descText = (TextView) view.findViewById(R.id.desc);

       // shortDesc = (TextView) view.findViewById(R.id.shortdesc);
        mPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.playerView);
        String value = getArguments().getString("YourKey");
        String yy = getArguments().getString("mohasa");
        previouse = (Button)view.findViewById(R.id.previouse);
        next = (Button)view.findViewById(R.id.bunexttton);
         postion = getArguments().getInt("mmm");
        Toast.makeText(getContext(),String.valueOf(postion),Toast.LENGTH_SHORT).show();

        listitems = getArguments().getParcelableArrayList("stepsarraylist");


        imageView = (ImageView)view.findViewById(R.id.image);



        descText.setText(value);

//        shortDesc.setText(yy);

        if (getArguments().get("url") != null) {

           String url = getArguments().getString("url");

            insialisemyplayer(url);

            mPlayerView.setVisibility(View.VISIBLE);
        }
        else if (getArguments().get("imageurl")!=null){


            imageView.setVisibility(View.VISIBLE);
            with(this.getContext())
                    .load(getArguments().getString("imageurl"))
                    .placeholder(R.drawable.nutella)
                    .resize(6000, 2000)
                    .onlyScaleDown()
                    .into(this.imageView);






        }

        else{

            // load here place holder image using picasso


            imageView.setVisibility(View.VISIBLE);
            Picasso
                    .with(this.getContext())
                    .load(R.drawable.brownies)
                    .resize(6000, 2000)
                    .onlyScaleDown()
                    .into(this.imageView);





        }









        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                releasePlayer();

                if(postion < (listitems.size()-1)) {


                    postion += 1;
                    String desc = listitems.get(postion).getNdescripsion();
                    String url = listitems.get(postion).getNurl();
                    //String shortd = listitems.get(postion).getNshortDescription();
                    if (url != null) {

                        mPlayerView.setVisibility(View.VISIBLE);
                        insialisemyplayer(url);
                    } else {

                        mPlayerView.setVisibility(View.INVISIBLE);
                    }


                    if (desc != null) {

                        descText.setText(desc);

                    }

                    // if (shortd != null) {
                    //   shortDesc.setText(shortd);
                    //  }


                    // }
                }
            }
        });


        previouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                releasePlayer();

                if(postion > 0) {
                    postion -= 1;
                    String desc = listitems.get(postion).getNdescripsion();
                     url = listitems.get(postion).getNurl();
                  //  String shortd = listitems.get(postion).getNshortDescription();
                    if (url != null) {

                        mPlayerView.setVisibility(View.VISIBLE);

                        insialisemyplayer(url);
                    }
                    else {

                        mPlayerView.setVisibility(View.INVISIBLE);

                    }


                    if (desc != null) {

                        descText.setText(desc);

                    }

                   // if (shortd != null) {
                      //  shortDesc.setText(shortd);
                    //}
                }

                else {

                    Toast.makeText(getContext(),"this the first step",Toast.LENGTH_SHORT).show();
                }

                // follow on estredat post


            }
        });



        // getActivity().onBackPressed();


        return view;
    }




@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Saved all necesary values

        outState.putLong("playerpostion", playbackpostion);
        outState.putInt("currentwindow", currentwindow);
        outState.putBoolean("playwhenready",playwhenready);
        // More code
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){

            Toast.makeText(getContext(), "a7aaaainstates", Toast.LENGTH_SHORT).show();

            playbackpostion= savedInstanceState.getLong("playerpostion");
            currentwindow=savedInstanceState.getInt("currentwindow");
            playwhenready=savedInstanceState.getBoolean("playwhenready");


            mExoPlayer.setPlayWhenReady(playwhenready);
            mExoPlayer.seekTo(currentwindow, playbackpostion);

        }

    }





    public void insialisemyplayer (String url){


        BandwidthMeter bandwidthMeter = new BandwidthMeter() {
            @Override
            public long getBitrateEstimate() {
                return 0;
            }
        };
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector);


            //  initializePlayer(Uri.parse(url));
            //  Toast.makeText(getContext(),url , Toast.LENGTH_SHORT).show();

            Uri uri = Uri.parse(url);


            DefaultHttpDataSourceFactory datasourcefactory = new DefaultHttpDataSourceFactory("moha");
            ExtractorsFactory extractor = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(uri,datasourcefactory,extractor,null,null);
            mPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);





    }




    @Override
    public void onResume() {
        super.onResume();
      startPlayer();


    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();

    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();


    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();

    }




    private void startPlayer(){
       // mExoPlayer.setPlayWhenReady(true);

     mExoPlayer.setPlayWhenReady(playwhenready);
        mExoPlayer.seekTo(currentwindow, playbackpostion);
        mExoPlayer.getPlaybackState();
    }



    private void releasePlayer() {
        if (mExoPlayer != null) {
            playbackpostion = mExoPlayer.getCurrentPosition();
            currentwindow = mExoPlayer.getCurrentWindowIndex();
            playwhenready = mExoPlayer.getPlayWhenReady();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            Log.d(TAG, "onPlayerStateChanged: PLAYING");
        } else if ((playbackState == ExoPlayer.STATE_READY)) {

            Log.d(TAG, "onPlayerStateChanged: PAUSED");
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

        Toast.makeText(getContext(),"a7a",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositionDiscontinuity() {


    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }





}

