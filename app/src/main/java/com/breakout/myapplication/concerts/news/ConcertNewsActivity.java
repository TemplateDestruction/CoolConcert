package com.breakout.myapplication.concerts.news;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  ConcertNewsActivity extends AppCompatActivity {

    @BindView(R.id.concert_news_recycler)
    EmptyRecyclerView newsRecycler;

    ConcertNewsAdapter adapter;

    List<String> concertsNews = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concert_news_layout);
        ButterKnife.bind(this);
        concertsNews.add("22.02 12:00 Начат опрос: 'Желаемые песни' ");
        concertsNews.add("22.03 12:00 Закончен опрос: 'Желаемые песни' ");

        newsRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConcertNewsAdapter(new ArrayList<>());
        adapter.attachToRecyclerView(newsRecycler);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        addNews();

    }

    public void addNews() {
        adapter.changeDataSet(concertsNews);
    }
}
