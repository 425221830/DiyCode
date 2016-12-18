package com.xiseven.diycode.ui.activity;

import android.os.Bundle;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.IAdapter;
import com.xiseven.diycode.adapter.NewsAdapter;
import com.xiseven.diycode.adapter.ProjectsAdapter;
import com.xiseven.diycode.adapter.TopicsAdapter;
import com.xiseven.diycode.ui.iView.INodeView;
import com.xiseven.diycode.ui.presenter.NewsPresenter;
import com.xiseven.diycode.ui.presenter.ProjectPresenter;
import com.xiseven.diycode.ui.presenter.TopicPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/12/18.
 */

public class NodeActivity extends BaseActivity implements INodeView {

    @BindView(R.id.rec_node)
    PullLoadMoreRecyclerView recNode;
    private String category;
    private int node_id;
    private int i;
    private IAdapter iAdapter;
    private NewsAdapter newsAdapter;
    private ProjectsAdapter projectsAdapter;
    private TopicsAdapter topicsAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_node;
    }

    @Override
    protected void initAllMembers(Bundle savedInstanceState) {
        initToolbar(getIntent().getStringExtra("title"));
        node_id = getIntent().getIntExtra("node_id", 1);
        category = getIntent().getStringExtra("category");
        initRecView();
        recNode.setRefreshing(true);
    }

    private void initRecView() {
        recNode.setLinearLayout();
        switch (category) {
            case "news":
                newsAdapter = new NewsAdapter(mActivity);
                recNode.setAdapter(newsAdapter);
                iAdapter = newsAdapter;
                final NewsPresenter nPresenter = new NewsPresenter(this, mActivity);
                recNode.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        nPresenter.getNews(node_id, null, 10);
                        i = 1;
                    }

                    @Override
                    public void onLoadMore() {
                        nPresenter.getNews(node_id, null, 10 * ++i);

                    }
                });
                nPresenter.getNews(node_id, null, 10);
                break;
            case "projects":
                projectsAdapter = new ProjectsAdapter(mActivity);
                recNode.setAdapter(projectsAdapter);
                iAdapter = projectsAdapter;
                final ProjectPresenter pPresenter = new ProjectPresenter(this, mActivity);
                recNode.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        pPresenter.getProjects(node_id, null, 10);
                        i = 1;
                    }

                    @Override
                    public void onLoadMore() {
                        pPresenter.getProjects(node_id, null, 10 * ++i);

                    }
                });
                pPresenter.getProjects(node_id, null, 10);
                break;
            case "topics":
                topicsAdapter = new TopicsAdapter(mActivity);
                recNode.setAdapter(topicsAdapter);
                iAdapter = topicsAdapter;
                final TopicPresenter tPresenter = new TopicPresenter(this, mActivity);
                recNode.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
                    @Override
                    public void onRefresh() {
                        tPresenter.getTopics(null, node_id, null, 10);
                        i = 1;
                    }

                    @Override
                    public void onLoadMore() {
                        tPresenter.getTopics(null, node_id, null, 10 * ++i);
                    }
                });
                tPresenter.getTopics(null, node_id, null, 10);
                break;
        }
    }

    @Override
    public void notifyRecView(List list) {
        iAdapter.setList(list);
        iAdapter.notifyChange();
        recNode.setPullLoadMoreCompleted();
    }

    @Override
    public void getFailed() {
        recNode.setPullLoadMoreCompleted();
        showToast("加载失败");
    }
}
