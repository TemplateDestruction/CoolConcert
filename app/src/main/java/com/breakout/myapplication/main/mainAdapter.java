package com.breakout.myapplication.main;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.breakout.myapplication.R;
import com.breakout.myapplication.widget.BaseAdapter;

import java.util.List;

public class mainAdapter extends BaseAdapter<mainHolder, Object> {
    public mainAdapter(@NonNull List<Object> items) {
        super(items);
    }
//    CoursesAdapter(@NonNull List<Result> courses) {
//        super(courses);
//    }

    @NonNull
    @Override
    public mainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new mainHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mainHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind();
    }
}
