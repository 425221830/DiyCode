package com.xiseven.diycode.ui.iView;

import com.xiseven.diycode.bean.TopicReplies;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/15.
 */

public interface ITopicInfoView extends IBaseView {
    void setTopicBody(String body);
    void setTopicRepliesAdapter(List<TopicReplies> repliesList);

}
