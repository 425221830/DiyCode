package com.xiseven.diycode.ui.activity;

import android.os.Bundle;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.NotificationAdapter;
import com.xiseven.diycode.ui.iView.INotificationView;
import com.xiseven.diycode.ui.presenter.NotificationPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class NotificationActivity extends BaseActivity implements INotificationView {

    @BindView(R.id.rec_notification)
    PullLoadMoreRecyclerView recView;
    NotificationPresenter mPresenter;
    NotificationAdapter mAdapter;
    private int i = 1;

    @Override
    public int getContentViewId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("消息");
        mPresenter = new NotificationPresenter(this);
        initRecView();
    }

    private void initRecView() {
        recView.setLinearLayout();
        if (mAdapter == null) {
            mAdapter = new NotificationAdapter(mActivity);
        }
        recView.setAdapter(mAdapter);
        mPresenter.getNotification(null, 10);
        recView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNotification(null, 10);
                i = 1;
            }

            @Override
            public void onLoadMore() {
                mPresenter.getNotification(null, 10 * ++i);

            }
        });
    }

    @Override
    public void setList(List list) {
        mAdapter.setNotifications(list);
        mAdapter.notifyDataSetChanged();
        recView.setPullLoadMoreCompleted();

    }

    @Override
    public void failed() {
        recView.setPullLoadMoreCompleted();
        showToast("加载失败");

    }
}
