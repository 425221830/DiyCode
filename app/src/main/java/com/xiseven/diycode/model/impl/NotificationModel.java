package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.app.DiyCodeApp;
import com.xiseven.diycode.bean.Notification;
import com.xiseven.diycode.constant.C;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.utils.SPUtils;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class NotificationModel {
    public List<Notification> nList;


    /**
     * 获取消息通知
     * @param offset
     * @param limit
     * @param callBack
     */
    public void getNotification(Integer offset, Integer limit, final MyCallBack callBack) {
        Observable<List<Notification>> token = BuildApi.getAPIService().getNotifications(
                C.getRequestHeaderValue((String) SPUtils.getParam(DiyCodeApp.getContext(), "token", "")), offset, limit);
        token.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Notification>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed();
                    }

                    @Override
                    public void onNext(List<Notification> notifications) {
                        if (notifications.isEmpty()) {
                            callBack.failed();
                        } else {
                            nList = notifications;
                            callBack.success();
                        }
                    }
                });
    }
}
