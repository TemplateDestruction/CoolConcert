package com.breakout.myapplication.concerts.fragments.current;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.breakout.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class CurrentHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.current_text_concert)
    TextView textConcert;

    CurrentHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(String concert) {
        textConcert.setText(concert);
    }
}
