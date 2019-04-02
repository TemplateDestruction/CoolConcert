package com.breakout.myapplication.concerts.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreviousConcertsFragment extends Fragment {

    LayoutInflater inflater;
    View mainLayout;

    EmptyRecyclerView previousRecycler;
    PreviousAdapter adapter;

    List<String> concerts = new ArrayList<>();

    public static PreviousConcertsFragment getInstance() {
        PreviousConcertsFragment fragment = new PreviousConcertsFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inflater = inflater;
        mainLayout = inflater.inflate(R.layout.previous_concerts_layout, container, false);
        previousRecycler = mainLayout.findViewById(R.id.previous_recycler);
        concerts.add("27.02 - Гречка - Aurora CH");        concerts.add("01.03 - Артур Пирожков - A2");
        concerts.add("15.03 - Градусы - Космонавт");
        previousRecycler.setLayoutManager(new LinearLayoutManager(mainLayout.getContext()));
        adapter = new PreviousAdapter(new ArrayList<>());
        adapter.attachToRecyclerView(previousRecycler);
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        addConcerts();

//        return super.onCreateView(inflater, container, savedInstanceState);
        return mainLayout;
    }

    public void addConcerts() {
        adapter.changeDataSet(concerts);
    }

}
