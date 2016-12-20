package com.xiseven.diycode.ui.activity;

import android.os.Bundle;

import com.xiseven.diycode.R;

/**
 * Created by XISEVEN on 2016/12/1.
 */
public class MyFollowActivity extends BaseActivity{
    @Override
    public int getContentViewId() {
        return R.layout.activity_myfollow;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("我的关注");
    }
}
