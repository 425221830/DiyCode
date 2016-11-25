package com.xiseven.diycode.model;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.xiseven.diycode.C;
import com.xiseven.diycode.model.impl.ILoginModel;

import java.io.IOException;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class LoginModel implements ILoginModel {

    private static final String TAG = LoginModel.class.getSimpleName();

    @Override
    public void getToken(final String username, final String password, final AbsCallback callback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    OkGo.post(C.getTokenUrl)
                            .params("client_id", C.client_id)
                            .params("client_secret", C.client_secret)
                            .params("grant_type", "password")
                            .params("password", password)
                            .params("username", username)
                            .execute(callback);
                } catch (Exception e) {
                    Log.e(TAG, "getToken: ", e);
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public void getUserInfo(final String token, final AbsCallback callback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //记录用户 Device 信息，用于 Push 通知
                    OkGo.post(C.baseUrl + "/devices.json")
                            .params("platform", "android")
                            .params("token", token)
                            .execute();
                    //获取用户信息
                    OkGo.get(C.baseUrl + "/users/me.json")
                            .headers(C.RequestHeaderKey, C.getRequestHeaderValue(token))
                            .execute(callback);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
