package com.xiseven.diycode.http;

import com.google.gson.JsonObject;
import com.xiseven.diycode.bean.MyReplies;
import com.xiseven.diycode.bean.News;
import com.xiseven.diycode.bean.Notification;
import com.xiseven.diycode.bean.Project;
import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.bean.TopicReplies;
import com.xiseven.diycode.bean.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by XISEVEN on 2016/11/29.
 */

public interface APIService {
    @FormUrlEncoded
    @POST("https://www.diycode.cc/oauth/token")
    Call<JsonObject> getToken(@Field("client_id") String client_id,
                              @Field("client_secret") String client_secret,
                              @Field("grant_type") String grant_type,
                              @Field("password") String password,
                              @Field("username") String username);

    @FormUrlEncoded
    @POST("devices.json")
    Call<JsonObject> postDevices(@Field("platform") String platform,
                                 @Field("token") String token);

    @DELETE("devices.json")
    Call<ResponseBody> deleteDevices(@Query("platform") String platform,
                                     @Query("token") String token);

    @GET("users/me.json")
    Call<User> getMyInfo(@Header("Authorization") String Authorization);

    @GET()
    Call<ResponseBody> getHeadImg(@Url String url);

    @GET("sites.json")
    Call<List<Sites>> getSites();

    @GET("news.json")
    Observable<List<News>> getNews(@Query("node_id") Integer node_id,
                                   @Query("offset") Integer offset,
                                   @Query("limit") Integer limit);

    @GET("projects.json")
    Observable<List<Project>> getProjects(@Query("node_id") Integer node_id,
                                          @Query("offset") Integer offset,
                                          @Query("limit") Integer limit);

    @GET("topics.json")
    Observable<List<Topic>> getTopics(@Header("Authorization") String Authorization,
                                      @Query("type") String type,
                                      @Query("node_id") Integer node_id,
                                      @Query("offset") Integer offset,
                                      @Query("limit") Integer limit);

    @GET("topics/{id}.json")
    Observable<Topic> getTopic(@Path("id") String id);


    @GET("topics/{id}.json")
    Observable<JsonObject> getTopicBody(@Path("id") String id);

    @GET("topics/{id}/replies.json")
    Observable<List<TopicReplies>> getTopicReplies(@Path("id") String id,
                                                   @Query("limit") Integer limit);

    @FormUrlEncoded
    @POST("topics/{id}/replies.json")
    Observable<ResponseBody> postTopicReplies(@Header("Authorization") String Authorization,
                                              @Path("id") String id,
                                              @Field("body") String body);
    @FormUrlEncoded
    @POST("replies/{id}.json")
    Observable<ResponseBody> postReplies(@Header("Authorization") String Authorization,
                                         @Path("id") String id,
                                         @Field("body") String body);

    @GET("notifications.json")
    Observable<List<Notification>> getNotifications(@Header("Authorization") String Authorization,
                                                    @Query("offset") Integer offset,
                                                    @Query("limit") Integer limit);

    @GET("users/{login}/replies.json")
    Observable<List<MyReplies>> getReplies(@Path("login") String login,
                                           @Query("order") String order,
                                           @Query("offset") Integer offset,
                                           @Query("limit") Integer limit);
}

