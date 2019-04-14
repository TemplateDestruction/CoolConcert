package com.breakout.myapplication.concerts.concert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.breakout.myapplication.R;
import com.breakout.myapplication.concerts.concert.meeting.FastMeetingActivity;
import com.breakout.myapplication.concerts.concert.songs.SongActivity;
import com.breakout.myapplication.concerts.news.ConcertNewsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ConcertActivity extends AppCompatActivity {

//    @BindView(R.id.fast_meet_img)
//    ImageView fastMeeting;
//
//    @BindView(R.id.user_bar)
//    Toolbar userBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_concert_layout);
//        setContentView(R.layout.concert_layout);
//        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setSupportActionBar(userBar);
        ButterKnife.bind(this);
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String kat = "cat";
            }
        }, new IntentFilter("ACTION.VIEW"));



    }

    public void concertClick(View view) {
        switch (view.getId()) {
            case R.id.music_lay:
                startActivity(new Intent(this, SongActivity.class));
                break;
            case R.id.ask_lay:
                startActivity(new Intent(this, ConcertNewsActivity.class));
                break;
            case R.id.meet_lay:
                startActivity(new Intent(this, FastMeetingActivity.class));
                break;
            case R.id.imageViewBack:
                onBackPressed();
                break;
        }

    }


}
