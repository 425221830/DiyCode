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
import com.xiseven.diycode.model.ILoginModel;
import com.xiseven.diycode.utils.BitmapUtils;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class LoginModel implements ILoginModel {

    private static final String TAG = LoginModel.class.getSimpleName();
    private User user;

    /**
     * 获取token
     * @param username
     * @param password
     * @param callback
     */
    @Override
    public void getToken(final String username, final String password, final MyCallBack callback) {
        Call<JsonObject> tokenCall =
                BuildApi.getAPIService().getToken(C.client_id, C.client_secret, "password", password, username);
        tokenCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        String token = (String) jsonObject.opt("access_token");
                        callback.success(token);
                        Log.e(TAG, "onResponse: onRespsonse--true");
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
     * @param platform
     * @param token
     */
    @Override
    public void postDevices(String platform, String token) {
        Call<JsonObject> call = BuildApi.getAPIService().postDevices(platform, token);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: ");
            }
        });
    }

    /**
     * 获取我的信息
     * @param token
     */
    @Override
    public void getMyInfo(final String token) {
        Call<User> myInfo = BuildApi.getAPIService().getMyInfo(C.getRequestHeaderValue(token));
        myInfo.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                getHeadImg();
                Log.e(TAG, "onResponse: "+ user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    /**
     * 获取我的头像图片
     */
    private void getHeadImg() {
        Call<ResponseBody> headImg = BuildApi.getAPIService().getHeadImg(user.getAvatar_url());
        headImg.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                //保存头像到存储器
                BitmapUtils.saveImgToCache(DiyCodeApp.getContext(), "headImg", bitmap);
                saveMyInfo();
                Log.e(TAG, "onResponse: "+bitmap.toString());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 保存我的信息
     */
    private void saveMyInfo() {
        //将本地缓存的头像路径更新到user
        user.setAvatar_url(DiyCodeApp.getContext().getExternalCacheDir()+"/headImg.png");
        String s = new Gson().toJson(user);
        //json字符串存储我的信息到sp
        SPUtils.setParam(DiyCodeApp.getContext(), "myInfo", s);
    }
}
