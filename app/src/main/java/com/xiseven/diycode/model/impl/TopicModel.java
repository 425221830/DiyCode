package com.xiseven.diycode.model.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.bean.TopicReplies;
import com.xiseven.diycode.constant.C;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public class TopicModel {

    public List<Topic> topicList;
    public String body_html;
    public List<TopicReplies> repliesList;

    /**
     * 获取topic列表
     *
     * @param context
     * @param type
     * @param node_id
     * @param offset
     * @param limit
     * @param callBack
     */
    public void getTopics(Context context, String type, Integer node_id, Integer offset, Integer limit, final MyCallBack callBack) {
        String token = (String) SPUtils.getParam(context, "token", "");
        if (token.isEmpty()) {
            token = null;
        } else {
            token = C.getRequestHeaderValue(token);
        }
        Observable<List<Topic>> topics = BuildApi.getAPIService().getTopics(token, type, node_id, offset, limit);
        topics.subscribeOn(Schedulers.io())
                //响应结果处理切换成主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Topic>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed();
                    }

                    @Override
                    public void onNext(List<Topic> topics) {
                        if (topics.isEmpty()) {
                            callBack.failed();
                        } else {
                            topicList = topics;
                            callBack.success();
                        }
                    }
                });
    }

    /**
     * 获取topic内容
     *
     * @param id
     * @param callBack
     */
    public void getTopicBody(String id, final MyCallBack callBack) {
        Observable<JsonObject> topicBody = BuildApi.getAPIService().getTopicBody(id);
        topicBody.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed();
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.isJsonNull()) {
                            callBack.failed();
                        } else {
                            try {
                                body_html = (String) new JSONObject(
                                        String.valueOf(jsonObject)).opt("body_html");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } finally {
                                callBack.success();
                            }
                        }
                    }
                });
    }

    /**
     * 获取topic评论
     *
     * @param id
     * @param callBack
     */
    public void getTopicReplies(String id, Integer limit, final MyCallBack callBack) {
        Observable<List<TopicReplies>> topicReplies = BuildApi.getAPIService().getTopicReplies(id, limit);
        topicReplies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TopicReplies>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed();
                    }

                    @Override
                    public void onNext(List<TopicReplies> topicReplies) {
                        if (topicReplies.isEmpty()) {
                            callBack.failed();
                        } else {
                            repliesList = topicReplies;
                            callBack.success();
                        }
                    }
                });
    }
}