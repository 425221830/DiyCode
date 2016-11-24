package com.xiseven.diycode.model;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.xiseven.diycode.C;
import com.xiseven.diycode.model.impl.ILoginModel;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class LoginModel implements ILoginModel {

    private static final String TAG = LoginModel.class.getSimpleName();

    @Override
    public void getToken(String username, String password, AbsCallback callback) {
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

    @Override
    public void getUserInfo(String token, AbsCallback callback) {
        OkGo.get(C.baseUrl+"/users/me.json")
                .headers(C.RequestHeaderKey, C.getRequestHeaderValue(token))
                .execute(callback);
    }
}
