package com.xiseven.diycode.utils;

import android.os.Looper;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.xiseven.diycode.C;


import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by XISEVEN on 2016/11/23.
 */

public class ApiUtils {
    static String TAG = "ApiUtils";

    public static String getToken(final String username, final String password) {
        //在主线程
        try {
            OkGo.post(C.getTokenUrl)
                    .params("client_id", C.client_id)
                    .params("client_secret", C.client_secret)
                    .params("grant_type", "password")
                    .params("password", password)
                    .params("username", username)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Log.e(TAG, "getToken: " + response.toString());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
