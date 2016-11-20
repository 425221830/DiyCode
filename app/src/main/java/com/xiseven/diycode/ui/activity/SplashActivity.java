package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiseven.diycode.R;

public class SplashActivity extends AppCompatActivity {

    private static final int START_MAINACTIVITY = 0x0001;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == START_MAINACTIVITY) {
                startMainActivity();
            }
        }
    };

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.sendEmptyMessageDelayed(START_MAINACTIVITY, 2000);
    }
}
