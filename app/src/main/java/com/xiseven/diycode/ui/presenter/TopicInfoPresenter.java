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

    public void getTopicReplies(Integer id, Integer limit) {
        topicModel.getTopicReplies(id.toString(), limit, new MyCallBack() {
            @Override
            public void success() {
                mView.notifyRecView(topicModel.repliesList);
            }

            @Override
            public void failed() {

            }
        });
    }

    public void getTopic(Integer topic_id) {
        topicModel.getTopic(topic_id, new MyCallBack() {
            @Override
            public void success() {
                mView.setTopic(topicModel.mTopic);
            }

            @Override
            public void failed() {

            }
        });
    }

    public void postTopicReplies(Integer id, String body) {
        topicModel.postTopicReplies(id.toString(), body, new MyCallBack() {
            @Override
            public void success() {
                mView.postRepliesSuccess();
            }

            @Override
            public void failed() {
                mView.postRepliesFailed();
            }
        });
    }

    public void postReplies(Integer id, String body) {
        topicModel.postReplies(id.toString(), body, new MyCallBack() {
            @Override
            public void success() {
                mView.postRepliesSuccess();
            }

            @Override
            public void failed() {
                mView.postRepliesFailed();
            }
        });
    }
}
