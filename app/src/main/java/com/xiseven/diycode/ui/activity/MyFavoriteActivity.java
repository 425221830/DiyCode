package com.xiseven.diycode.ui.activity;

import android.os.Bundle;

import com.xiseven.diycode.R;

/**
 * Created by XISEVEN on 2016/12/1.
 */
public class MyFavoriteActivity extends BaseActivity{
    @Override
    public int getContentViewId() {
        return R.layout.activity_myfavorite;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("我的收藏");
    }
}
