package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.ISitesModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class SitesModel implements ISitesModel {

    public List<Sites> sitesList;

    @Override
    public void getSites(final MyCallBack callBack) {
        Call<List<Sites>> sites = BuildApi.getAPIService().getSites();
        sites.enqueue(new Callback<List<Sites>>() {
            @Override
            public void onResponse(Call<List<Sites>> call, Response<List<Sites>> response) {
                sitesList = response.body();
                callBack.success();
            }

            @Override
            public void onFailure(Call<List<Sites>> call, Throwable t) {
                callBack.failed();
            }
        });
    }
}
