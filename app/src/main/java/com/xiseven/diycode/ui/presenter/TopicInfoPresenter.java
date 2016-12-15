package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.TopicModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.ITopicInfoView;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public class TopicInfoPresenter extends BasePresenter {

    private TopicModel topicModel;
    private ITopicInfoView mView;

    public TopicInfoPresenter(IBaseView iView) {
        super((Context) iView);
        mView = (ITopicInfoView) iView;
        topicModel = new TopicModel();
    }

    public void getTopicBody(Integer id) {
        topicModel.getTopicBody(id.toString(), new MyCallBack() {
            @Override
            public void success() {
                mView.setTopicBody(topicModel.body_html);
            }

            @Override
            public void failed() {

            }
        });
    }
}
