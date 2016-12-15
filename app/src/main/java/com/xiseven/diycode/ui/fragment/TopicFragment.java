package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.TopicsAdapter;
import com.xiseven.diycode.bean.Topic;
import com.xiseven.diycode.ui.activity.MainActivity;
import com.xiseven.diycode.ui.iView.ITopicView;
import com.xiseven.diycode.ui.presenter.TopicPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/11/21.
 */

public class TopicFragment extends BaseFragment implements ITopicView {
    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView recView;
    private TopicPresenter mPresenter;
    private int i = 1;
    private TopicsAdapter topicsAdapter;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initAllMembers(View view, Bundle savedInstanceState) {
        mPresenter = new TopicPresenter(this, mActivity);
        initRecView();
        recView.setRefreshing(true);
        mPresenter.getTopics(null, null, null, 10);
    }

    private void initRecView() {
        recView.setLinearLayout();
        if (topicsAdapter == null) {
            topicsAdapter = new TopicsAdapter(mActivity);
        }
        recView.setAdapter(topicsAdapter);
        recView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.getTopics(null, null, null, 10);
                i = 1;
            }

            @Override
            public void onLoadMore() {
                mPresenter.getTopics(null, null, null, 10 * ++i);
            }
        });
    }

    @Override
    public void notifyRecView(List<Topic> mList) {
        topicsAdapter.setProjectList(mList);
        topicsAdapter.notifyDataSetChanged();
        recView.setPullLoadMoreCompleted();
    }

    @Override
    public void getProjectsFailed() {
        recView.setPullLoadMoreCompleted();
        showToast("加载失败");
    }
}
