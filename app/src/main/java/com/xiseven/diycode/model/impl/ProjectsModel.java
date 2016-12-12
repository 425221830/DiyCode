package com.xiseven.diycode.model.impl;

import com.xiseven.diycode.bean.Project;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XISEVEN on 2016/12/7.
 */

public class ProjectsModel {
    public List<Project> projectList;

    public void getProjects(Integer node_id, Integer offset, Integer limit, final MyCallBack callBack) {
        Observable<List<Project>> projects = BuildApi.getAPIService().getProjects(node_id, offset, limit);
        projects.subscribeOn(Schedulers.io())
                //响应结果处理切换成主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Project>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.failed();
                    }

                    @Override
                    public void onNext(List<Project> projects) {
                        if (projects.isEmpty()) {
                            callBack.failed();
                        } else {
                            projectList = projects;
                            callBack.success();
                        }
                    }
                });

    }
}
