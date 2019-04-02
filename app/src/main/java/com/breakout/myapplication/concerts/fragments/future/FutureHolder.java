package com.breakout.myapplication.concerts.fragments.future;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.breakout.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class FutureHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.future_text_concert)
    TextView textConcert;

    FutureHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(String concert) {
        textConcert.setText(concert);
    }
}
