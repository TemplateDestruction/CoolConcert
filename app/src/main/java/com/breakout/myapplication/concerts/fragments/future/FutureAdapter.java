package com.breakout.myapplication.concerts.fragments.future;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.BaseAdapter;

import java.util.List;

public class FutureAdapter extends BaseAdapter<FutureHolder, String> {
    public FutureAdapter(@NonNull List<String> items) {
        super(items);
    }
//    CoursesAdapter(@NonNull List<Result> courses) {
//        super(courses);
//    }

    @NonNull
    @Override
    public FutureHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FutureHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.future_event_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FutureHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String concert = getItem(position);
        holder.bind(concert);
    }
}
