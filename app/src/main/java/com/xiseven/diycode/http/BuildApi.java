package com.xiseven.diycode.http;

import com.xiseven.diycode.app.DiyCodeApp;
import com.xiseven.diycode.constant.C;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XISEVEN on 2016/11/29.
 */

public class BuildApi {
    private static Retrofit retrofit;

    public static APIService getAPIService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(C.baseUrl) //设置Base的访问路径
                    .addConverterFactory(GsonConverterFactory.create()) //设置默认的解析库：Gson
                    .client(DiyCodeApp.defaultOkHttpClient())
                    .build();
        }
        return retrofit.create(APIService.class);
    }
}
