package com.xiseven.diycode.ui.activity;

import android.os.Bundle;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.MyRepliesAdapter;
import com.xiseven.diycode.model.impl.MyRepliesModel;
import com.xiseven.diycode.ui.iView.IMyRepliesView;
import com.xiseven.diycode.ui.presenter.MyRepliesPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/1.
 */
public class MyRepliesActivity extends BaseActivity implements IMyRepliesView{
    @BindView(R.id.rec_myReplies)
    PullLoadMoreRecyclerView recView;
    MyRepliesPresenter mPresenter;
    MyRepliesAdapter mAdapter;
    private int i = 1;

    @Override
    public int getContentViewId() {
        return R.layout.activity_myreplies;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar("我的评论");
        mPresenter = new MyRepliesPresenter(this);
        initRecView();
        mPresenter.getMyReplies(null, null, 10);
    }

    private void initRecView() {
        recView.setLinearLayout();
        if (mAdapter == null) {
            mAdapter = new MyRepliesAdapter(mActivity);
        }
        recView.setAdapter(mAdapter);
        recView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.getMyReplies(null, null, 10);
                i = 1;
            }

            @Override
            public void onLoadMore() {
                mPresenter.getMyReplies(null, null, 10*++i);
            }
        });
    }

    @Override
    public void setList(List list) {
        mAdapter.setList(list);
        mAdapter.notifyDataSetChanged();
        recView.setPullLoadMoreCompleted();

    }

    @Override
    public void failed() {
        recView.setPullLoadMoreCompleted();
        showToast("加载失败");
    }
}
