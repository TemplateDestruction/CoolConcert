package com.breakout.myapplication.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.breakout.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.user_bar)
    Toolbar toolbar;

    @BindView(R.id.text_desc_user)
    TextView userDescript;

    @BindView(R.id.user_nick)
    TextView userNick;

    @BindView(R.id.user_photo)
    ImageView userPhoto;

    @BindView(R.id.user_status_img)
    ImageView userStatusImg;

    int userStatus = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_edit:
                Intent intent = new Intent(this, EditingAct.class);
                String[] strings = new String[2];
                strings[0] = userDescript.getText().toString();
                strings[1] = userNick.getText().toString();
                intent.putExtra("strings", strings);
                startActivityForResult(intent, 100);
                break;
            case R.id.user_status_img:
                switch (userStatus) {
                    case 0:
                        userStatusImg.setImageDrawable(getDrawable(R.drawable.active_search));
                        userStatus = 1;
                        break;
                    case 1:
                        userStatusImg.setImageDrawable(getDrawable(R.drawable.no_meet));
                        userStatus = -1;
                        break;
                    case -1:
                        userStatusImg.setImageDrawable(getDrawable(R.drawable.no_need));
                        userStatus = 0;
                        break;
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 500:
                String[] strings = data.getExtras().getStringArray("strings_new");
                userDescript.setText(strings[0]);
                userNick.setText(strings[1]);
                Bitmap bitmap = data.getParcelableExtra("image");
                if (bitmap != null) {
                    userPhoto.setImageBitmap(bitmap);
                }
        }
    }
}
