 package com.onrpiv.uploadmedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

 public class Experiment1 extends YouTubeBaseActivity {
    YouTubePlayerView mYoutubePlayerView;
//    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment1);
//        btnPlay = (Button)findViewById(R.id.playButton);
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("9u9AIn0DliI");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnInitializedListener);
//            }
//        });
    }
}