package com.breakout.myapplication.concerts.news;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.BaseAdapter;

import java.util.List;

public class ConcertNewsAdapter extends BaseAdapter<ConcertNewsHolder, String> {
    public ConcertNewsAdapter(@NonNull List<String> items) {
        super(items);
    }
//    CoursesAdapter(@NonNull List<Result> courses) {
//        super(courses);
//    }

    @NonNull
    @Override
    public ConcertNewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ConcertNewsHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertNewsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String concert = getItem(position);
        holder.bind(concert);
    }
}
