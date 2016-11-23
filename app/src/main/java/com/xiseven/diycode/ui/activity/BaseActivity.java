package com.xiseven.diycode.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.anzewei.parallaxbacklayout.ParallaxActivityBase;
import com.github.anzewei.parallaxbacklayout.ParallaxBackActivityHelper;
import com.github.anzewei.parallaxbacklayout.ParallaxBackLayout;
import com.xiseven.diycode.R;

import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/11/20.
 */

public abstract class BaseActivity extends ParallaxActivityBase implements View.OnClickListener {
    private Toolbar toolbar;

    /**
     * 子类重写该方法，实现设置布局
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 子类重写该方法，初始化view
     * @param savedInstanceState
     */
    protected abstract void initAllMembersView(Bundle savedInstanceState);

    public Activity mActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        mActivity = this;
        ButterKnife.bind(mActivity);
        initAllMembersView(savedInstanceState);



    }

    /**
     * 初始化Toolbar
     * @param title toolbar标题
     * @return
     */
    public Toolbar initToolbar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //toolbar点击返回
        toolbar.setNavigationOnClickListener(this);
        return toolbar;
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
