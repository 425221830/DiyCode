package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.bean.MyReplies;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class MyRepliesModel {
    public List list;

    public void getMyReplies(String login, String order, Integer offset, Integer limit, final MyCallBack callBack) {
        Observable<List<MyReplies>> replies = BuildApi.getAPIService().getReplies(login, order, offset, limit);
        replies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MyReplies>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed();
                    }

                    @Override
                    public void onNext(List<MyReplies> myReplies) {
                        if (myReplies.isEmpty()) {
                            callBack.failed();
                        } else {
                            list = myReplies;
                            callBack.success();
                        }
                    }
                });
    }
}
