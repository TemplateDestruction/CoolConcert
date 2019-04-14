package com.breakout.myapplication.user;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.breakout.myapplication.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DesignedUserActivity extends AppCompatActivity {

//    @BindView(R.id.user_bar)
//    Toolbar toolbar;

    @BindView(R.id.text_desc_user)
    TextView userDescript;

    @BindView(R.id.user_nick)
    TextView userNick;

    @BindView(R.id.profile_stat_text)
    TextView userStatusText;

    @BindView(R.id.edit_user_photo)
    ImageView userPhoto;

    @BindView(R.id.user_status_img)
    ImageView userStatusImg;

    @BindView(R.id.user_town)
    TextView userTown;

    @BindView(R.id.user_genre)
    TextView userGenre;

    int userStatus = 1;

    // TODO: 11.04.2019 shared preferences!

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_user_layout);
//        setContentView(R.layout.user_layout);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void onClickUser(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_edit:
                Intent intent = new Intent(this, DesignedEditingAct.class);
                String[] strings = new String[5];
                strings[0] = userNick.getText().toString();
                strings[1] = userTown.getText().toString();
                strings[2] = userGenre.getText().toString();
                strings[3] = userDescript.getText().toString();
                intent.putExtra("strings", strings);
                startActivityForResult(intent, 100);
                break;
            case R.id.user_status_img:
                switch (userStatus) {
                    case 0:
                        userStatusImg.setImageDrawable(getDrawable(R.drawable.active_search));
                        userStatusText.setText(getString(R.string.profile_active));
                        userStatus = 1;
                        break;
//                    case 1:
//                        userStatusImg.setImageDrawable(getDrawable(R.drawable.no_meet));
//                        userStatusText.setText("Не знакомится");
//                        userStatus = -1;
//                        break;
                    case 1:
                        userStatusImg.setImageDrawable(getDrawable(R.drawable.no_need));
                        userStatusText.setText(getString(R.string.profile_passive));
                        userStatus = 0;
                        break;
                }
        }
    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 500:
                String[] strings = Objects.requireNonNull(data.getExtras()).getStringArray("strings_new");
                userNick.setText(strings[0]);
                userTown.setText(strings[1]);
                userGenre.setText(strings[2]);
                userDescript.setText(strings[3]);
                Bitmap bitmap = data.getParcelableExtra("image");
                if (bitmap != null) {
                    userPhoto.setImageBitmap(bitmap);
                }
        }
    }
}
