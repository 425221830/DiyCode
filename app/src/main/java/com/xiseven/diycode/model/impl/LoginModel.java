package com.xiseven.diycode.model.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xiseven.diycode.app.DiyCodeApp;
import com.xiseven.diycode.bean.User;
import com.xiseven.diycode.constant.C;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.utils.BitmapUtils;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class LoginModel {

    private static final String TAG = LoginModel.class.getSimpleName();
    private User user;
    private String token;
    private String platform = "android";

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @param callback
     */
    public void getToken(final String username, final String password, final MyCallBack callback) {
        final Call<JsonObject> tokenCall =
                BuildApi.getAPIService().getToken(C.client_id, C.client_secret, "password", password, username);
        tokenCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        token = (String) jsonObject.opt("access_token");
                        SPUtils.setParam(DiyCodeApp.getContext(), "token", token);
                        callback.success();
                        Log.e(TAG, "onResponse: onRespsonse--true " + token.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "onResponse: onRespsonse--flase");
                    callback.failed();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
                callback.failed();
            }

        });
    }

    /**
     * 注册设备
     *
     */
    public void postDevices(final MyCallBack myCallBack) {
        Call<JsonObject> call = BuildApi.getAPIService().postDevices(platform, token);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "onResponse: ");
                myCallBack.success();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
                myCallBack.failed();
            }
        });
    }

    /**
     * 获取我的信息
     *
     */
    public void getMyInfo(final MyCallBack myCallBack) {
        Call<User> myInfo = BuildApi.getAPIService().getMyInfo(C.getRequestHeaderValue(token));
        myInfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    myCallBack.success();
                } else {
                    myCallBack.failed();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                myCallBack.failed();
            }
        });
    }

    /**
     * 获取我的头像图片
     */
    public void getHeadImg(final MyCallBack myCallBack) {
        Call<ResponseBody> headImg = BuildApi.getAPIService().getHeadImg(user.getAvatar_url());
        headImg.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    //保存头像到存储器
                    BitmapUtils.saveImgToCache(DiyCodeApp.getContext(), "headImg", bitmap);
                    saveMyInfo();
                    myCallBack.success();
                    Log.e(TAG, "onResponse: " + bitmap.toString());
                } else {
                    myCallBack.failed();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                myCallBack.failed();
            }
        });
    }

    /**
     * 保存我的信息
     */
    private void saveMyInfo() {
        //将本地缓存的头像路径更新到user
        user.setAvatar_url(DiyCodeApp.getContext().getExternalCacheDir() + "/headImg.png");
        String s = new Gson().toJson(user);
        //json字符串存储我的信息到sp
        SPUtils.setParam(DiyCodeApp.getContext(), "myInfo", s);
        SPUtils.setParam(DiyCodeApp.getContext(), "login", user.getLogin());

    }
}
