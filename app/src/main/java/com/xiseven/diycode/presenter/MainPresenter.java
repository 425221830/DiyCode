package com.xiseven.diycode.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.xiseven.diycode.domain.User;
import com.xiseven.diycode.impl.InitMyInfo;
import com.xiseven.diycode.model.LoginModel;
import com.xiseven.diycode.model.MainModel;
import com.xiseven.diycode.model.impl.IMainModel;
import com.xiseven.diycode.ui.impl.IBaseView;
import com.xiseven.diycode.ui.impl.IMainView;
import com.xiseven.diycode.utils.BitmapUtils;
import com.xiseven.diycode.utils.SPUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class MainPresenter extends BasePresenter {
    private Context mContext;
    private IMainView mMainView;
    private IMainModel mMainModel;

    public MainPresenter(IBaseView iView) {
        super(iView);
        mContext = (Context) iView;
        mMainView = (IMainView) iView;
        mMainModel = new MainModel();
    }

    public void initMyInfo(final InitMyInfo initMyInfo) {
        String userName = (String) SPUtils.getParam(mContext, "userName", "");
        String userHeadUrl = (String) SPUtils.getParam(mContext, "userHeadUrl", "");
        mMainModel.getHeadImg(userHeadUrl, new BitmapCallback() {
            @Override
            public void onSuccess(Bitmap bitmap, Call call, Response response) {
                initMyInfo.setHeadImg(bitmap);
                BitmapUtils.saveImgToCache(mContext, "userHead", bitmap);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                initMyInfo.setHeadImg(BitmapUtils.getCacheBitmap(mContext, "userHead"));
            }
        });
        initMyInfo.setUserName(userName);
    }

    public void login(final String accounts, final String password) {
        new LoginModel().getToken(accounts, password, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                MainPresenter.super.saveLoginInfo(accounts, password, s);
                new LoginModel().getUserInfo((String) SPUtils.getParam(mContext, "access_token", 0), new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        User user = JSON.parseObject(s, User.class);
                        MainPresenter.super.saveUserInfo(user);
                    }
                });

            }
        });

    }

    public void showHeadView() {
        mMainView.showHeadView();
    }

}
