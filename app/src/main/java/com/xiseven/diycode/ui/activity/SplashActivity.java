package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.xiseven.diycode.R;

public class SplashActivity extends BaseActivity {

    private static final int START_MAINACTIVITY = 0x0001;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == START_MAINACTIVITY) {
                startMainActivity();
            }
        }
    };

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(START_MAINACTIVITY, 1000);
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

}
