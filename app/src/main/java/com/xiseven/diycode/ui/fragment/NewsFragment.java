package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.NewsAdapter;
import com.xiseven.diycode.bean.News;
import com.xiseven.diycode.ui.iView.INewsView;
import com.xiseven.diycode.ui.presenter.MyInfoPresenter;
import com.xiseven.diycode.ui.presenter.NewsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/11/20.
 */

public class NewsFragment extends BaseFragment implements INewsView {
    private NewsPresenter mPresenter;
    private int i = 1;

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView recView;
    private NewsAdapter newsAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initAllMembers(View view, Bundle savedInstanceState) {
        mPresenter = new NewsPresenter(this, mActivity);
        initRecView();
        recView.setRefreshing(true);
        mPresenter.getNews(null, null, 10);
    }

    private void initRecView() {
        recView.setLinearLayout();
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(mActivity);
        }
        recView.setAdapter(newsAdapter);
        recView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNews(null, null, 10);
                i = 1;
            }

            @Override
            public void onLoadMore() {
                mPresenter.getNews(null, null, 10*++i);
            }
        });
    }

    /**
     * 更新RecyclerView的news数据
     * @param mList
     */
    @Override
    public void notifyRecView(List<News> mList) {
        newsAdapter.setNewsList(mList);
        newsAdapter.notifyDataSetChanged();
        recView.setPullLoadMoreCompleted();

    }

    @Override
    public void getNewsFailed() {
        recView.setPullLoadMoreCompleted();
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
    }

}
