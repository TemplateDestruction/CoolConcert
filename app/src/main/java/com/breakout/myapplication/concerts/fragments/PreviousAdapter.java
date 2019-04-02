package com.breakout.myapplication.concerts.fragments;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.BaseAdapter;

import java.util.List;

public class PreviousAdapter extends BaseAdapter<PreviousHolder, String> {
    public PreviousAdapter(@NonNull List<String> items) {
        super(items);
    }
//    CoursesAdapter(@NonNull List<Result> courses) {
//        super(courses);
//    }

    @NonNull
    @Override
    public PreviousHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PreviousHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String concert = getItem(position);
        holder.bind(concert);
    }
}
