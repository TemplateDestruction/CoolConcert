package com.breakout.myapplication.concerts.fragments.current;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.BaseAdapter;

import java.util.List;

public class CurrentAdapter extends BaseAdapter<CurrentHolder, String> {
    public CurrentAdapter(@NonNull List<String> items) {
        super(items);
    }
//    CoursesAdapter(@NonNull List<Result> courses) {
//        super(courses);
//    }

    @NonNull
    @Override
    public CurrentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CurrentHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.current_event_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String concert = getItem(position);
        holder.bind(concert);
    }
}
