package com.breakout.myapplication.concerts.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.breakout.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviousHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.text_concert)
    TextView textConcert;

    PreviousHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(String concert) {
        textConcert.setText(concert);
    }
}
