package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.model.ISitesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class SitesModel implements ISitesModel{
    @Override
    public void getSites() {
        Call<List<Sites>> sites = BuildApi.getAPIService().getSites();
        sites.enqueue(new Callback<List<Sites>>() {
            @Override
            public void onResponse(Call<List<Sites>> call, Response<List<Sites>> response) {
                List<Sites> sitesList = response.body();
            }

            @Override
            public void onFailure(Call<List<Sites>> call, Throwable t) {

            }
        });
    }
}
