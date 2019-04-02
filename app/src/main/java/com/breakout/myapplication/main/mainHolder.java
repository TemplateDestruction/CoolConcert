package com.breakout.myapplication.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

class mainHolder extends RecyclerView.ViewHolder {



    mainHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind() {

    }
}
