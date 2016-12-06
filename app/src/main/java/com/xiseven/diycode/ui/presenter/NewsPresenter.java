package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.NewsModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.INewsView;

/**
 * Created by XISEVEN on 2016/12/5.
 */

public class NewsPresenter extends BasePresenter {

    private NewsModel mModel;
    private INewsView mView;

    public NewsPresenter(IBaseView iView, Context context) {
        super(context);
        mView = (INewsView) iView;
        mModel = new NewsModel();
    }

    public void getNews(Integer node_id, Integer offset, Integer limit) {
        mModel.getNews(node_id, offset, limit, new MyCallBack() {
            @Override
            public void success() {
                mView.notifyRecView(mModel.news);
            }

            @Override
            public void failed() {
                mView.getNewsFailed();
            }
        });
    }
}
