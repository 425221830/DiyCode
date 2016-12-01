package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.app.DiyCodeApp;
import com.xiseven.diycode.http.APIService;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.IMyInfoModel;
import com.xiseven.diycode.utils.SPUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class MyInfoModel implements IMyInfoModel {
    public final String platform = "android";

    public void deleteDevices(final MyCallBack callBack) {
        Call<ResponseBody> deleteDevices = BuildApi.getAPIService().deleteDevices(platform,
                (String) SPUtils.getParam(DiyCodeApp.getContext(), "token", ""));
        deleteDevices.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                callBack.success();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.failed();
            }
        });
    }
}
