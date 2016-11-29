package com.xiseven.diycode.http;

import com.google.gson.JsonObject;
import com.xiseven.diycode.bean.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by XISEVEN on 2016/11/29.
 */

public interface APIService {
    @FormUrlEncoded
    @POST("http://www.diycode.cc/oauth/token")
    Call<JsonObject> getToken(@Field("client_id") String client_id,
                              @Field("client_secret") String client_secret,
                              @Field("grant_type") String grant_type,
                              @Field("password") String password,
                              @Field("username") String username);
    @FormUrlEncoded
    @POST("devices.json")
    Call<JsonObject> postDevices(@Field("platform") String platform,
                     @Field("token") String token);

    @GET("users/me.json")
    Call<User> getMyInfo(@Header("Authorization") String Authorization);

    @GET()
    Call<ResponseBody> getHeadImg(@Url String url);
}
