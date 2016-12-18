package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.constant.C;
import com.xiseven.diycode.http.APIService;
import com.xiseven.diycode.http.BuildApi;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.TopicModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.INodeView;
import com.xiseven.diycode.ui.iView.ITopicView;
import com.xiseven.diycode.utils.SPUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public class TopicPresenter extends BasePresenter {
    private INodeView mView;
    private TopicModel mModel;
    private Context mContext;

    public TopicPresenter(IBaseView iView, Context context) {
        super(context);
        mContext = context;
        mView = (INodeView) iView;
        mModel = new TopicModel();
    }

    public void getTopics(String type, Integer node_id, Integer offset, Integer limit) {
        mModel.getTopics(mContext, type, node_id, offset, limit, new MyCallBack() {
            @Override
            public void success() {
                mView.notifyRecView(mModel.topicList);
            }

            @Override
            public void failed() {
                mView.getFailed();
            }
        });
    }
}
