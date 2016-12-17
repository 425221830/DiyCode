package com.xiseven.diycode.model.impl;

import android.util.Log;

import com.xiseven.diycode.bean.News;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XISEVEN on 2016/12/5.
 */

public class NewsModel {

    public List<News> news;

    /**
     * 获取新闻列表
     * @param node_id
     * @param offset
     * @param limit
     * @param callBack
     */
    public void getNews(Integer node_id, Integer offset, Integer limit, final MyCallBack callBack) {
        Observable<List<News>> newsObservable = BuildApi.getAPIService().getNews(node_id, offset, limit);
        //访问网络切换异步线程
        newsObservable.subscribeOn(Schedulers.io())
                //响应结果处理切换成主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<News>>() {
                    @Override
                    public void onCompleted() {
                        //请求结束回调
                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        callBack.failed();
                    }

                    @Override
                    public void onNext(List<News> newses) {
                        //成功结果返回
                        if (newses.isEmpty()) {
                            callBack.failed();
                        } else {
                            news = newses;
                            callBack.success();
                        }
                    }
                });
    }
}
