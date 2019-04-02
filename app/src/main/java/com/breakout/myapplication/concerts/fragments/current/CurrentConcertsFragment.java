package com.breakout.myapplication.concerts.fragments.current;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.breakout.myapplication.R;
import com.breakout.myapplication.concerts.concert.ConcertActivity;
import com.breakout.myapplication.concerts.fragments.PreviousAdapter;
import com.breakout.myapplication.concerts.fragments.PreviousHolder;
import com.breakout.myapplication.widget.BaseAdapter;
import com.breakout.myapplication.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentConcertsFragment extends Fragment {

    LayoutInflater inflater;
    View mainLayout;

    EmptyRecyclerView currentRecycler;
    CurrentAdapter adapter;

    List<String> concerts = new ArrayList<>();

    TextView textView;

    public static CurrentConcertsFragment getInstance() {
        CurrentConcertsFragment fragment = new CurrentConcertsFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inflater = inflater;
        mainLayout = inflater.inflate(R.layout.current_concert_layout, container, false);
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textView = mainLayout.findViewById(R.id.text_current_concert);
//        currentRecycler = mainLayout.findViewById(R.id.current_recycler);
//        concerts.add("23.03 - БИ-2 - СК Юбилейный");
//        currentRecycler.setLayoutManager(new LinearLayoutManager(mainLayout.getContext()));
//        adapter = new CurrentAdapter(new ArrayList<>());
//        adapter.attachToRecyclerView(currentRecycler);
//        addConcerts();
        textView.setText("23.03 - БИ-2 - СК Юбилейный");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConcertActivity.class);
//        intent.putExtra("concert", );
        startActivity(intent);
            }
        });


//        return super.onCreateView(inflater, container, savedInstanceState);
        return mainLayout;
    }

    public void addConcerts() {
        adapter.changeDataSet(concerts);
    }

//    @Override
//    public void onItemClick(@NonNull String item, View view) {
//        //item.getId - id концерта
//        // по нему отправляемся дальше
//        Toast.makeText(view.getContext(), "Clicking", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(view.getContext(), ConcertActivity.class);
//        intent.putExtra("concert", item);
//        startActivity(intent);
//
//    }
}
