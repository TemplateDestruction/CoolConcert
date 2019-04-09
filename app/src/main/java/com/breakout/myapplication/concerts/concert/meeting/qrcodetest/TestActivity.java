package com.breakout.myapplication.concerts.concert.meeting.qrcodetest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.breakout.myapplication.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class TestActivity extends AppCompatActivity {

    TextView tvCardText;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        startQRScanner();
//        btStartScan.setOnClickListener(view -> startQRScanner());

    }

    private void startQRScanner() {
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() == null) {
                    finish();
                } else {
                    intent = getIntent();
                    intent.putExtra("decoded", result.getContents());
                    setResult(500);
                    finish();
                }
            } else {
                finish();
            }
        }
}