package com.xiseven.diycode.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.xiseven.diycode.R;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/6.
 */
public class UserInfoActivity extends BaseActivity{
    @BindView(R.id.iv_head)
    ImageView iv_head;
    private Toolbar toolbar;

    @Override
    public int getContentViewId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        String userLogin = getIntent().getStringExtra("userLogin");
        toolbar = initToolbar(getIntent().getStringExtra("userName"));
    }
}
