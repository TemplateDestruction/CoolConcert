package com.breakout.myapplication.concerts.fragments.future;

import android.content.Context;
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

public class FutureConcertsFragment extends Fragment {

    LayoutInflater inflater;
    View mainLayout;

    EmptyRecyclerView futureRecycler;
    FutureAdapter adapter;

    List<String> concerts = new ArrayList<>();

    Context context;

    public static FutureConcertsFragment getInstance() {
        FutureConcertsFragment fragment = new FutureConcertsFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inflater = inflater;
        mainLayout = inflater.inflate(R.layout.future_concerts_layout, container, false);
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        futureRecycler = mainLayout.findViewById(R.id.future_recycler);
        concerts.add("20.04 - Нейромонах Феофан - СК Олимпийский");
        concerts.add("30.03 - ATL - Backstage");
        concerts.add("17.05 - Gone.Fludd - A2");
        futureRecycler.setLayoutManager(new LinearLayoutManager(mainLayout.getContext()));
        adapter = new FutureAdapter(new ArrayList<>());
        adapter.attachToRecyclerView(futureRecycler);
        addConcerts();

//        return super.onCreateView(inflater, container, savedInstanceState);
        return mainLayout;
    }


    public void addConcerts() {
        adapter.changeDataSet(concerts);
    }

}
