package com.xiseven.diycode.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.bean.User;
import com.xiseven.diycode.ui.iView.IInfoView;
import com.xiseven.diycode.ui.presenter.BasePresenter;
import com.xiseven.diycode.ui.presenter.MyInfoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的详情页面
 * Created by XISEVEN on 2016/11/24.
 */
public class MyInfoActivity extends BaseActivity implements IInfoView {
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_bio)
    TextView tv_bio;

    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_github)
    TextView tv_github;
    @BindView(R.id.tv_level)
    TextView tv_level;



    @BindView(R.id.sign_out_button)
    Button btn_sign_out;
    MyInfoPresenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_myinfo;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        mPresenter = new MyInfoPresenter(this);
        mPresenter.init();
    }

    @OnClick(R.id.sign_out_button)
    void signOut() {
        mPresenter.deleteDevices();
        finish();
    }

    @Override
    public void showHead(Bitmap bitmap) {
        iv_head.setImageBitmap(bitmap);
    }

    @Override
    public void showUsername(String username) {
        tv_username.setText(username);
    }

    @Override
    public void showBio(String bio) {
        tv_bio.setText(bio);
    }

    @Override
    public void showEmail(String email) {
        tv_email.setText(email);
    }

    @Override
    public void showGithub(String github) {
        tv_github.setText(github);
    }

    @Override
    public void showLevel(String level) {
        tv_level.setText(level);
    }
}
