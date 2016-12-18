package com.xiseven.diycode.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.app.DiyCodeApp;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/1.
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("关于");
        tvVersion.setText("DiyCode " + DiyCodeApp.getVersionName());
    }
}
