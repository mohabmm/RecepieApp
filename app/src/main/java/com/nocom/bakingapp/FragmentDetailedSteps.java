package com.nocom.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Moha on 11/18/2017.
 */

public class FragmentDetailedSteps extends Fragment implements ExoPlayer.EventListener {


    int postion;
    TextView descText;
    TextView shortDesc;
    SimpleExoPlayer mExoPlayer;
    SimpleExoPlayerView mPlayerView;
    Button previouse;
    Button next;
    Boolean mTablet;


    ArrayList<? extends Steps> listitems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_steps, container, false);


        descText = (TextView) view.findViewById(R.id.desc);

        shortDesc = (TextView) view.findViewById(R.id.shortdesc);
        mPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.playerView);
        String value = getArguments().getString("YourKey");
        String yy = getArguments().getString("mohasa");
        previouse = (Button)view.findViewById(R.id.previouse);
        next = (Button)view.findViewById(R.id.bunexttton);
         postion = getArguments().getInt("mmm");
        Toast.makeText(getContext(),String.valueOf(postion),Toast.LENGTH_SHORT).show();

        listitems = getArguments().getParcelableArrayList("stepsarraylist");

       // Toast.makeText(getContext(),String.valueOf(listitems.size()),Toast.LENGTH_SHORT).show();



        descText.setText(value);

//        shortDesc.setText(yy);

        if (getArguments().get("url") != null) {

           String url = getArguments().getString("url");

            mplayer(url);

            mPlayerView.setVisibility(View.VISIBLE);
        }
        else {

            mPlayerView.setVisibility(View.INVISIBLE);

        }






        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(postion < (listitems.size()-1)) {


                    postion += 1;
                    String desc = listitems.get(postion).getNdescripsion();
                    String url = listitems.get(postion).getNurl();
                    //String shortd = listitems.get(postion).getNshortDescription();
                    if (url != null) {

                        mPlayerView.setVisibility(View.VISIBLE);
                        mplayer(url);
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

                if(postion > 0) {
                    postion -= 1;
                    String desc = listitems.get(postion).getNdescripsion();
                    String url = listitems.get(postion).getNurl();
                  //  String shortd = listitems.get(postion).getNshortDescription();
                    if (url != null) {

                        mPlayerView.setVisibility(View.VISIBLE);

                        mplayer(url);
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





    public void mplayer(String url){


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
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();

    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        //mExoPlayer = null;
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

