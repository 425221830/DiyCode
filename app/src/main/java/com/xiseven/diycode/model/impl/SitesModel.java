package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class SitesModel{

    public List<Sites> sitesList;

    /**
     * 获取酷站列表
     * @param callBack
     */
    public void getSites(final MyCallBack callBack) {
        Call<List<Sites>> sites = BuildApi.getAPIService().getSites();
        sites.enqueue(new Callback<List<Sites>>() {
            @Override
            public void onResponse(Call<List<Sites>> call, Response<List<Sites>> response) {
                if (response.isSuccessful()) {
                    sitesList = response.body();
                    callBack.success();
                } else {
                    callBack.failed();
                }
            }

            @Override
            public void onFailure(Call<List<Sites>> call, Throwable t) {
                callBack.failed();
            }
        });
    }
}
