package com.xiseven.diycode.presenter;

import android.content.Context;
import android.util.Log;
import android.util.LogPrinter;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.xiseven.diycode.domain.User;
import com.xiseven.diycode.model.LoginModel;
import com.xiseven.diycode.model.impl.ILoginModel;
import com.xiseven.diycode.ui.impl.IBaseView;
import com.xiseven.diycode.ui.impl.ILoginView;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class LoginPresenter extends BasePresenter {
    private static final String TAG = LogPrinter.class.getSimpleName();
    private ILoginView mLoginView;
    private ILoginModel mLoginModel;
    private Context mContext;

    public LoginPresenter(IBaseView iView) {
        super(iView);
        iView.setPresenter(this);
        mContext = (Context) iView;
        mLoginView = (ILoginView) iView;
        mLoginModel = new LoginModel();
    }

    public void login(final String accounts, final String password) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                mLoginModel.getToken(accounts, password, new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        getUserInfo(s);
                        LoginPresenter.super.saveLoginInfo(accounts, password, s);
                        mLoginView.loginSuccess();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        mLoginView.loginFailed();
                        Log.d(TAG, "onError: " + e.toString());
                    }
                });
            }
        }.start();
    }

    private void getUserInfo(String respinseJson) {
        try {
            mLoginModel.getUserInfo((String) new JSONObject(respinseJson).opt("access_token"), new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    User user = JSON.parseObject(s, User.class);
                    LoginPresenter.super.saveUserInfo(user);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
